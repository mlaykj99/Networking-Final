package p2p_Final_Project;

public class PortNumber {
	private int portNumber;
	
	public PortNumber(int portNumber)
	{
		if(portNumber < 2000)
		{
			throw new IllegalArgumentException("Error: portNumber for class "+this.getClass()+  " cannot be less 2000");
		}
		this.portNumber = portNumber;
	}
	public int get()
	{
		return this.portNumber;
	}
	public String toString()
	{
		return "This is a port number " + portNumber;
	}
}
