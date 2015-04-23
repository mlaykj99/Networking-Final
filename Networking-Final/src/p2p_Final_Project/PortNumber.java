package p2p_Final_Project;

public class PortNumber {
	private int portNumber;
	
	public PortNumber(int portNumber)
	{
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
