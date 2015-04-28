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
		//ID1 & ID2 16 bits and TTL 4 bits
		byte[] data = datagramPacket.getData();
		byte[] id1 = new byte[16];
		byte[] id2 = new byte[16];
		byte[] ttl = new byte[4];
		byte[] message = new byte[data.length - (id1.length+id2.length+ttl.length )];
		
		for(int i = 0;i<id1.length;i++)
		{
			id1[i] = data[i];
			id2[i] = data[i+16];
		}
		for(int i = 32;i<id1.length+id2.length+ttl.length;i++)
		{
			ttl[i] = data[i];
		}
		System.arraycopy(data,id1.length+id2.length+ttl.length , message, 0, message.length);
	}
	public DatagramPacket getDatagramPacket()
	{
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
