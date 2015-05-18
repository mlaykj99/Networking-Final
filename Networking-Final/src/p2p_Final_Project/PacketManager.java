package p2p_Final_Project;

import java.net.DatagramPacket;
import java.util.ArrayList;

import javax.swing.JFrame;

import p2p_GUI.FrameBruh;

public class PacketManager implements Runnable
{
	private PeerController pc;
	private boolean done;
	private ResponsesToOurFinds responsesToOurFinds;
	private ArrayList<ID> ignoreList;
	public PacketManager(PeerController pc, FrameBruh frame,ArrayList ignoreList)
	{
		this.pc = pc;
		this.done = false;
		this.responsesToOurFinds = new ResponsesToOurFinds(frame);
		this.ignoreList = ignoreList;
	}

	@Override
	public void run()
	{
		while(!this.done)
		{
			if(!pc.getIncomingPacketsFromPeerQueue().isEmpty())
			{
				//System.out.println("GOT IT BITCHISE!");
				DatagramPacket d = (DatagramPacket)pc.getIncomingPacketsFromPeerQueue().deQueue();
				UDPMessage msg = new UDPMessage(d);
				boolean b = pc.getReqMan().getRequest(msg.getId2()) != null;
				
				System.out.println("Checking if a prev request "+b);
				System.out.println("Orginiating id: "+ msg.getId2());
				System.out.println("First id: "+msg.getId1());
				if(b)
				{
					System.out.println("UPDATEING THE RESPONSES");
					this.responsesToOurFinds.updateResponses(msg);
				}
				else if(pc.getReqMan().getRequest(msg.getId2()) != null)
				{
					
				}
				else if(pc.getResMan().getResourceByID(msg.getId2()) != null)
				{
					GetRequestFromPeer getRequest = new GetRequestFromPeer(msg, pc.getOutgoingPacketsToPeerQueue());
					getRequest.run();
				}
				else
				{
					System.out.println("Attempting to find resource.");
					FindRequestFromPeer findRequest = new FindRequestFromPeer(msg, pc.getOutgoingPacketsToPeerQueue());
					findRequest.run();
				}
				
				//msg.decrementTimeToLive();
				GossipPartners.getInstance().send(msg);
				/*
				if(msg.getTimeToLive().get() > 0)
				{
					GossipPartners.getInstance().send(msg);
				}
				*/
			}
		}
	}
	
	public void stop()
	{
		this.done = true;
	}
	
	public void startAsThread()
	{
		new Thread(this).start();
	}
	public ResponsesToOurFinds getResponsesToOurFinds() {
		return responsesToOurFinds;
	}

}
