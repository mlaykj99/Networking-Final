package p2p_Final_Project;

public abstract class RequestFromPeer implements Runnable{
	private UDPMessage udpMessage;
	private OutgoingPacketQueue outgoing;
	public RequestFromPeer(UDPMessage message, OutgoingPacketQueue outgoing)
	{
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in "+this.getClass()+" is null.");
		}
		if(outgoing == null)
		{
			throw new IllegalArgumentException("Error: outgoing in " + this.getClass()+" is null.");
		}
		this.udpMessage = message;
		for(int i = 0;i < message.getMessage().length;i++)
		{
			System.out.println("Message at position "+ i + " in request constructor" + "" +message.getMessage()[i]);
		}
		this.outgoing = outgoing;
	}
	public OutgoingPacketQueue getOutgoingPacketQueue() {
		return this.outgoing;
	}
	public UDPMessage getUDPMessage()
	{
		return this.udpMessage;
	}
	public abstract void run();
}
