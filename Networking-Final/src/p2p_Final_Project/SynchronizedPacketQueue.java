package p2p_Final_Project;

import java.net.DatagramPacket;

public abstract class SynchronizedPacketQueue extends SynchronizedLinkedListQueue
{
	/*
	  Josh Greenwell
	  CISC 370 Networking
	  4/9/15

	  This class is a synchronized version of LinkedListQueue

	 Class Variables:

	 Constructor:
	  public SynchronizedPacketQueue()

	 Methods:
	  public synchronized Object peek()
	   returns the data in the front node

	  public synchronized Object deQueue()
	   removes the front element of the queue and
	   returns it's data

	  public synchronized void enQueue(DatagramPacket data)
	   adds a node to the end of the queue

	  public synchronized void enQueue(SynchronizedPacketQueue queue)
	   Adds a queue to the end of the current queue

	 Modification History:
	  March 19, 2015
	   Original Version

	  */
	
	public SynchronizedPacketQueue() { super(); }
	
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

	 public synchronized void enQueue(DatagramPacket data)
	 {
	   //adds a node to the end of the queue
	   super.enQueue(data);
	 }

	 public synchronized void enQueue(SynchronizedPacketQueue queue)
	 {
	   //adds a queue to the end of the queue
	   super.enQueue(queue);
	 }
}
