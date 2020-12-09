import java.util.*;

public class Town implements Comparable<Town> {
	String townName;
	List<Town> adjacentList = new ArrayList<Town>();
	
	//constructor
	public Town(String name) {
		townName = name;
	}
	
	//copy constructor
	public Town(Town template) {
		townName = template.townName;
		adjacentList = template.adjacentList;
	}
	
	//0 if names are equal, 
	//a positive or negative number if the names are not equal
	@Override
	public int compareTo(Town o) {
		if(o.townName.equals(townName)) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	//true if the town names are equal, false if not
	@Override
	public boolean equals(Object obj) {
		
		try {
			//compares the hashcode of the obj and the townName of this towm
			if( ((Town) obj).getName().hashCode()==townName.hashCode()) {
				return true;
			}
			else {
				return false;
			}
		}
		//if the obj is not a town an exception will be thrown
		//and the super method will run instead
		catch(Exception e) {
			return super.equals(obj);
		}
		
		
		
	}
	
	//Returns the town's name
	public String getName() {
		return townName;
		
	}
	
	//the hashcode for the name of the town
//	
//	not sure if this is right
//	????????????????????????????????????????????????????????????????????????????????????????????															
//	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	//the town name
	@Override
	public String toString() {
		return getName();
	}
}
