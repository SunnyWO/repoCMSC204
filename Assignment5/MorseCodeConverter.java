import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MorseCodeConverter {
	//returns a string with all the data in the tree in LNR order with an space in between them
	public static String printTree() {
		MorseCodeTree tree = new MorseCodeTree();
		ArrayList array = tree.toArrayList();
		String result="";
		for(Object a : array) {
			result+=a+" ";
		}
		return result;
	}
	
	//Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘)
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		StringBuilder strBuild = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(codeFile))) 
        {
            String line;
            while ((line=br.readLine()) != null) 
            {
            	strBuild.append(line).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		
        String code = strBuild.toString();
        code.trim();
        code = code.replace("\n", "");
        System.out.println(code);

        return convertToEnglish(code);
	}
	
	//Converts Morse code into English
	public static String convertToEnglish(String code) {
		MorseCodeTree tree = new MorseCodeTree();
		ArrayList array = tree.toArrayList();

		
		if(code.equals("")||code.equals(" ")) {
			return "";
		}
		
		String[] process;
		process=code.split(" ");
		
		
		System.out.println("before");
		for(String a : process) {
			System.out.println(a);
		}
		System.out.println("after");
		
		System.out.println("{"+process[process.length-1]+"}");

		
		String returnString="";
		for(int i=0;i<process.length;i++) {
			if(process[i]==null||process[i]==""||process[i]==" ") {
				continue;
			}
			if(process[i].equals("/")) {
				returnString+=" ";
			}
			else {
				returnString+=tree.fetch(process[i]);
			}
		}
		return returnString;
	}

}
