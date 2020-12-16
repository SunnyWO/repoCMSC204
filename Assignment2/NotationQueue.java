import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {
	private int size;
	private T[] queueArray;
	private int front;
	private static int DEFAULT_SIZE = 10;
	
	
	@SuppressWarnings({ "unchecked" })
	public NotationQueue(int queueSize) {
		queueArray = (T[]) new Object[queueSize];
		front=0;
		
	}
	
	@SuppressWarnings({ "unchecked" })
	public NotationQueue() {
		this.size=DEFAULT_SIZE;
		queueArray = (T[]) new Object[DEFAULT_SIZE];
	}
	
	@Override
	public boolean isEmpty() {
		if(queueArray[0]==null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(queueArray[queueArray.length-1]!=null) {
			return true;
		}
		return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T returnFrontElement = null;
		//if empty throws empty exep
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		//saves the front element, deletes from queue,
		//and lowers the front/size
		else {
			front--;
			size--;
			returnFrontElement = queueArray[front];
			queueArray[front]=null;
			
			
		}
		return returnFrontElement;
	}

	@Override
	public int size() {
		return (size);
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		//if Full throws Full exep
		if(isFull()) {
			throw new QueueOverflowException();
		}
		else {
			front++;
			size++;
			for (int i=front-1;i>0;i--) { 
				queueArray[i] = queueArray[i-1];
            }
			queueArray[0]=e;
			
		}
		return true;
	}
	
	@Override
	public String toString() {
		String elements = "";
		for (int i=size-1;i>=0;i--) { 
			elements+=queueArray[i];
        }
		return elements;
		
	}
	
	@Override
	public String toString(String delimiter) {
		String elements = "";
		for (int i=size-1;i>=0;i--) { 
			elements+=queueArray[i];
			if(i!=0) {
				elements+=delimiter;	
			}
        }
		return elements;
	}

	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> deepCopyList = new ArrayList<T>();
		for(int i=0;i<list.size();i++) {
			deepCopyList.add(i, list.get(i));
		}
		for(int i=0;i<deepCopyList.size();i++) {
			queueArray[queueArray.length-i-deepCopyList.size()]=deepCopyList.get(i);
			front++;
			size++;
		}
	}

}
