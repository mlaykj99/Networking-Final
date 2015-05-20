package p2p_Final_Project;

import java.util.ArrayList;
import java.util.Iterator;

public class GetRequestManager {
	private ArrayList<GetResourceRequest> gets;
	private static GetRequestManager grm = new GetRequestManager();
	
	private GetRequestManager()
	{
		this.gets = new ArrayList<GetResourceRequest>();
	}
	public static GetRequestManager getInstance()
	{
		return grm;
	}
	
	public static GetRequestManager newInstance()
	{
		return grm;
	}

	public boolean contains(ID requestID)
	{
		Iterator<GetResourceRequest> i;
		boolean b;
		GetResourceRequest g;
		i = gets.iterator();
		b = false;
		
		while(i.hasNext())
		{
			g = i.next();
			if(g.getID().equals(requestID))
			{
				b = true;
			}
		}
		
		return b;
	}
	public void add(GetResourceRequest get)
	{
		this.gets.add(get);
	}
	public GetResourceRequest get(ID requestID)
	{
		Iterator<GetResourceRequest> i;
		GetResourceRequest g;
		boolean b;
		i = gets.iterator();
		b = false;
		g = i.next();
		while(i.hasNext() || b == false)
		{
			
			if(g.getID().equals(requestID))
			{
				b = true;
			}
			else
			{
				g = i.next();
			}
		}
		
		return g;
	}
}
