
public class ArraySum {
	
	public int sumOfArray (Integer[] a,int index) {
		if(index>=1) {
			return a[index]+sumOfArray(a,index-1);
		}
		else {
			return a[index];
		}
		
	}

}
