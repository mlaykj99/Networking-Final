package p2p_Final_Project;

import java.util.HashMap;

public class RequestManager {
	private static RequestManager instance = new RequestManager();
	private HashMap<ID,Request> requestDirectory;
	private RequestManager()
	{
		this.requestDirectory = new HashMap<ID, Request>();
	}
	public static RequestManager getInstance()
	{
		return instance;
	}
	public static RequestManager newInstance()
	{
		return getInstance();
	}
	public Request getRequest(ID id)
	{
		if(id == null)
		{
			throw new IllegalArgumentException("Error: id in RequestManager method getRequest cannot be null.");
		}
		return this.requestDirectory.get(id);
	}
	public void insertRequest(Request request)
	{
		if(request == null)
		{
			throw new IllegalArgumentException("Error: request in RequestManager insertRequest cannot be null.");
		}
		//System.out.println("Successful Insertion.");
		requestDirectory.put(request.getID(),request);
		//System.out.println(request.getID());
	}
	
	public void clearRequestDirectory()
	{
		this.requestDirectory.clear();
	}
}
