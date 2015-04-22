package p2p_Final_Project;

public class TimeToLive
{
	private int timeToLive;
	
	public TimeToLive(int timeToLive)
	{
		this.timeToLive = timeToLive;
	}
	
	public TimeToLive(byte[] byteArray)
	{
		//timeToLive = byteArray[3] & 0xFF | (byteArray[2] & 0xFF) << 8 | (byteArray[1] & 0xFF) << 16 | (byteArray[0] & 0xFF) << 24;	
		for (int i = 0; i < byteArray.length; i++) {
		    timeToLive = timeToLive | (byteArray[i - (byteArray.length-1)] & 0xFF) << (i * 8);
		}
	}
	
	public static int getLengthInBytes()
	{
		return 4;
	}
	
	public int get() { return timeToLive; }
	public void set(int timeToLive) { this.timeToLive = timeToLive; }
	
	public byte[] toByteArray()
	{
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i++) {
		    bytes[i] = (byte)(timeToLive >>> (i * 8));
		}
		
		return bytes;
	}
	
	public String toString()
	{
		String result;
		byte[] bytes;
		
		result = "";
		bytes = toByteArray();
		for(int i = 0; i < bytes.length; i++)
		{
			result = result + "Byte " + i + ": " + bytes[i] + "\n";
		}
		
		return result;
	}
}
