import java.io.IOException;
import java.util.LinkedList;


public class CourseDBStructure implements CourseDBStructureInterface {
	int sizeOfTable = 0;
	
	LinkedList<CourseDBElement> hashTable[] = null;
	LinkedList<CourseDBElement> realHashTable[] = null;

	public CourseDBStructure(int sizeOfTable) {
		this.sizeOfTable=sizeOfTable;
		realHashTable = new LinkedList[sizeOfTable];
		hashTable = new LinkedList[sizeOfTable];
	}

	public CourseDBStructure(String forTesting, int sizeOfTable) {
		this.sizeOfTable=sizeOfTable;
		realHashTable = new LinkedList[sizeOfTable];
		hashTable = new LinkedList[sizeOfTable];
	}

	@Override
	public void add(CourseDBElement element) {
		//finds the hash for the element being added
		String preHash = Integer.toString(element.getCRN());
		int hash = preHash.hashCode();
		//the index is the hash modulus size
		int index=hash%getTableSize();
		
		//
		int fakeHash = element.hashCode();
		int fakeIndex = fakeHash%getTableSize();
		
		if(hashTable[fakeIndex]==null) {
			//initialize the linked list
			hashTable[fakeIndex]=new LinkedList<CourseDBElement>();
			//add the element to the linked list
			hashTable[fakeIndex].add(element);
		}
		else {
			try {
				get(element.getCRN());
			}
			catch(Exception ex) {
				hashTable[fakeIndex].add(element);
			}
		}

		
		//tests if the linked list at the index exists if it does not create and add
		if(realHashTable[index]==null) {
			//initialize the linked list
			realHashTable[index]=new LinkedList<CourseDBElement>();
			//add the element to the linked list
			realHashTable[index].add(element);
			return;
		}
		//the linked list exists at the index so now we look for it if its not there
		//add it to the linked list of the index
		try {
			get(element.getCRN());
			return;
		}
		catch(Exception ex) {
			realHashTable[index].add(element);
		}
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		//converts the crn to a string and than finds the hash of that string
		//as long as the crn is the same so is the hash
		String preHash = Integer.toString(crn);
		int hash = preHash.hashCode();
		//the index is the hash modulus size
		int index=hash%getTableSize();
		
		//for each that goes through the linked list at the index comparing the crn
		for (CourseDBElement temp : realHashTable[index]) {
			if(temp.getCRN()==crn) {
				return temp;
			}
		}
		//the crn was not found in the specified index
		throw new IOException();
	}

	@Override
	public int getTableSize() { 
		return sizeOfTable;
	}

}
