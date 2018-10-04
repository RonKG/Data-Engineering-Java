package dataAccessObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import interfaces.TransactionDAOInterface;
import modelClasses.Branches;
import modelClasses.Transaction;
import modelClasses.Zipcodes;

public class TransactionDAOImplementation implements TransactionDAOInterface{
	
	Connection connection = null;
	
	private static final String JDBC_DRIVER = 
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://localhost/cdw_sapp?autoReconnect=true&useSSL=false";
	private static final String USER = 
			"root";
	private static final String PASS = 
			"root";
	private static final String ZIPCODE_DATE = 
			"SELECT  TRANSACTION_ID, FIRST_NAME, LAST_NAME, CUST_STATE, cd.CREDIT_CARD_NO, CUST_ZIP, STR_TO_DATE( CONCAT( day, '-', month, '-', year ), '%d-%m-%Y') AS dt \n" + 
			"FROM cdw_sapp_customer cs\n" + 
			"RIGHT JOIN cdw_sapp_creditcard cd\n" + 
			"ON cs.CREDIT_CARD_NO = cd.CREDIT_CARD_NO\n" + 
			"WHERE CUST_ZIP = ?\n" + 
			"AND MONTH = ? AND YEAR = ?";
	private static final String MONTHLY_BILL = 
			"SELECT TRANSACTION_ID, DAY, MONTH, YEAR, CREDIT_CARD_NO, TRANSACTION_VALUE\n" + 
			"FROM cdw_sapp_creditcard\n" + 
			"WHERE MONTH=? AND YEAR=? AND CUST_SSN=?;";
	private static final String GROUP_TRANSACTIONS =
			"SELECT COUNT(*),TRANSACTION_TYPE, SUM(TRANSACTION_VALUE)\n" + 
			"FROM cdw_sapp_creditcard\n" + 
			"WHERE  TRANSACTION_TYPE = ?";
	/*
	private static final String SELECT_DATES2 = 
			"SELECT * FROM cdw_sapp_creditcard \n" + 
			"WHERE STR_TO_DATE( CONCAT( day, '-', month, '-', year ), '%d-%m-%Y') \n" + 
			"BETWEEN STR_TO_DATE( ?, '%d-%m-%Y' ) \n" + 
			"AND STR_TO_DATE( ?, '%d-%m-%Y' )" + 
			"ORDER BY STR_TO_DATE( CONCAT( day, '-', month, '-', year ), '%d-%m-%Y');";
			*/
	private static final String SELECT_DATES = 
			"SELECT TRANSACTION_ID, \n" + 
			"CUST_SSN, \n" + 
			"CREDIT_CARD_NO, \n" + 
			"BRANCH_CODE,\n" + 
			"TRANSACTION_TYPE,\n" + 
			"TRANSACTION_VALUE,\n" +
			"DAY,\n" + 
			"MONTH,\n" + 
			"YEAR,\n"+
			"CONCAT(YEAR,'-',lpad(MONTH,2,0),'-',lpad(DAY,2,0)) as date\n" + 
			"FROM cdw_sapp_creditcard\n" + 
			"WHERE CUST_SSN=? \n" + 
			"AND ((CONCAT(YEAR,'-',lpad(MONTH,2,0),'-',lpad(DAY,2,0)) BETWEEN ? AND ?))\n" + 
			"ORDER BY date;";
	/*
			"SELECT BRANCH_STATE, SUM(TRANSACTION_VALUE)\n" + 
			"FROM cdw_sapp_creditcard cd\n" + 
			"LEFT JOIN cdw_sapp_branch br\n" + 
			"ON cd.BRANCH_CODE = br.BRANCH_CODE\n" + 
			"WHERE BRANCH_STATE = ?;";	
			*/
	private static final String BRANCH_TRANSACTIONS2 =
			"SELECT BRANCH_STATE, SUM(TRANSACTION_VALUE), COUNT(*) AS COUNT\n" + 
			"FROM cdw_sapp_creditcard cd\n" + 
			"LEFT JOIN cdw_sapp_branch br\n" + 
			"ON cd.BRANCH_CODE = br.BRANCH_CODE\n" + 
			"WHERE BRANCH_STATE = ?;";
	/*
	private static final String BRANCH_TRANSACTIONS2 = "SELECT COUNT(*), BRANCH_STATE, SUM(TRANSACTION_VALUE)\n" + 
			"			FROM cdw_sapp_creditcard cd\n" + 
			"			LEFT JOIN cdw_sapp_branch br\n" + 
			"			ON cd.BRANCH_CODE = br.BRANCH_CODE\n" + 
			"			WHERE BRANCH_STATE = 'FL';";
	*/

	public Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			if (connection == null)
				connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connection;
	}
	
	public List<Zipcodes> byZipandDate(int zipCode, int month, int year)
	{
		/*
		 To display the transactions made by customers living in a given zipcode
		  for a given month and year. Order by day in descending order.
		 */		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; 
		
		try {
			conn = getConnection();
            stmt = conn.prepareStatement(ZIPCODE_DATE);           
            stmt.setInt(1, zipCode);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            rs = stmt.executeQuery();
            List<Zipcodes> zipcodes = new LinkedList<Zipcodes>();  
            while (rs.next()) {       
            	Zipcodes zipcode = new Zipcodes();
            	zipcode.id = rs.getInt(1);
            	zipcode.fname = rs.getString("FIRST_NAME"); 
            	zipcode.lname = rs.getString("LAST_NAME"); 
            	zipcode.state = rs.getString("CUST_STATE"); 
            	zipcode.cc = rs.getString("CREDIT_CARD_NO"); 
            	zipcode.zip = rs.getString("CUST_ZIP"); 
            	zipcode.date = rs.getString(7); 
            	zipcodes.add(zipcode);
            }    
            System.out.println(".......Fetching transactions from Zipcode: "+zipCode);
            rs.close();
            stmt.close();
            return zipcodes; 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }	
	}
	
	public void groupByTransactionType(String billType)
	{
		/*
		 To display the number and total values of transactions for a given type.
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		try {			
			conn = getConnection();
			stmt = conn.prepareStatement(GROUP_TRANSACTIONS);
			stmt.setString(1, billType);	
			rs  = stmt.executeQuery(); 
            while (rs.next()) {
            	//append result and display            	
            	System.out.println("Transaction Type   :  " + rs.getString(2) + 
            			"\n# of Transactions  : \t" + rs.getString(1) +
            			"\nTotal Amount       : \t$" + numFormatter(rs.getDouble(3)) + "\n");
            }  
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
 	}
	
	public Branches totalsByBranch(String state)
	{
		/*
		  To display the number and total values of transactions for branches in a given state
		 */		
		Connection conn = null;
		PreparedStatement stmt = null;		
		try {
			conn = getConnection();
            stmt = conn.prepareStatement(BRANCH_TRANSACTIONS2);
            Branches branch = null;            
            stmt.setString(1, state);
            ResultSet rs = stmt.executeQuery();                   
            while (rs.next()) {            	
            	branch = new Branches();
            	branch.state = rs.getString(1);
            	branch.value = rs.getFloat(2);
            	branch.count = rs.getInt(3);
            }
            rs.close();
            stmt.close();
            return branch; 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        	} 
		}
	
	public List<Transaction> getMonthlyBill(int month, int year, int ssn) {
		/*
		 * To generate monthly bill for a credit card number for a given month and year.
		 */		
		Connection conn = null;
		PreparedStatement stmt = null;		
		try {
			conn = getConnection();
            stmt = conn.prepareStatement(MONTHLY_BILL);
            Transaction transaction = null;            
            stmt.setInt(1, month);
            stmt.setInt(2, year); // change this to dateformat
            stmt.setInt(3, ssn); // maybe change this to CC number            
            List<Transaction> transactions = new LinkedList<Transaction>();   
            ResultSet rs = stmt.executeQuery();                   
            while (rs.next()) {            	
            	transaction = new Transaction();
            	transaction.setTransactionId(rs.getInt(1));
            	transaction.setDay((int) rs.getFloat(2));
            	transaction.setMonth(rs.getInt(3));
            	transaction.setYear(rs.getInt(4));
            	transaction.setCcNumber(rs.getString(5));
            	transaction.setTransactionValue(rs.getFloat(6));            	
            	transactions.add(transaction);
            }
            rs.close();
            stmt.close();
            return transactions; 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}
	
	public List<Transaction> viewSelectDates(String startDate, String endDate, String ssn) {

		/*
		 * To display the transactions made by a customer between two dates. Order by
		 * year, month, and day in descending order.
		 */
		
		Connection conn = null;
		Transaction transaction = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
            stmt = conn.prepareStatement(SELECT_DATES);            
            //transaction = null;
            
            stmt.setInt(1, Integer.parseInt(ssn));
            stmt.setString(2, startDate);
            stmt.setString(3, endDate);
             
            List<Transaction> transactions = new LinkedList<Transaction>();            
 
            ResultSet rs = stmt.executeQuery(); 
                    
            while (rs.next()) {
            	
            	transaction = new Transaction();
            	transaction.setTransactionId(rs.getInt(1));
            	transaction.setSsn(Integer.parseInt(rs.getString(2)));
            	transaction.setCcNumber(rs.getString(3));
            	transaction.setBranchCode(Integer.parseInt(rs.getString(4)));
            	transaction.setTransactiontype(rs.getString(5));
            	transaction.setTransactionValue(rs.getFloat(6));
            	transaction.setDay((int) rs.getFloat(7));
            	transaction.setMonth(rs.getInt(8));
            	transaction.setYear(rs.getInt(9));            	
            	transactions.add(transaction);
            }
                 
            rs.close();
            stmt.close();
            return transactions;
 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
	}	

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}
	
	public String numFormatter(double d) {
		return String.format("%.2f", d);
	}

}

