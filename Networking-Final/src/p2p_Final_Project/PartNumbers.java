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
	public int getBytes()
	{
		//TODO have on a sheet not currently with me 
		//Will do at a later time.
		return 0;
	}
}
