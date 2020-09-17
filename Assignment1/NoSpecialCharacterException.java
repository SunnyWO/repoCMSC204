
public class NoSpecialCharacterException extends Exception {
	
	public NoSpecialCharacterException() {
		super();
		System.out.println("The password must contain at least one special character");
	}

}
