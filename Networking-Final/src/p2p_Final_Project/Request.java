package p2p_Final_Project;

public abstract class Request {
	private ID requestID;
	
	public Request(ID requestID)
	{
		if(requestID == null)
		{
			throw new IllegalArgumentException("Error: requestID in "+this.getClass()+"constructor cannot be null.");
		}
		this.requestID = requestID;
	}

	public ID getID()
	{
		return this.requestID;
	}
	public abstract void updateRquest(UDPMessage udpMessage);
}
