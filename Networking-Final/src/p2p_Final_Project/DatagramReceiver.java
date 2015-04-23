package p2p_Final_Project;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class DatagramReceiver extends DatagramSenderReceiver
{
	public DatagramReceiver(InetSocketAddress inetSocketAddress, IncomingPacketQueue queue, int packetSize) throws SocketException
	{
		super(inetSocketAddress, queue, packetSize);
	}
	
	public SynchronizedPacketQueue action(DatagramSocket datagramSocket, SynchronizedPacketQueue queue)
	{
		return new IncomingPacketQueue();
	}
}
