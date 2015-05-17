package p2p_Final_Project;

import java.net.DatagramPacket;

public class FindRequestFromPeer extends RequestFromPeer implements Runnable {

	private String delimeter;
	
	public FindRequestFromPeer(UDPMessage message, OutgoingPacketQueue outgoing) //Add reference to queue from peer controller when constructed as instance var
	{
		super(message,outgoing);
		delimeter = ",";
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
		byte[] originatingID;
		byte[] resourceID;
		byte[] timeToLive;
		byte[] randomID;
		byte[] resourceLength;
		byte[] mimeType;
		byte[] description;
		byte[] delimeter;
		Resource[] resourcesThatMatch;
		byte[] response;
		
		outgoing = this.getOutgoingPacketQueue();
		request = this.getUDPMessage();
		originatingID = request.getId1().getBytes();
		
		rm = ResourceManager.newInstance();
		System.out.println("Searching for: "+new String (getUDPMessage().getMessage(),0,getUDPMessage().getMessage().length));
		resourcesThatMatch = rm.getResourcesThatMatch(new String (getUDPMessage().getMessage(),0,getUDPMessage().getMessage().length));
		
		for(int i = 0; i < resourcesThatMatch.length;i++)
		{
			System.out.println("Found a resource.");
			resourceID = resourcesThatMatch[i].getResourceID().getBytes();
			timeToLive = (new TimeToLive(Utilities.randomInt()).toByteArray());
			randomID = ID.idFactory().getBytes();
			delimeter = this.delimeter.getBytes();
			mimeType = resourcesThatMatch[i].getMimeType().getBytes();
			resourceLength = Utilities.longToBytes(resourcesThatMatch[i].getSizeInBytes());
			description = resourcesThatMatch[i].getDescription().getBytes();
			System.out.println("Created the response.");
			response = Utilities.arrayCopy(Utilities.arrayCopy(resourceID, originatingID, timeToLive, randomID),delimeter,mimeType,resourceLength,description);
			packet = new DatagramPacket(response,response.length);
			outgoing.enQueue(packet);
			System.out.println("Response put in queue.");
		}
	}
}
