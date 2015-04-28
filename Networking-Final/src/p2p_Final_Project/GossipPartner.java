package p2p_Final_Project;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class GossipPartner {
	private InetSocketAddress gossipPartnerAddress;
	private boolean isAlive;
	private OutgoingPacketQueue queue;
	
	public GossipPartner(InetSocketAddress gossipPartnerAddress,OutgoingPacketQueue queue)
	{
		this.isAlive = true;
		this.gossipPartnerAddress = gossipPartnerAddress;
		this.queue = queue;
	}
	public boolean equals(Object other)
	{
		return false;
	}
	public InetSocketAddress getGossipPartnerAddress()
	{
		return this.gossipPartnerAddress;
	}
	public int hashCode()
	{
		return 0;
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
		
		dgp = message.getDatagramPacket();
		dgp.setAddress(this.gossipPartnerAddress.getAddress());
		dgp.setPort(this.gossipPartnerAddress.getPort());
		this.queue.enQueue(dgp);
	}
}
