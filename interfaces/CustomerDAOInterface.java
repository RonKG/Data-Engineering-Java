package interfaces;

import modelClasses.Customer;

public interface CustomerDAOInterface {

	public Customer getCustomer(int ssn);
	
	public void updateCustomerD(Customer customer);	
}
