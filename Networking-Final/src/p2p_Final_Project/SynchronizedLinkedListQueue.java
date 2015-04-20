package p2p_Final_Project;

public class SynchronizedLinkedListQueue extends LinkedListQueue
{
	/*
	  Josh Greenwell
	  CISC 370 Networking
	  4/9/15

	  This class is a synchronized version of LinkedListQueue

	 Class Variables:

	 Constructor:
	  public SynchronizedLinkedListQueue()

	 Methods:
	  public synchronized void removeAll()
	   sets the head and rear to null

	  public synchronized boolean isEmpty()
	   returns true if there is nothing in the queue

	  public synchronized Object peek()
	   returns the data in the front node

	  public synchronized Object deQueue()
	   removes the front element of the queue and
	   returns it's data

	  public synchronized void enQueue(Object data)
	   adds a node to the end of the queue

	  public synchronized void enQueue(LinkedListQueue queue)
	   Adds a queue to the end of the current queue

	 Modification History:
	  March 9, 2015
	   Original Version

	  */

	 public SynchronizedLinkedListQueue()
	 {
	   //Initializes variables
	   super();
	 }

	 public synchronized void removeAll()
	 {
	   //sets the rear and front to null
	   super.removeAll();
	 }

	 public synchronized boolean isEmpty(){return super.isEmpty();}

	 public synchronized Object peek()
	 {
	   Object value;
	  
	   //returns the front's data
	   if(this.isEmpty()){value = null;}
	   value = super.peek();
	  
	   return value;
	 }

	 public synchronized Object deQueue()
	 {
	   Object value;
		 
	  
	   //removes the front node and returns it's data
	   if(this.isEmpty()){value = null;}
	   value = super.deQueue();
	  
	   return value;
	 }

	 public synchronized void enQueue(Object data)
	 {
	   //adds a node to the end of the queue
	   super.enQueue(data);
	 }

	 public synchronized void enQueue(LinkedListQueue queue)
	 {
	   //adds a queue to the end of the queue
	   super.enQueue(queue);
	 }
}
