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
		DatagramPacket packet;
		Resource resource;
		int partNumberAsInt;
		int resourceIDAsInt;
		byte[] requestID;
		byte[] resourceID;
		byte[] timeToLive;
		byte[] randomID;
		byte[] partNumber;
		byte[] bytesFromResource;
		byte[] message;
		
		requestID = this.getUDPMessage().getId1().getBytes();
		resourceID = this.getUDPMessage().getId2().getBytes();
		resource = rm.getResourceByID(new ID(resourceID));
		timeToLive = (new TimeToLive(Utilities.randomInt()).toByteArray());
		randomID = ID.idFactory().getBytes();
		partNumber = getPartNumber();
		partNumberAsInt =  Utilities.bytesToInt(partNumber);
		bytesFromResource = resource.getBytes(partNumberAsInt);	
		
		message = Utilities.arrayCopy(Utilities.arrayCopy(resourceID,requestID,timeToLive,randomID,partNumber),bytesFromResource);
		
		packet = new DatagramPacket(message,message.length);

	}
	
	
	
	private byte[] getPartNumber()
	{
		byte[] message = this.getUDPMessage().getMessage();
		byte[] partNumber;
		
		partNumber = new byte[message.length-ID.getIDLength()];
		
		System.arraycopy(partNumber,0,message,ID.getIDLength(),message.length);
		
		return partNumber;
	}


}
