package p2p_Final_Project;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class DatagramReceiver extends DatagramSenderReceiver
{
	public DatagramReceiver(DatagramSocket datagramSocket, IncomingPacketQueue queue, int packetSize) throws SocketException
	{
		super(datagramSocket, queue, packetSize);
	}
	
	public void action(DatagramSocket datagramSocket, SynchronizedPacketQueue queue)
	{
		try
		{
			DatagramPacket packet = new DatagramPacket(new byte[476], 476);
			datagramSocket.receive(packet);
			queue.enQueue(packet);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
