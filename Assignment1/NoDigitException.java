
public class NoDigitException extends Exception {
	
	public NoDigitException() {
		super();
		System.out.println("The password must contain at least one digit");
	}

}
