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
	
	public DatagramSenderReceiver(DatagramSocket datagramSocket, SynchronizedPacketQueue queue, int packetSize) throws SocketException
	{
		if( datagramSocket == null ) { throw new IllegalArgumentException("DatagramSenderReceiver.constructor: inetSocketAddress is null!"); }
		if( queue == null ) { throw new IllegalArgumentException("DatagramSenderReceiver.constructor: queue is null!"); }
		if( packetSize < 1 ) { throw new IllegalArgumentException("DatagramSenderReceiver.constructor: packetSize is less than 1!"); }
		
		this.datagramSocket = datagramSocket;
		this.queue = queue;
		this.packetSize = packetSize;
		this.done = new AtomicBoolean();
	}
	
	public int getPacketSize() { return this.packetSize; }
	
	public void stop() { this.done.set(true); }
	
	public boolean isStopped() { return this.done.get(); }
	
	public void startAsThread() { new Thread(this).start(); }
	
	@Override
	public void run()
	{
		while(!this.done.get())
		{
			action(this.datagramSocket, this.queue);
		}
	}
	
	public abstract void action(DatagramSocket datagramSocket, SynchronizedPacketQueue queue);
}
