package p2p_Final_Project;

public class GetResourceRequest extends Request implements Runnable {

	//private ID resourceID;
	private ID resourceID;
	private Long length;
	private ID requestID;
	private PartNumbers numberOfParts;
	private OutgoingPacketQueue queue;
	public GetResourceRequest(ID resourceID,ID requestID,Long length,OutgoingPacketQueue queue)
	{
		super(requestID);
		//this.resourceID = resourceID;
		this.requestID = requestID;
		this.resourceID = requestID;
		this.length = length;
		this.numberOfParts = new PartNumbers((int)Math.ceil((this.length/(double)456)));
		this.queue = queue;
		
	}
	@Override
	public void run() {
		// request parts

	}
	
	
	public void startAsThread()
	{
		new Thread(this).start();		
	}
	@Override
	public void updateRquest(UDPMessage udpMessage) {
		// gets called with parts and fills the file up;
		
	}
	

}
