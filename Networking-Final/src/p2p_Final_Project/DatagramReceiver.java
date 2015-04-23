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
		if(!queue.isEmpty())
		{
			try
			{
				datagramSocket.receive((DatagramPacket) queue.deQueue());
				//Thread.sleep(100);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
