package interfaces;

public class UserInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676377984928067068L;

	public UserInputException(String exceptionMsg) {
		System.out.println("Invalid: " + exceptionMsg);
	}
}
