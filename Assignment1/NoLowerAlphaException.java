
public class NoLowerAlphaException extends Exception {
	
	public NoLowerAlphaException() {
		super();
		System.out.println("The password must contain at least one lowercase alphabetic character");
	}

}
