
public class NoUpperAlphaException extends Exception {
	
	public NoUpperAlphaException() {
		super();
		System.out.println("The password must contain at least one uppercase alphabetic character");
	}

}
