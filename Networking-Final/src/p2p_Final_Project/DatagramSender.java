package p2p_Final_Project;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramSender extends DatagramSenderReceiver
{
	public DatagramSender(DatagramSocket datagramSocket, OutgoingPacketQueue queue, int packetSize) throws SocketException
	{
		super(datagramSocket, queue, packetSize);
	}
	
	public void action(DatagramSocket datagramSocket, SynchronizedPacketQueue queue)
	{
		if(!queue.isEmpty())
		{ 
			try
			{
				DatagramPacket dgp = (DatagramPacket) queue.deQueue();
				System.out.println("Length: " + dgp.getLength());
				datagramSocket.send(dgp);
				Thread.sleep(1000);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			ID.generateID();
		}
	}
}
