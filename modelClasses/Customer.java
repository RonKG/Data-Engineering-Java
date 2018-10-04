package modelClasses;

public class Customer{

	private String firstName;
	private String middleName;
	private String lastName;
	private int ssn;
	private String ccNumber;
	private String aptNumber;
	private String streetName;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private int phoneNumber;
	private String email;
	private String lastUpdated;
	
	
	public Customer() {
		super();
	}
	
	
	public Customer(String firstName, String middleName, String lastName, Integer ssn, String ccNumber, String aptNumber,
			String streetName, String city, String state, String country, String zipcode, int phoneNumber, String email,
			String lastUpdated) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.ccNumber = ccNumber;
		this.aptNumber = aptNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.lastUpdated = lastUpdated;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getAptNumber() {
		return aptNumber;
	}
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


	@Override
	public String toString() {
		return "Customer [\n"+ 
				"\nfirstName       = " + firstName + 
				"\nmiddleName      = " + middleName + 
				"\nlastName        = " + lastName + 
				"\nssn             = " + ssn + 
				"\nccNumber        = " + ccNumber + 
				"\naptNumber       = " + aptNumber + 
				"\nstreetName      = " + streetName + 
				"\ncity            = " + city + 
				"\nstate           = " + state + 
				"\ncountry         = " + country + 
				"\nzipcode         = " + zipcode + 
				"\nphoneNumber     = " + phoneNumber + 
				"\nemail           = " + email + 
				"\nlastUpdated     = " + lastUpdated + "\n]";
	}
}
