package p2p_Final_Project;

public class TimeToLive
{
	private int timeToLive;
	
	public TimeToLive()
	{
		this.timeToLive = ((int)(Math.random()*3))+3;
	}
	public TimeToLive(int timeToLive)
	{
		if( timeToLive < 1 ) { throw new IllegalArgumentException("TimeToLive.constructor: timeToLive is less then 1!"); }
		
		this.timeToLive = timeToLive;
	}
	
	public TimeToLive(byte[] byteArray)
	{
		if( byteArray == null ) { throw new IllegalArgumentException("TimeToLive.constructor: byteArray is null!"); }
		if( byteArray.length != 4 ) { throw new IllegalArgumentException("TimeToLive.constructor: byteArray is not size 4!"); }
		
		timeToLive = 0;
		
		//timeToLive = byteArray[0] & 0xFF | (byteArray[1] & 0xFF) << 8 | (byteArray[2] & 0xFF) << 16 | (byteArray[3] & 0xFF) << 24;	
		for (int i = 0; i < byteArray.length; i++) {
		    timeToLive = (timeToLive << (8*(3-i))) | byteArray[i];
		}
	}
	
	public static int getLengthInBytes() { return 4; }
	public int get() { return timeToLive; }
	
	public void set(int timeToLive)
	{ 
		//if( timeToLive < -1 ) { throw new IllegalArgumentException("TimeToLive.set: timeToLive is less then 1!"); }
		this.timeToLive = timeToLive; 
	}
	
	public byte[] toByteArray()
	{
		int x = this.timeToLive;
		byte[] bytes = new byte[4];
		for (int i = 3; i>=0;i--)
		{
			bytes[i] = (byte) (x & 0xFF);
			x = x >> 8;
		}
		
		return bytes;
	}
	
	public String toString()
	{
		return this.timeToLive+"";
	}
}
