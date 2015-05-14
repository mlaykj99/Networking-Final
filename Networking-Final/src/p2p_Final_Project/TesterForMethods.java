package p2p_Final_Project;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TesterForMethods
{
	public static void main(String[] args)
	{
		/*ID id1 = ID.idFactory();
		ID id2 = ID.idFactory();
		
		//Hex test
		System.out.println("Hex test: " + id1.getAsHex());
		System.out.println("Hex test: " + id2.getAsHex());
		
		//To String
		System.out.println("To String: " + id1.toString());
		
		//Equals test
		System.out.println(id1.equals(id2));
		System.out.println(id1.equals(id1));
		
		//Zero test
		System.out.println(ID.getZeroID().isZero());
		System.out.println(id1.isZero());
		
		//Reference test
		System.out.println(id1.getBytes());*/
		
		
		
		//UDP Message
		byte[] b0 = new byte[36];
		byte[] b1 = new byte[500];
		byte[] b2 = new byte[300];
		byte[] b3 = new byte[16];
		byte[] b4 = new byte[476];
		
		int max = 16;
		int count = 0;
		for(byte i = 1; i <= 4; i++)
		{
			while(count<max)
			{
				b2[count] = i;
				count++;
			}
			if(i == 1){max += 16;}
			if(i == 2){max += 4;}
			if(i == 3){max += 300-36;}
		}
		
		DatagramPacket d0 = new DatagramPacket(b0, 36);
		DatagramPacket d1 = new DatagramPacket(b1, 500);
		DatagramPacket d2 = new DatagramPacket(b2, 300);
		DatagramPacket d3 = new DatagramPacket(b3, 16);
		DatagramPacket d4 = new DatagramPacket(b4, 476);
		
		//UDPMessage m0 = new UDPMessage(d0);
		//UDPMessage m1 = new UDPMessage(d1);*/
		UDPMessage m2 = new UDPMessage(d2);
		//UDPMessage m3 = new UDPMessage(d3);
		//UDPMessage m4 = new UDPMessage(d4);
		
		/*System.out.println(m2.getId1().toString());
		System.out.println(m2.getId2().toString());
		System.out.println(m2.getTimeToLive().toString());
		
		for(int i = 0; i < m2.getMessage().length; i++)
		{
			System.out.println(m2.getMessage()[i] + " | " + i);
		}*/
		
		
		/*for(int i = 0;i < m2.getDatagramPacket().getData().length;i++ )
		{
			System.out.println(m2.getDatagramPacket().getData()[i] + " | "+ i);
		}*/
		
		//System.out.println(m2.getDatagramPacket().getData()[300]);
		
		/*for(int i = 0;i < m2.getDatagramPacket("Aasd").getData().length;i++ )
		{
			System.out.println(m2.getDatagramPacket("Aasd").getData()[i] + " | "+ i);
		}*/
		
		/*for(int i = 0;i < m2.getDatagramPacket(new byte[5]).getData().length;i++ )
		{
			System.out.println(m2.getDatagramPacket(new byte[5]).getData()[i] + " | "+ i);
		}*/
		
		try {
			GossipPartners gps = GossipPartners.newInstance();
			OutgoingPacketQueue queue = new OutgoingPacketQueue();
			DatagramSocket ds = new DatagramSocket(54321);
			DatagramSender dsn = new DatagramSender(ds, queue, 512);
			//GossipPartner gp = new GossipPartner(new InetSocketAddress("10.20.61.151" , 54321) , queue);
			GossipPartner gp2 = new GossipPartner(new InetSocketAddress("10.20.73.219" , 54321) , queue);
			
			dsn.startAsThread();
			//gps.addPartner(gp);
			gps.addPartner(gp2);
			gps.send(m2);
			System.out.println("Sent");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dsn.stop();
		} 
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*SynchronizedLinkedListQueue ui = new SynchronizedLinkedListQueue();
		SynchronizedLinkedListQueue peer = new SynchronizedLinkedListQueue();
		UIController uic = new UIController(ui, peer);
		PeerController pc = new PeerController(ui, peer);
		
		pc.start();
		uic.start();*/
	}
}
