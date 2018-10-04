package modelClasses;

public class Transaction {

	private Integer transactionId;
	private Integer day;
	private Integer month;
	private Integer year;
	private String ccNumber;
	private Integer ssn;
	private Integer branchCode;
	private String transactiontype;
	private Float transactionValue;	


	public Transaction() {
		super();
	}
	
	public Transaction(Integer transactionId, Integer day, Integer month, Integer year, String ccNumber, Integer ssn,
			Integer branchCode, String transactiontype, Float transactionValue) {
		super();
		this.transactionId = transactionId;
		this.day = day;
		this.month = month;
		this.year = year;
		this.ccNumber = ccNumber;
		this.ssn = ssn;
		this.branchCode = branchCode;
		this.transactiontype = transactiontype;
		this.transactionValue = transactionValue;		
	}
	
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
	public Integer getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public Float getTransactionValue() {
		return transactionValue;
	}
	public void setTransactionValue(Float transactionValue) {
		this.transactionValue = transactionValue;
	}
	
	public String printMonthBill() {
		return " [transactionId=" + transactionId + 
				", day=" + day + 
				", month=" + month + 
				", year=" + year + 
				", ccNumber=" + ccNumber + 
				", transactiontype=" + transactiontype + 
				", transactionValue=" + transactionValue + "]";
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", day=" + day + ", month=" + month + ", year=" + year
				+ ", ccNumber=" + ccNumber + ", ssn=" + ssn + ", branchCode=" + branchCode + ", transactiontype="
				+ transactiontype + ", transactionValue=" + transactionValue + "]\n";
	}
	
	public void printBillRange() {
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
							transactionId,day,month,year,ccNumber,transactiontype,numFormatter(transactionValue));

	}
	
	public void printBillHeader() {
		System.out.printf("\n%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n","transactionId","day","month"
				,"year","ccNumber","transactiontype","transactionValue"+
				"\n=============================================================================="+
				"===========================================================");
		
	}
	
	public String numFormatter(double d) {
		return String.format("%.2f", d);
	}
}
