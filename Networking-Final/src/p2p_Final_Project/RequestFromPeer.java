package p2p_Final_Project;

public abstract class RequestFromPeer implements Runnable{
	private UDPMessage udpMessage;
	public RequestFromPeer(UDPMessage message)
	{
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in "+this.getClass()+" is null.");
		}
		this.udpMessage = message;
	}
	public UDPMessage getUDPMessage()
	{
		return this.udpMessage;
	}
	public abstract void run();
}
