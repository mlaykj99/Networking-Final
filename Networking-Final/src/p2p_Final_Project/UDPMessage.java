package p2p_Final_Project;

import java.net.DatagramPacket;

public class UDPMessage {
	private ID id1;
	private ID id2;
	private TimeToLive timeToLive;
	private byte[] message;
	public UDPMessage(ID id1,ID id2, TimeToLive timeToLive,String message)
	{
		this.id1 = id1;
		this.id2 = id2;
		this.timeToLive = timeToLive;
		this.message = message.getBytes();
	}
	public UDPMessage(ID id1,ID id2, TimeToLive timeToLive,byte[] message)
	{
		this.id1 = id1;
		this.id2 = id2;
		this.timeToLive = timeToLive;
		this.message = message;
	}
	public UDPMessage(DatagramPacket datagramPacket)
	{
		//TODO process datagramPacket given our protocol
	}
	public DatagramPacket getDatagramPacket()
	{
		//TODO build datagram packet from the class variables
		return null;
	}
	public DatagramPacket getDatagramPacket(String message)
	{
		//TODO build datagram packet from the class variables
		//use the new message instead of class variable
		return null;
	}
	public DatagramPacket getDatagramPacket(byte[] message)
	{
		//TODO build datagram packet from the class variables
		//use the new message instead of class variable
		return null;
	}
	public ID getId1() {
		return this.id1;
	}
	public ID getId2() {
		return this.id2;
	}
	public TimeToLive getTimeToLive() {
		return this.timeToLive;
	}
	public byte[] getMessage() {
		return this.message;
	}
	
}
