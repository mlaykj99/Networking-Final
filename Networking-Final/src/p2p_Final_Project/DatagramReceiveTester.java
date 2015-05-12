package p2p_Final_Project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramReceiveTester {
	public static void main(String[] args)
	{
		try {
			DatagramSocket ds = new DatagramSocket(54321);
			DatagramPacket dg = new DatagramPacket(new byte[512],512);
			UDPMessage[] udpArray = new UDPMessage[10];
			UDPMessage udp;
			int i = 0;
			
			while(i < 1)
			{
				ds.receive(dg);
				System.out.println(dg + " we received a packet a packet");
				udpArray[i] = new UDPMessage(dg);
				i++;
			}
			
			for(i = 0; i < udpArray.length;i++)
			{
					System.out.print(udpArray[i].getId1());
			}
			
			
			
			
			
			
			
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
