import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFileChooser;

public class CourseDBManager implements CourseDBManagerInterface {
	CourseDBStructure hashTableInstance = new CourseDBStructure(20);

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id,crn,credits,roomNum,instructor);
		hashTableInstance.add(element);

	}

	@Override
	public CourseDBElement get(int crn) {
		CourseDBElement element=null;
		try {
			element = hashTableInstance.get(crn);
		} catch (IOException e) {
	
		}
		return element;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException{
		BufferedReader br = null;
			br = new BufferedReader(new FileReader(input));
		
		String st; 
		try {
			while ((st = br.readLine()) != null) { 
	//System.out.println(st); 
				//every string of chars seperated by a space is an element
				String[] splitUp = st.split("\\s");
				
				//courseID, CRN, numCredits, and roomNum are the first 4 indexes
				String courseID = splitUp[0];
				int CRN = Integer.valueOf(splitUp[1]);
				int numCredits = Integer.valueOf(splitUp[2]);
				String roomNum = splitUp[3];
				//the name is every index after the first 4
				String name = "";
				for(int i=4;i<splitUp.length-1;i++) {
					name+=splitUp[i]+" ";
				}
				name+=splitUp[splitUp.length-1];
				add(courseID, CRN, numCredits, roomNum, name);
			}
		} catch (IOException e) {
		} 
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> returnArray = new ArrayList<String>();
		
		for (LinkedList<CourseDBElement> a : hashTableInstance.realHashTable) {
			if(a==null) {
				continue;
			}
			else {
				for (CourseDBElement temp : a) {
					returnArray.add("\nCourse:"+temp.getCourseID()+" CRN:"+temp.getCRN()
					+" Credits:"+temp.getNumCredits()+" Instructor:"+temp.getInstructorName()
					+" Room:"+temp.getRoomNum());
				}
			}
			
		}		
		return returnArray;
	}

}
