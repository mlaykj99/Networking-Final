package p2p_Final_Project;

import java.security.SecureRandom;

public class ID
{
	/*
	  Josh Greenwell
	  CISC 370 Networking
	  4/9/15

	  This class creates ID objects for packet ID's

	 Class Variables:
		private static int idLengthInBytes
			The max number of bytes for ids
			
		private static LinkedListQueue idQueue
			Holds ids that can be used
			
		private static int maxQueueLength
			Maximum number of ids waiting to be used
			
		private static int queueLength
			The current number of ids held in the queue
			
		private static SecureRandom secureRandom
			The id random generator
		
		private static ID zeroID
			Zero id used for joins
			
		private byte[] id
			the id as a byte array
		
	 Constructor:
		private ID()
		  	creates an id from random generation
		  
		public ID(byte[] byteArray)
		  	creates a new id with the byte array

	 Methods:
	  	public static ID idFactory()
	
		private static ID createZeroID()
		
		public static void generateID()
		
		public static int getIDLength()
		
		private static LinkedListQueue getQueue()
		
		public static int getMaxQueueLength()
		
		public static int getQueueLength()
		
		public static ID getZeroID()
		
		public static void setIDLength(int lengthInBytes)
		
		public static void setMaxQueueLength(int length)
		
		public byte[] getBytes()
		
		public boolean equals(Object other)
		
		public int hashCode()
		
		public String toString()

	 Modification History:
	  March 9, 2015
	   Original Version

	  */
	
	private static int idLengthInBytes;
	private static LinkedListQueue idQueue;
	private static int maxQueueLength;
	private static int queueLength;
	private static SecureRandom secureRandom;
	private static ID zeroID;
	private byte[] id;
	
	
	private ID()
	{
		byte[] newID;
		
		//Set the size of the array to the length of each ID
		newID = new byte[getIDLength()];
		//Then make sure there is an ID in the queue to be used
		if(getQueue().isEmpty()) { generateID(); }
		
		//TODO: set up byte array based on queue ID
		
		this.id = newID.clone();
	}
	
	public ID(byte[] byteArray)
	{
		if( byteArray == null ) { throw new IllegalArgumentException("ID.constructor: byteArray is null!"); }
		if( byteArray.length != getIDLength() ) { throw new IllegalArgumentException("ID.constructor: byteArray is an incorrect length! Current Length: "
																							+ byteArray.length + " | Correct Length: " + getIDLength()); }
		this.id = byteArray.clone();
	}
	
	public static ID idFactory()
	{
		
	}
	
	private static ID createZeroID() { return new ID( new byte[getIDLength()] ); }
	
	public static void generateID()
	{
		if(getQueueLength() < getMaxQueueLength())
		{
			//TODO: Check the secureRandom!
			getQueue().enQueue(secureRandom.next(getIDLength()));
		}
	}
	
	public static int getIDLength() { return idLengthInBytes; }
	
	private static LinkedListQueue getQueue() { return idQueue; }
	
	public static int getMaxQueueLength() { return maxQueueLength; }
	
	public static int getQueueLength() { return queueLength; }
	
	public static ID getZeroID() { return zeroID; }
	
	public static void setIDLength(int lengthInBytes)
	{
		if(lengthInBytes < 1) { throw new IllegalArgumentException("ID.setIDLength: lengthInBytes is less than 1!"); }
		
		idLengthInBytes = lengthInBytes;
	}
	
	public static void setMaxQueueLength(int length)
	{ 
		if(length < 1) { throw new IllegalArgumentException("ID.setMaxQueueLength: length is less than 1!"); }
		
		maxQueueLength = length;
	}
	
	public byte[] getBytes() { return this.id.clone(); }
	
	public boolean equals(Object other) {}
	
	public int hashCode() {}
	
	public String toString() {}
}
