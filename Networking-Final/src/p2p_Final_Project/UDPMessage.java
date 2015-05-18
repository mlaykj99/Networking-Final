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
		if(datagramPacket == null)
		{
			throw new IllegalArgumentException("Error: UDPMessage.constructor(DatagramPacket) datagramPacket cannot be null");
		}
		
		//ID1 & ID2 16 bits and TTL 4 bits
		byte[] data = datagramPacket.getData();
		
		if(data.length <= getMinimumPacketSize())
		{
			throw new InvalidPacketFormatException("DatagramPacket in UDPMessage constructor is too small.",datagramPacket);
		}
		if(data.length > getMaximumPacketSize())
		{
			throw new InvalidPacketFormatException("DatagramPacket in UDPMessage constructor is too large.",datagramPacket);
		}
		
		byte[] id1 = new byte[ID.getIDLength()];
		byte[] id2 = new byte[ID.getIDLength()];
		byte[] ttl = new byte[TimeToLive.getLengthInBytes()];
		byte[] message = new byte[data.length - (id1.length+id2.length+ttl.length )];
		
		
		System.arraycopy(data,0,id1,0,id1.length);
		System.arraycopy(data, id1.length, id2, 0, id2.length);
		System.arraycopy(data,id1.length+id2.length,ttl,0,ttl.length);
		System.out.println(id1.length);
		System.out.println(id2.length);
		System.out.println(ttl.length);
		System.arraycopy(data,id1.length+id2.length+ttl.length , message, 0, message.length);
		
		this.id1 = new ID(id1);
		this.id2 = new ID(id2);
		this.timeToLive = new TimeToLive(ttl);
		System.out.println("UDPMessage Constructor: Why do you suck? "+this.timeToLive.get());
		System.out.println(timeToLive);
		System.out.println(data[32]);
		System.out.println(data[33]);
		System.out.println(data[34]);
		System.out.println(data[35]);
		this.message = message;
	}
	public DatagramPacket getDatagramPacket()
	{
		byte[] data;
		
		data = new byte[getMinimumPacketSize()+this.message.length];
		
		System.arraycopy(this.id1.getBytes(), 0, data, 0, ID.getIDLength());
		System.arraycopy(this.id2.getBytes(), 0,data , ID.getIDLength(), ID.getIDLength());
		System.arraycopy(this.timeToLive.toByteArray(),0,data,ID.getIDLength()*2,TimeToLive.getLengthInBytes());
		System.arraycopy(this.message,0,data,ID.getIDLength()*2+TimeToLive.getLengthInBytes(),this.message.length);
		
		return new DatagramPacket(data,data.length);
	}
	public DatagramPacket getDatagramPacket(String message)
	{
		
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in UDPMessage method getDatagramPacket(String) cannot be null.");
		}
		
		byte[] data;
		byte[] messageAsBytes;
		
		messageAsBytes = message.getBytes();
		data = new byte[ID.getIDLength()*2+TimeToLive.getLengthInBytes()+messageAsBytes.length];
		
		
		System.arraycopy(this.id1.getBytes(), 0, data, 0, ID.getIDLength());
		System.arraycopy(this.id2.getBytes(), 0,data , ID.getIDLength(), ID.getIDLength());
		System.arraycopy(this.timeToLive.toByteArray(),0,data,ID.getIDLength()*2,TimeToLive.getLengthInBytes());
		System.arraycopy(messageAsBytes,0,data,ID.getIDLength()*2+TimeToLive.getLengthInBytes(),messageAsBytes.length);
		
		return new DatagramPacket(data,0);
	}
	public DatagramPacket getDatagramPacket(byte[] message)
	{
		if(message == null)
		{
			throw new IllegalArgumentException("Error: message in UDPMessage method getDatagramPacket(byte[]) cannot be null.");
		}
		
		byte[] data;
		
		data = new byte[ID.getIDLength()*2+TimeToLive.getLengthInBytes()+message.length];
		
		System.arraycopy(this.id1.getBytes(), 0, data, 0, ID.getIDLength());
		System.arraycopy(this.id2.getBytes(), 0,data , ID.getIDLength(), ID.getIDLength());
		System.arraycopy(this.timeToLive.toByteArray(),0,data,ID.getIDLength()*2,TimeToLive.getLengthInBytes());
		System.arraycopy(message,0,data,ID.getIDLength()*2+TimeToLive.getLengthInBytes(),message.length);
		
		return new DatagramPacket(data,0);
	}
	public void decrementTimeToLive()
	{
		System.out.println("Decrementing Time To Liveaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		this.timeToLive.set(this.timeToLive.get()-1);
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
	public static int getMaximumPacketSize()
	{
		return 512;
	}
	public static int getMinimumPacketSize()
	{
		return ID.getIDLength()*2+TimeToLive.getLengthInBytes();
	}
}
