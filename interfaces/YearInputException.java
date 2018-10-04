package interfaces;

public class YearInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1324250058016380960L;

	public YearInputException(String exceptionMsg) {
		System.out.println("Invalid: " + exceptionMsg);
	}

}
