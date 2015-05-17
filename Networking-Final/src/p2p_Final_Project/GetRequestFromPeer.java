package p2p_Final_Project;

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
		Resource resource;
		int partNumberAsInt;
		int resourceIDAsInt;
		byte[] requestID;
		byte[] resourceID;
		byte[] timeToLive;
		byte[] randomID;
		byte[] partNumber;
		byte[] bytesFromResource;
		
		requestID = this.getUDPMessage().getId1().getBytes();
		resourceID = this.getUDPMessage().getId2().getBytes();
		resourceIDAsInt =  Utilities.bytesToInt(requestID);
		timeToLive = (new TimeToLive(Utilities.randomInt()).toByteArray());
		randomID = ID.idFactory().getBytes();
		partNumber = getPartNumber();
		partNumberAsInt =  Utilities.bytesToInt(partNumber);
		
		
		
		/*
		RandomAccessFile f = new RandomAccessFile(fileName, "r");
		byte[] b = new byte[(int)f.length()];
		f.read(b);
		*/
		
		
		

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
