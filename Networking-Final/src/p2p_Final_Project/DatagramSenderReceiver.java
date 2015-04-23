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
	
	public void stop() { this.done.set(true); }
	
	public boolean isStopped() { return this.done.get(); }
	
	public void startAsThread() { new Thread(this).start(); }
	
	@Override
	public void run()
	{
		while(!this.done.get())
		{
			try
			{
				action(this.datagramSocket, this.queue);
				Thread.sleep(1000);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public abstract void action(DatagramSocket datagramSocket, SynchronizedPacketQueue queue);
}
