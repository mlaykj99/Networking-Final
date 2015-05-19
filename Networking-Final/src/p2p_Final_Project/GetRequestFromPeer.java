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
		message = (""+ID.idFactory() + partNumber+new String(bytesFromResource)).getBytes();
		packet = new UDPMessage(resourceID,requestID,timeToLive,message);
		System.arraycopy(message, 16, holdID, 0, 4);
		GossipPartners.getInstance().send(packet);
	}
	
	
	
	private byte[] getPartNumber()
	{
		byte[] message = this.getUDPMessage().getMessage();
		byte[] partNumber;
		System.out.println("Message in getPartNumber: "+new String(message));
		//partNumber = new byte[message.length-ID.getIDLength()];
		partNumber = new byte[]{0,0,0,0};
		
		
		for(int i = 0; i< message.length;i++)
		{
			System.out.println("Byte "+(i+1)+": "+message[i]);
		}
		
		
		System.out.println("First byte: "+partNumber[0]);
		System.out.println("Scnd byte: "+partNumber[1]);
		System.out.println("Third byte: "+partNumber[2]);
		System.out.println("Fourth byte: "+partNumber[3]);
		System.arraycopy(message,ID.getIDLength(),partNumber,4-(message.length-ID.getIDLength()),message.length-ID.getIDLength());
		//System.arraycopy(src, srcPos, dest, destPos, length);
		System.out.println("First byte after: "+partNumber[0]);
		System.out.println("Scnd byte after: "+partNumber[1]);
		System.out.println("Third byte after: "+partNumber[2]);
		System.out.println("Fourth byte after: "+partNumber[3]);
		System.out.println("Part number in getPartNumber() "+new String(partNumber));
		
		return partNumber;
	}


}
