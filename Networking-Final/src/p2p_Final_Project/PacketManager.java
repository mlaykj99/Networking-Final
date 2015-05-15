package p2p_Final_Project;

import java.net.DatagramPacket;

public class PacketManager implements Runnable
{
	private PeerController pc;
	private boolean done;
	
	public PacketManager(PeerController pc)
	{
		this.pc = pc;
		this.done = false;
	}

	@Override
	public void run()
	{
		while(!this.done)
		{
			if(!pc.getIncomingPacketsFromPeerQueue().isEmpty())
			{
				DatagramPacket d = (DatagramPacket)pc.getIncomingPacketsFromPeerQueue().deQueue();
				UDPMessage msg = new UDPMessage(d);
				if(pc.getReqMan().getRequest(msg.getId2()) != null)
				{
					RequestToFindResources requestFind = new RequestToFindResources(msg.getId2());
					requestFind.updateRquest(msg);
				}
				else if((pc.getResMan().getResourcesThatMatch(new String(msg.getMessage(),0,msg.getMessage().length)).length < 1))
				{
					FindRequestFromPeer findRequest = new FindRequestFromPeer(msg);
					findRequest.run();
				}
				else if(pc.getResMan().getResourceByID(msg.getId2()) != null)
				{
					GetRequestFromPeer getRequest = new GetRequestFromPeer(msg);
					getRequest.run();
				}
				
				msg.decrementTimeToLive();
				if(msg.getTimeToLive().get() > 0)
				{
					pc.getOutgoingPacketsToPeerQueue().enQueue(msg.getDatagramPacket());
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
}
