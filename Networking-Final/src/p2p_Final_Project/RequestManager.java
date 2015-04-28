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
		return this.requestDirectory.get(id);
	}
	public void insertRequest(Request request)
	{
		requestDirectory.put(request.getID(),request);
	}
}
