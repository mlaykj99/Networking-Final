package p2p_Final_Project;

import java.util.ArrayList;

public class RequestToFindResources extends Request implements Runnable {
	
	ArrayList<UDPMessage> responses;
	
	public RequestToFindResources(ID requestID) 
	{
		super(requestID);
	}
	public void updateRquest(UDPMessage udpMessage) 
	{
		
	}
	@Override
	public void run() {
		
	}
	public void startAsThread()
	{
		this.run();
	}

}
