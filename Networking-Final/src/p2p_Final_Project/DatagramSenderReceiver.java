package p2p_Final_Project;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DatagramSenderReceiver implements Runnable
{
	private AtomicBoolean done;
	private DatagramSocket datagramSocket;
	private int packetSize;
	private SynchronizedPacketQueue queue;
	
	public DatagramSenderReceiver(InetSocketAddress inetSocketAddress, SynchronizedPacketQueue queue, int packetSize) throws SocketException
	{
		if( inetSocketAddress == null ) { throw new IllegalArgumentException("DatagramSenderReceiver.constructor: inetSocketAddress is null!"); }
		if( queue == null ) { throw new IllegalArgumentException("DatagramSenderReceiver.constructor: queue is null!"); }
		if( packetSize < 1 ) { throw new IllegalArgumentException("DatagramSenderReceiver.constructor: packetSize is less than 1!"); }
		
		this.datagramSocket = new DatagramSocket(inetSocketAddress);
		this.queue = queue;
		this.packetSize = packetSize;
	}
	
	public int getPacketSize() { return this.packetSize; }
	
	public void stop()
	{ 
		
	}
	
	public boolean isStopped() { return this.done.get(); }
	
	public void startAsThread()
	{
		
	}
	
	@Override
	public void run()
	{
		
	}
	
	public abstract SynchronizedPacketQueue action(DatagramSocket datagramSocket, SynchronizedPacketQueue queue);
}
