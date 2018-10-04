package interfaces;

import java.util.List;

import modelClasses.Branches;
import modelClasses.Transaction;
import modelClasses.Zipcodes;

public interface TransactionDAOInterface {

	public List<Zipcodes> byZipandDate(int ZipCode, int month, int year);

	public Branches totalsByBranch(String state);

	public List<Transaction> viewSelectDates(String startDate, String endDate, String ssn);

	public void groupByTransactionType(String s);

	public List<Transaction> getMonthlyBill(int month, int year, int ssn);

	// haribabaaaaa!!!!
}
