package p2p_Final_Project;

public abstract class RequestFromPeer implements Runnable{
	private UDPMessage udpMessage;
	public RequestFromPeer(UDPMessage message)
	{
		this.udpMessage = message;
	}
	public UDPMessage getUDPMessage()
	{
		return this.udpMessage;
	}
	public abstract void run();
}
