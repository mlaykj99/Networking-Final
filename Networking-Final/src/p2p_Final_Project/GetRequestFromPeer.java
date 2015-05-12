package p2p_Final_Project;

public class GetRequestFromPeer extends RequestFromPeer implements Runnable {

	public GetRequestFromPeer(UDPMessage message) //Add reference to queue from peer controller when constructed as instance var
	{
		super(message);
	}

	@Override
	public void run() 
	{
		// TODO create packet with requested part info
		//place in outgoing peer queue

	}

}
