import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test the methods of PasswordChecker
 * @author Sunny
 *
 */
public class PasswordCheckerUtility {
	public PasswordCheckerUtility() {
		
	}
	
	public static boolean isValidPassword(String passwordString) throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException {
		
		//checks if passwordString is at least 6 characters
		if(passwordString.length()<6) {
			throw new LengthException();
		}
		
		//checks if password has an upper case, lower case, and digit character 
		int zeroIfNoUpperCase = 0;
		int zeroIfNoLowerCase = 0;
		int zeroIfNoDigit = 0;
		for(int i=1;i<passwordString.length();i++) {
			char ch = passwordString.charAt(i);
			if(!(Character.isLowerCase(ch))) {
				zeroIfNoUpperCase++;
			}
			if(Character.isLowerCase(ch)) {
				zeroIfNoLowerCase++;
			}
			if(Character.isDigit(ch)) {
				zeroIfNoDigit++;
			}
		}
		
		//try/catch for NoUpperAlphaException
		if(zeroIfNoUpperCase==0) {
			throw new NoUpperAlphaException();
		}
		
		//try/catch for NoLowerAlphaException
		if(zeroIfNoLowerCase==0) {
			throw new NoLowerAlphaException();
		}
		
		//try/catch for NoDigitException
		if(zeroIfNoDigit==0) {
			throw new NoDigitException();
		}
		
		//checks if password has a special character
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(passwordString);
		if(matcher.matches()) {
			throw new NoSpecialCharacterException();
		}
		
		//checks if password has 2 of the same char in sequence
		int zeroIfNoInvalidSequence = 0;
		for(int i=1;i<passwordString.length()-1;i++) {
			char ch1 = passwordString.charAt(i);
			char ch2 = passwordString.charAt(i+1);
			if(ch1==ch2) {
				zeroIfNoInvalidSequence++;
			}
		}
		
		//try/catch for InvalidSequenceException
		if(zeroIfNoInvalidSequence!=0) {
			throw new InvalidSequenceException();
		}	
		
		//password passed all tests
		return true;
	}
	
	public static boolean isWeakPassword(String passwordString) {
		if(passwordString.length()>=6&&passwordString.length()<9) {
			return true;
		}
		else
		return false;
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> InvalidPasswords = new ArrayList<String>();
		for(int i=0;i<passwords.size();i++) {
			try {
				if(isValidPassword(passwords.get(i))) {
				}
			} catch (Exception e) {
				InvalidPasswords.add(passwords.get(i));
			} 
		}
		return InvalidPasswords;
	}
	
}
