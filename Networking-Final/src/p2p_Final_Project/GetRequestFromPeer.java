package p2p_Final_Project;

import java.net.DatagramPacket;

public class GetRequestFromPeer extends RequestFromPeer implements Runnable {

	public GetRequestFromPeer(UDPMessage message , OutgoingPacketQueue outgoing) //Add reference to queue from peer controller when constructed as instance var
	{
		super(message,outgoing);
	}

	@Override
	public void run() 
	{
		// TODO create packet with requested part info
		//place in outgoing peer queue
		ResourceManager rm = ResourceManager.getInstance();
		UDPMessage packet;
		Resource resource;
		int partNumberAsInt = 0;
		int resourceIDAsInt;
		ID requestID;
		ID resourceID;
		TimeToLive timeToLive;
		byte[] randomID;
		byte[] partNumber;
		byte[] bytesFromResource;
		byte[] message;
		
		requestID = this.getUDPMessage().getId1();
		resourceID = this.getUDPMessage().getId2();
		resource = rm.getResourceByID(resourceID);
		timeToLive = new TimeToLive(Utilities.randomInt());
		randomID = ID.idFactory().getBytes();
		partNumber = getPartNumber();
		for(int i = 0; i < partNumber.length;i++)
		{
			partNumberAsInt = (partNumberAsInt << (8*(3-i))) | partNumber[i];
		}
		bytesFromResource = resource.getBytes(partNumberAsInt);

		message = Utilities.arrayCopy(Utilities.arrayCopy(randomID,partNumber),bytesFromResource);
		packet = new UDPMessage(resourceID,requestID,timeToLive,message);
		
		GossipPartners.getInstance().send(packet);

	}
	
	
	
	private byte[] getPartNumber()
	{
		byte[] message = this.getUDPMessage().getMessage();
		byte[] partNumber;
		
		partNumber = new byte[message.length-ID.getIDLength()];
		
		System.arraycopy(partNumber,0,message,ID.getIDLength(),message.length-ID.getIDLength());
		
		return partNumber;
	}


}
