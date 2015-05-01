package p2p_Final_Project;

import java.net.DatagramPacket;

public class UDPMessage {
	private ID id1;
	private ID id2;
	private TimeToLive timeToLive;
	private byte[] message;
	public UDPMessage(ID id1,ID id2, TimeToLive timeToLive,String message)
	{
		if(id1 == null)
		{
			throw new IllegalArgumentException("Error: id1 in UDPmessage cannot be null.");
		}
		if(id2 == null)
		{
			throw new IllegalArgumentException("Error: id2 in UDPmessage cannot be null.");
		}
		if(timeToLive == null)
		{
			throw new IllegalArgumentException("Error: timeToLive in UDPMessage cannot be null.");
		}
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in UDPMessage cannot be null.");
		}
		this.id1 = id1;
		this.id2 = id2;
		this.timeToLive = timeToLive;
		this.message = message.getBytes();
	}
	public UDPMessage(ID id1,ID id2, TimeToLive timeToLive,byte[] message)
	{
		if(id1 == null)
		{
			throw new IllegalArgumentException("Error: id1 in UDPmessage cannot be null.");
		}
		if(id2 == null)
		{
			throw new IllegalArgumentException("Error: id2 in UDPmessage cannot be null.");
		}
		if(timeToLive == null)
		{
			throw new IllegalArgumentException("Error: timeToLive in UDPMessage cannot be null.");
		}
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in UDPMessage cannot be null.");
		}
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
		//TODO need to checking to make sure the values truly are numbers
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
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in UDPMessage method getDatagramPacket(String) cannot be null.");
		}
		//TODO build datagram packet from the class variables
		//use the new message instead of class variable
		return null;
	}
	public DatagramPacket getDatagramPacket(byte[] message)
	{
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in UDPMessage method getDatagramPacket(byte[]) cannot be null.");
		}
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
