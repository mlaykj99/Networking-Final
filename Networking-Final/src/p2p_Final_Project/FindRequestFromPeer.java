package p2p_Final_Project;

public class FindRequestFromPeer extends RequestFromPeer implements Runnable {

	public FindRequestFromPeer(UDPMessage message) //Add reference to queue from peer controller when constructed as instance var
	{
		super(message);
	}

	@Override
	public void run()
	{
		//TODO: resource manager - calls newInstance and getResourcesThatMatch( new String(message.getMessage) )
		//loop for each resource - create a packet
		//Push to outgoingPacketQueue
	}

}
