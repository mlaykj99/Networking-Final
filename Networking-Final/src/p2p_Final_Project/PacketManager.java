package p2p_Final_Project;

import java.net.DatagramPacket;

import javax.swing.JFrame;

public class PacketManager implements Runnable
{
	private PeerController pc;
	private boolean done;
	private ResponsesToOurFinds responsesToOurFinds;
	public PacketManager(PeerController pc)
	{
		this.pc = pc;
		this.done = false;
		this.responsesToOurFinds(new Jframe());
	}

	@Override
	public void run()
	{
		while(!this.done)
		{
			if(!pc.getIncomingPacketsFromPeerQueue().isEmpty())
			{
				System.out.println("GOT IT BITCHISE!");
				DatagramPacket d = (DatagramPacket)pc.getIncomingPacketsFromPeerQueue().deQueue();
				UDPMessage msg = new UDPMessage(d);
				if(pc.getReqMan().getRequest(msg.getId2()) != null)
				{
					ResponsesToOurFinds requestFind = new ResponsesToOurFinds(new JFrame());
					requestFind.updateResponses(msg);
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
				
				msg.decrementTimeToLive();
				if(msg.getTimeToLive().get() > 0)
				{
					GossipPartners.getInstance().send(msg);
				}
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
