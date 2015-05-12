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
			DatagramPacket dg = new dg(new byte[512],512);
			DatagramPacket[] dgArray = new DatagramPacket[10];
			int i = 0;
			
			while(i < 10)
			{
				ds.receive(dg);
				System.out.println(dg + " we received a packet a packet");
				dgArray[i] = dg;
				i++;
			}
			
			for(i = 0; i < dgArray.length;i++)
			{
				for(int j = 0; j < dgArray[i].getData().length;j++)
				{
					System.out.print(dgArray[i].getData()[j]);
				}
				System.out.println();
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
