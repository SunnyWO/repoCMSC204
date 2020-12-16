import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	private T[] stackArray;
	private int topIndex;
	private int size;
	private static int DEFAULT_CAPACITY = 10;
	
	@SuppressWarnings("unchecked")
	public NotationStack(int size) {
		this.size=size;
		stackArray = (T[]) new Object[size];
		topIndex=-1;
		
	}
	@SuppressWarnings("unchecked")
	public NotationStack() {
		this.size=DEFAULT_CAPACITY;
		stackArray = (T[]) new Object[DEFAULT_CAPACITY];
		topIndex=-1;
	}
	

	@Override
	public boolean isEmpty() {
		if(stackArray[0]==null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(stackArray[stackArray.length-1]!=null) {
			return true;
		}
		return false;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			T topElement = stackArray[topIndex];
			stackArray[topIndex] = null;
			topIndex--;
			return topElement;
		}
	}

	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		else {
			T topElement = stackArray[topIndex];
			return topElement;
		}
	}

	@Override
	public int size() {
		int size = topIndex+1;
		return size;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		try{
			stackArray[topIndex+1] = e;
			topIndex++;	
			return true;
		}
		catch(Exception StackOverflowException) {
			throw new StackOverflowException();
		}
	}

	@Override
	public String toString() {
		String elements = "";
		for(int i=0;i<=topIndex;i++) {
			elements+=stackArray[i];
		}
		return elements;
	}
	
	@Override
	public String toString(String delimiter) {
		String elements = "";
		for(int i=0;i<=topIndex;i++) {
			elements+=stackArray[i];
			//if its the last element we dont need a delimiter after
			if(i!=topIndex) {
				elements+=delimiter;	
			}
		}
		return elements;
	}

	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> deepCopyList = new ArrayList<T>();
//		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i));
//		}
		for(int i=0;i<list.size();i++) {
			deepCopyList.add(i, list.get(i));
		}
		for(int i=0;i<deepCopyList.size();i++) {
			stackArray[i]=deepCopyList.get(i);
			topIndex++;
		}
			
	}

}
