
public class CourseDBElement implements Comparable {
	String courseID;
	int CRN;
	int numCredits;
	String roomNum;
	String instructorName;
	
	CourseDBElement(){
		
	}
	
	public CourseDBElement(String courseID, int CRN, int numCredits, String roomNum, String instructorName) {
		this.courseID=courseID;
		this.CRN=CRN;
		this.numCredits=numCredits;
		this.roomNum=roomNum;
		this.instructorName=instructorName;

	}

	@Override
	public int compareTo(CourseDBElement element) {
//		if( this.compareTo(element) < 0) {
//			return -1;
//		}
//		else if(this.compareTo(element) == 0) {
//		return 0;
//		}
//		else {
//			return -1;
//		}
		return 0;
	}
	

	public void setCRN(int CRN) {
		this.CRN=CRN;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getNumCredits() {
		return numCredits;
	}

	public void setNumCredits(int numCredits) {
		this.numCredits = numCredits;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public int getCRN() {
		return CRN;
	}


}
