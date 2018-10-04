package dataAccessObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import interfaces.CustomerDAOInterface;
import modelClasses.Customer;

public class CustomerDAOImplementation implements CustomerDAOInterface {

	static final String JDBC_DRIVER = 
			"com.mysql.jdbc.Driver";
	private static final String DB_URL = 
			"jdbc:mysql://localhost/cdw_sapp?autoReconnect=true&useSSL=false";
	private static final String USER = 
			"root";
	private static final String PASS = 
			"root";
	private static final String FIND_BY_SSN = 
			"SELECT * FROM cdw_sapp_customer WHERE SSN=?";
	private static final String UPDATE_CUSTOMER = 
			"UPDATE cdw_sapp_customer"	
			+ "SET APT_NO=?, "
			+ "STREET_NAME=?, "
			+ "CUST_CITY=?, "
			+ "CUST_STATE=?, "
			+ "CUST_COUNTRY=?, "
			+ "CUST_ZIP=?, "
			+ "CUST_PHONE=?, "
			+ "CUST_EMAIL=? "	
			+ "LAST_UPDATED" 
			+ "WHERE SSN=?";
	
	Connection connection = null;
	
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
	
	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			// do nothing
		}
	}

	public Customer getCustomer(int ssn) {
		/*
		 * To check the existing account details of a customer.
		 */

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(FIND_BY_SSN);
			stmt.setInt(1, ssn);

			Customer customer = new Customer();
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				customer.setSsn(rs.getInt("SSN"));
				customer.setFirstName(rs.getString("FIRST_NAME"));
				customer.setLastName(rs.getString("LAST_NAME"));
				customer.setCcNumber(rs.getString("CREDIT_CARD_NO"));
				customer.setAptNumber(rs.getString("APT_NO"));
				customer.setStreetName(rs.getString("STREET_NAME"));
				customer.setCity(rs.getString("CUST_CITY"));
				customer.setState(rs.getString("CUST_STATE"));
				customer.setCountry(rs.getString("CUST_COUNTRY"));
				customer.setZipcode(rs.getString("CUST_ZIP"));
				customer.setEmail(rs.getString("CUST_EMAIL"));
				customer.setPhoneNumber(rs.getInt("CUST_PHONE"));
				customer.setLastUpdated(rs.getString("LAST_UPDATED"));			
			
			}return customer;
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void updateCustomerD(Customer customer) {
		/*
		 * To modify the existing account details of a customer
		 */
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE_CUSTOMER);

			// Bind arraylist values into the parameters
			stmt.setString(1, customer.getAptNumber());
			stmt.setString(2, customer.getStreetName());
			stmt.setString(3, customer.getCity());
			stmt.setString(4, customer.getState());
			stmt.setString(5, customer.getCountry());
			stmt.setString(6, customer.getZipcode());
			stmt.setInt   (7, customer.getPhoneNumber());
			stmt.setString(8, customer.getEmail());
			//stmt.setInt   (9, customer.getSsn()); //getCurrentTimeStamp()
			
			// get and print updated database entry
			//CustomerDAOImplementation custDao = new CustomerDAOImplementation();
			Customer cust = getCustomer(customer.getSsn());
			System.out.println("User with SSN # " + customer.getSsn()
					+ " in data base : \n"
					+ cust.toString() + "\n");
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int updateCustomerOther(Customer customer) {
		/*
		 * To modify the existing account details of a customer
		 */
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE_CUSTOMER);

			stmt.setString(1, customer.getAptNumber());
			stmt.setString(2, customer.getStreetName());
			stmt.setString(3, customer.getCity());
			stmt.setString(4, customer.getState());
			stmt.setString(5, customer.getCountry());
			stmt.setString(6, customer.getZipcode());
			stmt.setInt   (7, customer.getPhoneNumber());
			stmt.setString(8, customer.getEmail());
			stmt.setLong  (9, customer.getSsn());

			System.out.println("User with SSN # " + customer.getSsn()
					+ " in data base : \n"
					+ customer.toString() + "\n");

			return stmt.executeUpdate();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void updateCustomerOther2(ArrayList<?> arr) {
		/*
		 * To modify the existing account details of a customer
		 */
		Customer customer = new Customer();
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(UPDATE_CUSTOMER);

			// Bind arraylist values into the parameters
			stmt.setString(1, (String) arr.get(0));
			stmt.setString(2, (String) arr.get(1));
			stmt.setString(3, (String) arr.get(2));
			stmt.setString(4, (String) arr.get(3));
			stmt.setString(5, (String) arr.get(4));
			stmt.setString(6, (String) arr.get(5));
			stmt.setInt   (7,  (int) arr.get(6));
			stmt.setString(8, (String) arr.get(7));
			stmt.setInt  (9, (int) arr.get(8));
			
			// get and print updated database entry
			CustomerDAOImplementation custDao = new CustomerDAOImplementation();
			customer = custDao.getCustomer((int) arr.get(8));
			System.out.println("User with SSN # " + customer.getSsn()
					+ " in data base : \n"
					+ customer.toString() + "\n");
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void updateCustomerNamed(Customer customer) {
		/*
		 * To modify the existing account details of a customer
		 */
		PreparedStatement stmt = null;
		Connection conn = null;
		String sql = 
				"UPDATE cdw_sapp_customer"	
						+ "SET APT_NO=? OR (APT_NO IS NULL AND ? IS NULL);, "
						+ "STREET_NAME=?, "
						+ "CUST_CITY=?, "
						+ "CUST_STATE=?, "
						+ "CUST_COUNTRY=?, "
						+ "CUST_ZIP=?, "
						+ "CUST_PHONE=?, "
						+ "CUST_EMAIL=? "	
						+ "LAST_UPDATED " 
						+"WHERE SSN=?";

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);

			// Bind arraylist values into the parameters
			if (customer.getAptNumber() != null)
				stmt.setString(1, customer.getAptNumber());
			if (customer.getStreetName() != null)
				stmt.setString(2, customer.getStreetName());
			if (customer.getCity() != null)
				stmt.setString(3, customer.getCity());
			if (customer.getState() != null)
				stmt.setString(4, customer.getState());
			if (customer.getCountry() != null)
				stmt.setString(5, customer.getCountry());
			if (customer.getZipcode() != null)
				stmt.setString(6, customer.getZipcode());
			if (customer.getPhoneNumber() != 0)
				stmt.setInt   (7, customer.getPhoneNumber());
			if (customer.getEmail() != null)
				stmt.setString(8, customer.getEmail());
			
			//stmt.setInt   (9, customer.getSsn()); //getCurrentTimeStamp()
			
			// get and print updated database entry
			//CustomerDAOImplementation custDao = new CustomerDAOImplementation();
			Customer cust = getCustomer(customer.getSsn());
			System.out.println("User with SSN # " + customer.getSsn()
					+ " in data base : \n"
					+ cust.toString() + "\n");
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			try {
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public  Timestamp getCurrentTimeStamp() { 
		java.util.Date date= new java.util.Date(); 
		Timestamp time = new Timestamp(date.getTime()); 
		return  time;
 }
	
	public void updateApt(String s, int ssn)
	{
		String apt = "UPDATE cdw_sapp_customer SET APT_NO=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(apt);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateStreet(String s, int ssn)
	{
		String street = "UPDATE cdw_sapp_customer SET STREET_NAME=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(street);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateState(String s, int ssn)
	{
		String state = "UPDATE cdw_sapp_customer SET CUST_STATE=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(state);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateCity(String s, int ssn)
	{
		String city = "UPDATE cdw_sapp_customer SET CUST_CITY=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(city);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateCountry(String s, int ssn)
	{
		String country = "UPDATE cdw_sapp_customer SET CUST_COUNTRY=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(country);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateZip(String s, int ssn)
	{
		String zip = "UPDATE cdw_sapp_customer SET CUST_ZIP=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(zip);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updatePhone(String s, int ssn)
	{
		String phone = "UPDATE cdw_sapp_customer SET CUST_PHONE=? WHERE SSN=?;";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(phone);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateEmail(String s, int ssn)
	{
		String email = "UPDATE cdw_sapp_customer SET CUST_EMAIL=? WHERE SSN=?;";		
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(email);
			stmt.setString(1, s);
			stmt.setInt(2, ssn);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
			}
	}
	
	public void updateCustomerMulti(Customer customer) {
		/*
		 * To modify the existing account details of a customer
		 */
		
		String apt = "UPDATE cdw_sapp_customer SET APT_NO=? WHERE SSN=?;";
		String street = "UPDATE cdw_sapp_customer SET STREET_NAME=? WHERE SSN=?;";
		String city = "UPDATE cdw_sapp_customer SET CUST_CITY=? WHERE SSN=?;";
		String state = "UPDATE cdw_sapp_customer SET CUST_STATE=? WHERE SSN=?;";
		String country = "UPDATE cdw_sapp_customer SET CUST_COUNTRY=? WHERE SSN=?;";
		String zip = "UPDATE cdw_sapp_customer SET CUST_ZIP=? WHERE SSN=?;";
		String phone = "UPDATE cdw_sapp_customer SET CUST_PHONE=? WHERE SSN=?;";
		String email = "UPDATE cdw_sapp_customer SET CUST_EMAIL=? WHERE SSN=?;";
		
		int ssn = customer.getSsn();
		
		//Statement stmt = null;
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			conn = getConnection();
			//stmt = conn.prepareStatement(UPDATE_CUSTOMER);

			// Bind arraylist values into the parameters
			stmt = conn.prepareStatement(apt);
			stmt.setString(1, customer.getAptNumber());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(street);
			stmt.setString(1, customer.getStreetName());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(city);
			stmt.setString(1, customer.getCity());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(state);
			stmt.setString(1, customer.getState());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(country);
			stmt.setString(1, customer.getCountry());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(zip);
			stmt.setString(1, customer.getZipcode());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(phone);
			stmt.setInt   (1, customer.getPhoneNumber());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(email);
			stmt.setString(1, customer.getEmail());
			stmt.setInt(2, customer.getSsn());
			stmt.executeUpdate();

			int status  = stmt.executeUpdate();
			System.out.println("Status: " + status);
					
			// get and print updated database entry
			//CustomerDAOImplementation custDao = new CustomerDAOImplementation();
			Customer cust = getCustomer(customer.getSsn());
			System.out.println("User with SSN # " + ssn
					+ " in data base : \n"
					+ cust.toString() + "\n");
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}	}
	

}


