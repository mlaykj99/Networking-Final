package p2p_Final_Project;

public class PartNumbers {
	private int numberOfParts;
	private int numberOfMissingParts;
	
	public PartNumbers(int numberOfParts)
	{
		if(numberOfParts < 0)
		{
			throw new IllegalArgumentException("Error: number of parts in a resource cannot be 0 or less. ");
		}
		this.numberOfParts = numberOfParts;
		this.numberOfMissingParts = numberOfParts;
	}
	public int get()
	{
		return this.numberOfParts;
	}
	public byte[] getBytes()
	{
		byte[] bytes = new byte[4];
		for (int i = 0; i < 4; i++) {
		    bytes[i] = (byte)(numberOfParts >>> (i * 8));
		}
		
		return bytes;
	}
	public int getLengthInBytes()
	{
		return 4;
	}
	public int numberOfMissingParts()
	{
		return numberOfMissingParts;
	}
}
