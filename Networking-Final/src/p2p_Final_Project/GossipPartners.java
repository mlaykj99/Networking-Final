package p2p_Final_Project;

import java.util.ArrayList;
import java.util.Iterator;

public class GossipPartners {
	private static GossipPartners instance = new GossipPartners();
	private ArrayList<GossipPartner> gossipPartners;
	
	private GossipPartners()
	{
		this.gossipPartners = new ArrayList<GossipPartner>();
	}
	public static GossipPartners getInstance()
	{
		return instance;
	}
	public static GossipPartners newInstance()
	{
		return instance;
	}
	public void addPartner(GossipPartner gossipPartner)
	{
		gossipPartners.add(gossipPartner);
	}
	public void send(UDPMessage message)
	{
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in GossipPartners  send method is null.");
		}
		Iterator<GossipPartner> i = gossipPartners.iterator();
		
		while(i.hasNext())
		{
			i.next().send(message);
		}
	}
}
