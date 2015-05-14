package p2p_Final_Project;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DatagramReceiveTester {
	public static void main(String[] args) throws InterruptedException, SocketException
	{
		
		IncomingPacketQueue ipq = new IncomingPacketQueue();
		DatagramSocket ds = new DatagramSocket(54321);
		DatagramReceiver dr = new DatagramReceiver(ds,ipq,UDPMessage.getMaximumPacketSize());
		
		dr.startAsThread();
		
		Thread.sleep(3000);
		
		
		while(true)
		{
			//System.out.println("HI");
			if(!ipq.isEmpty())
			{
				System.out.println(((DatagramPacket) ipq.deQueue()).getData().length);	
			}
		}
		
		
	

			
			
			
			
			/*
			 try{
			byte[] buffer = new byte[512];
			DatagramSocket ds = new DatagramSocket(54321);
			DatagramPacket dg = new DatagramPacket(buffer,buffer.length);
			dg.setPort(54321);
			//byte[] a = {10,10,10,10,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			//dg.setData(a,0,5);
			UDPMessage[] udpArray = new UDPMessage[10];
			UDPMessage udp;
			int i = 0;
			
			while(i < 10)
			{
				System.out.println("Waiting");
				ds.receive(dg);
				System.out.println(new String(dg.getData(),0,dg.getLength()));
				System.out.println(dg.getLength());
				System.out.println(dg.getData()[12] + " we received a packet a packet");
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
		*/
	}
}
