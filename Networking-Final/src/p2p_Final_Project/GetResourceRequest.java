package p2p_Final_Project;

public class GetResourceRequest extends Request implements Runnable {

	//private ID resourceID;
	private ID requestID;
	private Long numberOfParts;
	public GetResourceRequest(ID resourceID,ID requestID,Long numberOfParts)
	{
		super(resourceID);
		//this.resourceID = resourceID;
		this.requestID = requestID;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	
	
	public void startAsThread()
	{
		new Thread(this).start();		
	}
	@Override
	public void updateRquest(UDPMessage udpMessage) {
		// TODO Auto-generated method stub
		
	}
	

}
