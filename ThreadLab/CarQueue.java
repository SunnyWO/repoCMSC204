import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class CarQueue {
	
	
	Random direc;
	Queue<Integer> theQueue;

	CarQueue(){
		theQueue = new ArrayDeque<Integer>();
		direc = new Random();
		
		//five random ints from 0-3
		theQueue.add(direc.nextInt(4));
		theQueue.add(direc.nextInt(4));
		theQueue.add(direc.nextInt(4));
		theQueue.add(direc.nextInt(4));
		theQueue.add(direc.nextInt(4));
		theQueue.add(direc.nextInt(4));
		
	}
	
	public void addToQueue(){ 
		class runnableQueue implements Runnable {

			@Override
			public void run() {
				try {
					while(true) {
						theQueue.add(direc.nextInt(4));
						Thread.sleep(10);
					}
				}
				catch(InterruptedException exception) {
					System.out.println("bruh");
				}
				finally {
					
				}

			}
			
		}
		
		Runnable r = new runnableQueue();
		Thread t = new Thread(r);
		t.start();
		
	}
	
	//returns an Inetger
	public int deleteQueue() {
		return theQueue.remove();
	}
}
