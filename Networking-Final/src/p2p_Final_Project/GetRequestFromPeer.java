package p2p_Final_Project;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

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
		System.out.println("Part number as bytes " + new String(partNumAsBytes));
		partNumber = 0;
		System.out.println("PartNumber befroe assigning: " + partNumber);
	
		partNumber = ByteBuffer.wrap(partNumAsBytes).getInt();
		System.out.println("PartNumber after getting from bytes and befroe requesting parts: " + partNumber);
		/*for (int i = 0; i < partNumAsBytes.length; i++) {
		    partNumber = (partNumber << (8)) | partNumAsBytes[i];
		}*/

		bytesFromResource = resource.getBytes(partNumber);
		System.out.println("This is the partNumber after requesting the part: "+partNumber);
		//message = (""+ID.idFactory() + partNumber+new String(bytesFromResource)).getBytes();
		
		message = Utilities.arrayCopy(ID.idFactory().getBytes(),partNumAsBytes,bytesFromResource);
		
		packet = new UDPMessage(resourceID,requestID,timeToLive,message);
		System.arraycopy(message, 16, holdID, 0, 4);
		GossipPartners.getInstance().send(packet);
	}
	
	
	
	private byte[] getPartNumber()
	{
		byte[] message = this.getUDPMessage().getMessage();
		byte[] partNumber;

		//partNumber = new byte[message.length-ID.getIDLength()];
		partNumber = new byte[]{0,0,0,0};
		
		System.arraycopy(message,ID.getIDLength(),partNumber,0,4);
		//System.arraycopy(src, srcPos, dest, destPos, length);
		
		return partNumber;
	}


}
