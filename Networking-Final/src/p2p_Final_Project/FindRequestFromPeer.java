package p2p_Final_Project;

import java.net.DatagramPacket;

public class FindRequestFromPeer extends RequestFromPeer implements Runnable {

	private String delimeter;
	
	public FindRequestFromPeer(UDPMessage message, OutgoingPacketQueue outgoing) //Add reference to queue from peer controller when constructed as instance var
	{
		super(message,outgoing);
		delimeter = "\t";
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
		long resourceLength;
		String mimeType;
		String description;
		String delimeter;
		Resource[] resourcesThatMatch;
		byte[] response;
		
		outgoing = this.getOutgoingPacketQueue();
		request = this.getUDPMessage();
		originatingID = request.getId1();
		//System.out.println(originatingID + " the originating ID");
		rm = ResourceManager.newInstance();
		//System.out.println("Searching for: "+new String (getUDPMessage().getMessage(),0,getUDPMessage().getMessage().length));
		resourcesThatMatch = rm.getResourcesThatMatch(new String (getUDPMessage().getMessage(),0,getUDPMessage().getMessage().length));
		//System.out.println("# of matching resources: "+resourcesThatMatch.length);
		for(int i = 0; i < resourcesThatMatch.length;i++)
		{
			//System.out.println("Found a resource.");
			//System.out.println(resourcesThatMatch[i].getResourceID());
			resourceID = resourcesThatMatch[i].getResourceID();
			
			randomID = ID.idFactory().getBytes();
			delimeter = this.delimeter;
			mimeType = resourcesThatMatch[i].getMimeType();
			resourceLength = resourcesThatMatch[i].getSizeInBytes();

			description = resourcesThatMatch[i].getDescription();

			//System.out.println("Created the response.");
			response = (delimeter + mimeType + delimeter + resourceLength + delimeter + description).getBytes();
			//System.out.println(new String(response,0,response.length));
			response = Utilities.arrayCopy(randomID,response);
			
			
			/*
			try {
				Thread.sleep(100000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			request = new UDPMessage(resourceID, originatingID, new TimeToLive(),response);
			
			
			//System.out.println(originatingID);
			//System.out.println(request.getId2());
			GossipPartners.newInstance().send(request);
			//System.out.println("Response put in queue.");
		}
	}
}
