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
		int partNumber;
		byte[] partNumAsBytes;
		byte[] bytesFromResource;
		byte[] message;
		int x;
		byte[] holdID = new byte[4];
		requestID = this.getUDPMessage().getId1();
		resourceID = this.getUDPMessage().getId2();
		resource = rm.getResourceByID(resourceID);
		timeToLive = new TimeToLive();
		partNumAsBytes = getPartNumber();
		partNumber = 0;
		
		for (int i = 0; i < partNumAsBytes.length; i++) {
		    partNumber = (partNumber << (8*(3-i))) | partNumAsBytes[i];
		}
		System.out.println("ResourceID");
		System.out.println(resourceID);
		bytesFromResource = resource.getBytes(partNumber);
		System.out.println("This is the partNumber: "+partNumber);
		message = (""+ID.idFactory() + partNumber+new String(bytesFromResource)).getBytes();
		packet = new UDPMessage(resourceID,requestID,timeToLive,message);
		System.out.println(timeToLive);
		System.arraycopy(message, 16, holdID, 0, 4);
		System.out.println(new String(holdID));
		GossipPartners.getInstance().send(packet);
	}
	
	
	
	private byte[] getPartNumber()
	{
		byte[] message = this.getUDPMessage().getMessage();
		byte[] partNumber;
		
		partNumber = new byte[message.length-ID.getIDLength()];
		
		System.arraycopy(message,ID.getIDLength(),partNumber,0,4);
		System.out.println(partNumber[0]);
		System.out.println(partNumber[1]);
		System.out.println(partNumber[2]);
		System.out.println(partNumber[3]);
		
		return partNumber;
	}


}
