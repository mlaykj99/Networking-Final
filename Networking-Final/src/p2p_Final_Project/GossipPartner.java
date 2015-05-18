package p2p_Final_Project;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class GossipPartner {
	private InetSocketAddress gossipPartnerAddress;
	private boolean isAlive;
	private OutgoingPacketQueue queue;
	
	public GossipPartner(InetSocketAddress gossipPartnerAddress,OutgoingPacketQueue queue)
	{
		if(gossipPartnerAddress == null)
		{
			throw new IllegalArgumentException("Error: gossipPartnerAddress in class GossipPartner cannot be null.");
		}
		if(queue == null)
		{
			throw new IllegalArgumentException("Error: queue in class GossipPartner cannot be null.");
		}
		this.isAlive = true;
		this.gossipPartnerAddress = gossipPartnerAddress;
		this.queue = queue;
	}
	public boolean equals(GossipPartner other)
	{
		return other.getGossipPartnerAddress() == this.getGossipPartnerAddress();
	}
	public InetSocketAddress getGossipPartnerAddress()
	{
		return this.gossipPartnerAddress;
	}
	public int hashCode()
	{
		return getGossipPartnerAddress().toString().hashCode();
	}
	public boolean isAlive()
	{
		return this.isAlive;
	}
	public void send(UDPMessage message)
	{
		//TODO need to check if the partner isAlive first
		//how to do this do not know because we don't have access to them
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message is null in GossipPartner.");
		}
		DatagramPacket dgp;
		message.decrementTimeToLive();
		System.out.println("TimeToLive: "+message.getTimeToLive().get());
		if(message.getTimeToLive().get() > 0)
		{
			dgp = message.getDatagramPacket();
			dgp.setAddress(this.gossipPartnerAddress.getAddress());
			dgp.setPort(this.gossipPartnerAddress.getPort());
			//System.out.println(dgp.getAddress());
			this.queue.enQueue(dgp);
			//System.out.println("Inserting DatagramPacket into queue.");
		}
	}
}
