package modelClasses;

public class Zipcodes {
	public int id;
	public String fname;
	public String lname;
	public String state;
	public String cc;
	public String zip;
	public String date;

	/**
	 * @param id
	 * @param fname
	 * @param lname
	 * @param state
	 * @param cc
	 * @param zip
	 * @param date
	 */
	public Zipcodes(int id, String fname, String lname, String state, String cc, String zip, String date) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.state = state;
		this.cc = cc;
		this.zip = zip;
		this.date = date;
	}

	public Zipcodes() {
	}

	@Override
	public String toString() {
		return "Zipcodes [id=" + id + ", fname=" + fname + ", lname=" + lname + ", state=" + state + ", cc=" + cc
				+ ", zip=" + zip + ", date=" + date + "]";
	}

	public void printZipRange() {
		System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", id, fname, lname, state, cc, zip, date);

	}

	public void printZipHeader() {
		System.out.printf("\n%-20s%-20s%-20s%-20s%-20s\n", "transactionId", "firstName", "lastName", "state",
				"ccNumber", "zipcode", "date");
	}

	public String numFormatter(double d) {
		return String.format("%.2f", d);

	}
}
