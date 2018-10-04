package interfaces;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataAccessObj.CustomerDAOImplementation;
import dataAccessObj.TransactionDAOImplementation;
import modelClasses.Customer;
import modelClasses.Transaction;
import modelClasses.Zipcodes;

public class Runner {

	public static Scanner sc = new Scanner(System.in);

	public void findCustomer() {

		int userIn = 0;
		@SuppressWarnings("unused")
		boolean cont = true;

		while (cont = true) {
			System.out.println("\n======================================");
			System.out.println("Enter a SSN # to Get Customer Record\n\n" + "             OR\n\n"
					+ "         'Q' to exit                 ");
			System.out.println("======================================\n");
			if (sc.hasNext("q") || sc.hasNext("Q")) {

				System.out.println("............Program Exiting!");
				cont = false;
				return;
			} else {

				if (sc.hasNextInt()) {

					userIn = sc.nextInt();

					if (validateSSN(userIn)) {

						CustomerDAOImplementation getCust = new CustomerDAOImplementation();

						if (getCust.getCustomer(userIn).getSsn() == 0) {
							System.out.println("..........Customer not in DATABASE");
						} else {
							System.out.println(getCust.getCustomer(userIn) + "\n");
						}
					} else {
						System.out.println("............INVALID SSN NUMBER");
					}
				} else {
					try {
						userIn = Integer.parseInt(sc.next());

					} catch (NumberFormatException ex) {

						System.out.println("............INVALID NUMBER FORMAT");
					}
				}
			}
		}
	}

	public void updateCustomer() {
		CustomerDAOImplementation custDao = new CustomerDAOImplementation();
		Customer customer = new Customer();
		System.out.println("Enter a SSN to update a Customer Record: ");
		int ssn = sc.nextInt();
		customer.setSsn(ssn);

		while (true) {
			System.out.println("      ===============================================");
			System.out.println("      Choose a NUMBER to update OR press 'Q' to quit ");
			System.out.println("             #1 - Apartment number                   ");
			System.out.println("             #2 - Street name                        ");
			System.out.println("             #3 - City                               ");
			System.out.println("             #4 - State                              ");
			System.out.println("             #5 - Country                            ");
			System.out.println("             #6 - Zipcode                            ");
			System.out.println("             #7 - Phone number                       ");
			System.out.println("             #8 - Email                              ");
			System.out.println("              Q - to save and exit program           ");
			System.out.println("      ===============================================");

			String input = sc.next();
			sc.nextLine();
			System.out.println("You entered: " + input);

			switch (input) {
			case "1":
				System.out.println("enter new apartment #: ");
				String apt = sc.nextLine();
				custDao.updateApt(apt, ssn);
				System.out.println("apartment number updated to --> " + apt);
				break;
			case "2":
				System.out.println("enter new street name: ");
				String street = toTitleCase(sc.nextLine());
				custDao.updateStreet(street, ssn);
				System.out.println("street name updated to --> " + street);
				break;
			case "3":
				System.out.println("enter new city: ");
				String city = toTitleCase(sc.next());
				custDao.updateCity(city, ssn);
				System.out.println("city updated to --> " + city);
				break;
			case "4":
				System.out.println("enter new State abbreviation as XX: ");
				String state = sc.next().toUpperCase();
				if (state.length() == 2) {
					custDao.updateState(state, ssn);
					System.out.println("state updated to --> " + state);
				} else
					System.out.println("Wrong State Format!");
				break;
			case "5":
				System.out.println("enter new country: ");
				String country = toTitleCase(sc.next());
				custDao.updateCountry(country, ssn);
				System.out.println("country updated to --> " + country);
				break;
			case "6":
				System.out.println("enter new 5 Digit zipcode: ");
				String zip = sc.next();
				if (validateZipcode(zip)) {
					custDao.updateZip(zip, ssn);
					System.out.println("zipcode updated to --> " + zip);
				} else
					System.out.println("Wrong Zipcode Format!");
				break;
			case "7":
				System.out.println("enter new 7 digit phone number (XXXXXXX): ");
				String phone = sc.next();
				if (validatePhoneNumber(phone)) {
					custDao.updatePhone(phone, ssn);
					System.out.println("phone number updated to --> " + phone);
				} else
					System.out.println("Wrong Phone Number Format!");

				break;
			case "8":
				System.out.println("enter new email address: ");
				String email = sc.next();
				if (validateEmail(email)) {
					custDao.updateEmail(email, ssn);
					System.out.println("email updated to --> " + email);
				} else
					System.out.println("Wrong Email Format!");
				break;
			case "q":
			case "Q":
				System.out.println("program closing..");
				System.out.println("-----------------------------------\n" + "------- UPDATED TABLE RECORD ------");
				System.out.println(custDao.getCustomer(ssn) + "\n");
				return;
			default:
				System.err.println("Invalid input. Try again!!");
			}

		}

	}

	public void fetchCategoryTotals() {

		TransactionDAOImplementation groupCust = new TransactionDAOImplementation();

		while (true) {
			System.out.println("     ======================================================");
			System.out.println("     Pick a Categoryto view totals OR press 'Q' to quit    ");
			System.out.println("           #1 - Bills                                      ");
			System.out.println("           #2 - Education                                  ");
			System.out.println("           #3 - Entertainment                              ");
			System.out.println("           #4 - Gas                                        ");
			System.out.println("           #5 - Grocery                                    ");
			System.out.println("           #6 - Healthcare                                 ");
			System.out.println("           #7 - Test                                       ");
			System.out.println("            Q - to save and exit program                   ");
			System.out.println("    =======================================================\n");

			String input = sc.next();
			System.out.println("You entered: " + input);
			System.out.println("-------- TRANSACTION GROUP --------");
			System.out.println("");

			switch (input) {
			case "1":
				String bills = "Bills";
				groupCust.groupByTransactionType(bills);
				break;
			case "2":
				String education = "Education";
				groupCust.groupByTransactionType(education);
				break;
			case "3":
				String entertainment = "Entertainment";
				groupCust.groupByTransactionType(entertainment);
				break;
			case "4":
				String gas = "Gas";
				groupCust.groupByTransactionType(gas);
				break;
			case "5":
				String grocery = "Grocery";
				groupCust.groupByTransactionType(grocery);
				break;
			case "6":
				String healthcare = "Healthcare";
				groupCust.groupByTransactionType(healthcare);
				break;
			case "7":
				String test = "Test";
				groupCust.groupByTransactionType(test);
				break;
			case "Q":
			case "q":
				System.out.println("No Groups Chosen!\nprogram closing....");
				return;
			default:
				System.err.println("Invalid input. Try again!!");

			}
		}

	}

	public void fetchMonthlyBill() throws Exception {

		TransactionDAOImplementation monthBill = new TransactionDAOImplementation();

		@SuppressWarnings("unused")
		boolean cont = true;
		int month = 0;
		int year = 0;
		while (cont = true) {

			System.out.println("                               ");
			System.out.println("    ===============================");
			System.out.println("    =    VIEW MONTHLY BILL        =");
			System.out.println("    =  Enter __CUSTOMER SSN #__   =");
			System.out.println("    =             OR              =");
			System.out.println("    =          Q to quit          =");
			System.out.println("    ===============================");
			System.out.println("                               ");

			String s = sc.next();

			if (s.equalsIgnoreCase("q")) {
				System.out.println("   ......Program Exiting!");
				return;

			} else {
				try {
					System.out.println("Enter __MONTH #__");
					try {
						month = sc.nextInt();
					} catch (Exception e) {
						System.out.println("Wrong MONTH Format!");
						continue;
					}

					System.out.println("Enter __ YEAR #__");
					try {
						year = sc.nextInt();
					} catch (Exception e) {
						System.out.println("Wrong YEAR Format!");
						continue;
					}

					List<Transaction> transactions = monthBill.getMonthlyBill(month, year, Integer.parseInt(s));

					transactions.get(1).printBillHeader();

					for (int i = 0; i < transactions.size(); i++) {
						transactions.get(i).printBillRange();
					}

				} catch (Exception e) {
					System.out.println("Verify customer SSN!");
				}
			}
		}
	}

	public void viewTransactionsInDateRange() throws Exception {

		TransactionDAOImplementation betweenDates = new TransactionDAOImplementation();

		while (true) {

			String first = "";
			String second = "";
			System.out.println("\n      Press ENTER to fetch transaction range");
			System.out.println("\n                      Q to quit   \n        ");
			String s = sc.nextLine();

			if (s.equals("")) {
				try {

					System.out.println();
					System.out.println("            Enter _____CUSTOMER SSN #________   ");
					String ssn = sc.next();
					System.out.println("");
					System.out.println("      ====================================");
					System.out.println("      | Required DATE format: YYYY-MM-DD |");
					System.out.println("      ====================================");
					System.out.println("");

					System.out.println("      Enter______First  Date String____   ");
					String temp = sc.next();
					if (validateDateFormat(temp)) {
						first = temp;
					} else {
						System.out.println("      Wrong DATE Format!");
						continue;
					}

					System.out.println("      Enter______Second Date String____   ");
					temp = sc.next();
					if (validateDateFormat(temp)) {
						second = temp;
					} else {
						System.out.println("      Date Not in Range!              ");
						continue;
					}
					System.out.println("");

					// TEST VALUES: 2018-02-12, 2018-03-13, 123452072
					List<Transaction> btwnDates = new LinkedList<Transaction>();
					btwnDates = betweenDates.viewSelectDates(first, second, ssn);
					btwnDates.get(1).printBillHeader();
					btwnDates.get(1).toString();
					for (int i = 0; i < btwnDates.size(); i++) {
						btwnDates.get(i).printBillRange();
					}
				} catch (Exception e) {
					System.out.println("      Wrong Date Range!!               ");
					continue;
				}

			}
			if (s.equalsIgnoreCase("q")) {
				System.out.println("            !!!  Program Exit  !!!         ");
				return;
			}
		}

	}

	public void viewTransactionsInAZipcode() {

		System.out.println("====================================");
		TransactionDAOImplementation zips = new TransactionDAOImplementation();
		String s = "";
		System.out.println(
				"           Press Enter  \n\n__To View Transactions In Zipcode__\n\n" + "          Q to quit!");
		System.out.println("====================================");
		while (true) {

			s = sc.nextLine();

			if (s.equals("")) {

				System.out.println("");
				System.out.println("   Enter Zipcode Number   ");
				int zip = sc.nextInt();
				System.out.println("   Enter Month (MM)       ");
				int month = sc.nextInt();
				System.out.println("   Enter Year (YYYY)      ");
				int year = sc.nextInt();
				System.out.println("");

				// test --> WHERE CUST_ZIP = 39120 AND MONTH = 2 AND YEAR = 2018
				List<modelClasses.Zipcodes> zipcodes = zips.byZipandDate(zip, month, year);
				Zipcodes zipcode = new Zipcodes();
				zipcode.printZipHeader();
				for (int i = 0; i < zipcodes.size(); i++) {
					zipcodes.get(i).printZipRange();
				}
				System.out.println("Total Number of Transactions   :: " + zipcodes.size() + "\n");
			}

			if (s.equalsIgnoreCase("q")) {
				System.out.println("      !!!  Program Exit  !!!");
				return;
			}
		}

	}

	public void viewCountbyTotalbyBranchesbyState() {

		TransactionDAOImplementation branches = new TransactionDAOImplementation();
		String state = null;

		do {
			System.out.println("===================================================================");
			System.out.println("=               ___ Enter State Abbreviation (**)___              =");
			System.out.println("=      to view Transactions for Branches in Zipcode by State__    =");
			System.out.println("=                      'Q' to quit!                               =");
			System.out.println("===================================================================");
			state = sc.nextLine();
			System.out.println("\n");
			if(validateState(state)) {
			System.out.println("You entered " + state.toUpperCase() + " ");
			System.out.println(branches.totalsByBranch(state.toUpperCase()));}
			else {System.out.println("Wrong STATE abbreviations\n");}

		} while (!state.equalsIgnoreCase("q"));
		System.out.println("Program Exit....");
	}

	// Some validation methods below

	public static String toTitleCase(String input) {

		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			}
			titleCase.append(c);
		}
		return titleCase.toString();
	}

	public static boolean validateZipcode(String s) {

		String regexStr = "^[0-9]{5}?$";
		Pattern pattern = Pattern.compile(regexStr);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean validatePhoneNumber(String s) {

		String regexStr = "^\\d{7}$";
		return s.matches(regexStr);
	}

	public static boolean validateEmail(String s) {

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean validateSSN(int ssn) {

		return (int) (Math.log10(ssn) + 1) == 9;
	}

	public static boolean validateDateFormat(String s) {

		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	public static boolean validateMonth(int num) {

		Set<Integer> foo = new HashSet<Integer>();
		foo.add(1);
		foo.add(2);
		foo.add(3);
		foo.add(4);
		foo.add(5);
		foo.add(6);
		foo.add(7);
		foo.add(8);
		foo.add(9);
		foo.add(10);
		foo.add(11);
		foo.add(12);
		return foo.contains(num);

	}

	public static boolean validateState( String state )
	   {
	      return state.matches( "([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)" ) ;
	   } 
}
