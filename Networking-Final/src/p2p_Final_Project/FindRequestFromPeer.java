package p2p_Final_Project;

import java.net.DatagramPacket;

public class FindRequestFromPeer extends RequestFromPeer implements Runnable {

	private byte[] delimeter;
	
	public FindRequestFromPeer(UDPMessage message, OutgoingPacketQueue outgoing) //Add reference to queue from peer controller when constructed as instance var
	{
		super(message,outgoing);
		delimeter = ",".getBytes();
	}

	@Override
	public void run()
	{
		
		//TODO: resource manager - calls newInstance and getResourcesThatMatch( new String(message.getMessage) )
		//loop for each resource - create a packet
		//Push to outgoingPacketQueue
		ResourceManager rm;
		OutgoingPacketQueue outgoing;
		UDPMessage request;
		DatagramPacket packet;
		ID originatingID;
		ID resourceID;
		TimeToLive timeToLive;
		byte[] randomID;
		byte[] resourceLength;
		byte[] mimeType;
		byte[] description;
		byte[] delimeter;
		Resource[] resourcesThatMatch;
		byte[] response;
		
		outgoing = this.getOutgoingPacketQueue();
		request = this.getUDPMessage();
		originatingID = request.getId1();
		System.out.println(originatingID + " the originating ID");
		rm = ResourceManager.newInstance();
		System.out.println("Searching for: "+new String (getUDPMessage().getMessage(),0,getUDPMessage().getMessage().length));
		resourcesThatMatch = rm.getResourcesThatMatch(new String (getUDPMessage().getMessage(),0,getUDPMessage().getMessage().length));
		
		for(int i = 0; i < resourcesThatMatch.length;i++)
		{
			System.out.println("Found a resource.");
			resourceID = resourcesThatMatch[i].getResourceID();
			timeToLive = (new TimeToLive(Utilities.randomInt()));
			randomID = ID.idFactory().getBytes();
			delimeter = this.delimeter;
			mimeType = resourcesThatMatch[i].getMimeType().getBytes();
			resourceLength = Utilities.longToBytes(resourcesThatMatch[i].getSizeInBytes());
			description = resourcesThatMatch[i].getDescription().getBytes();
			System.out.println("Created the response.");
			response = Utilities.arrayCopy(Utilities.arrayCopy( randomID,delimeter),mimeType,resourceLength,description);
			request = new UDPMessage(resourceID, originatingID, timeToLive,response);
			GossipPartners.newInstance().send(request);
			System.out.println("Response put in queue.");
		}
	}
}
