package p2p_Final_Project;

import java.util.ArrayList;

public class RequestToFindResources extends Request {
	
	ArrayList<ID> responses;
	
	public RequestToFindResources(ID requestID) 
	{
		super(requestID);
	}
	public void updateRquest(UDPMessage udpMessage) 
	{
		
	}

}
