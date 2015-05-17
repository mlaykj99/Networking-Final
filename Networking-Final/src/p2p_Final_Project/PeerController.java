package p2p_Final_Project;

import java.io.File;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import p2p_GUI.FrameBruh;

public class PeerController
{
	private CommandProcessor 	commandProcessor;
	private boolean 			done;
	private IncomingPacketQueue incomingPacketsFromPeerQueue;
	private OutgoingPacketQueue outgoingPacketsToPeerQueue;
	private DatagramReceiver 	receiveFromPeers;
	private DatagramSender 		sendToPeers;
	private SynchronizedLinkedListQueue uiQueue;
	private SynchronizedLinkedListQueue peerQueue;
	private DatagramSocket socket;
	private QueueListener queueListener;
	private RequestManager reqMan;
	private ResourceManager resMan;
	private PacketManager packetMan;
	private GossipPartners partners;
	private FrameBruh frame;
	
	
	public PeerController(SynchronizedLinkedListQueue uiQueue, SynchronizedLinkedListQueue peerQueue, FrameBruh frame)
	{
		this.uiQueue = uiQueue;
		this.peerQueue = peerQueue;
		this.incomingPacketsFromPeerQueue = new IncomingPacketQueue();
		this.outgoingPacketsToPeerQueue = new OutgoingPacketQueue();
		this.commandProcessor = new CommandProcessor(null,null);
		this.queueListener = new QueueListener(peerQueue, commandProcessor);
		this.done = false;
		this.reqMan = RequestManager.newInstance();
		this.resMan = ResourceManager.newInstance();
		this.frame = frame;
		this.packetMan = new PacketManager(this, frame);
		try
		{
			this.socket = new DatagramSocket(12345);
			this.receiveFromPeers = new DatagramReceiver(socket, incomingPacketsFromPeerQueue, 512);
			this.sendToPeers = new DatagramSender(socket, outgoingPacketsToPeerQueue, 512);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		ResourceManager.loadResourcesFrom(new File("res/files.txt"));
		
		partners = GossipPartners.newInstance();
		
		//partners.addPartner(new GossipPartner(new InetSocketAddress("10.20.24.132" , 12345) , outgoingPacketsToPeerQueue));
		//partners.addPartner(new GossipPartner(new InetSocketAddress("140.209.121.104" , 12345) , outgoingPacketsToPeerQueue));
		//partners.addPartner(new GossipPartner(new InetSocketAddress("140.209.121.209" , 12345) , outgoingPacketsToPeerQueue));
		partners.addPartner(new GossipPartner(new InetSocketAddress("10.20.61.151" , 12345) , outgoingPacketsToPeerQueue));
		
	}
	public void start()
	{
		commandProcessor.register(new CommandJoin("join","Used to join the peer community."));
		commandProcessor.register(new CommandGet("get","Used to request items from the peer community."));
		commandProcessor.register(new CommandFind("find","Used to find items that others on the peer community have."));
		commandProcessor.register(new CommandExit("exit","Used to exit the peer community."));
		
		this.sendToPeers.startAsThread();
		this.receiveFromPeers.startAsThread();
		this.queueListener.startAsThread();
		
		this.packetMan.startAsThread();
	}
	
	private void stop()
	{
		this.sendToPeers.stop();
		this.receiveFromPeers.stop();
		this.queueListener.stop();
		this.packetMan.stop();
	}
	
	private void insert(CommandCall cc)
	{
		uiQueue.enQueue(cc);
	}
	public GossipPartners getPartners()
	{
		return this.partners;
	}
	public IncomingPacketQueue getIncomingPacketsFromPeerQueue()
	{
		return incomingPacketsFromPeerQueue;
	}
	public OutgoingPacketQueue getOutgoingPacketsToPeerQueue()
	{
		return outgoingPacketsToPeerQueue;
	}
	public RequestManager getReqMan()
	{
		return reqMan;
	}
	public ResourceManager getResMan()
	{
		return resMan;
	}
	private abstract class PeerControllerCommand extends Command
	{
		public PeerControllerCommand()
		{
			super();
		}
		public PeerControllerCommand(String commandName,String description)
		{
			super(commandName,description);
		}
		public CommandProcessor getCommandProcessor()
		{
			return commandProcessor;
		}
		public boolean getDoneFlag()
		{
			return done;
		}
		public void print(String message)
		{
			System.out.print(message);
		}
		public void println()
		{
			System.out.println();
		}
		public void println(String message)
		{
			System.out.println(message);
		}
		public void setDoneFlag(boolean flag)
		{
			done = flag;
		}
		public void sendToPeer(byte[] message)
		{
			//getOutgoing().enQueue(new DatagramPacket(getPeerAddress(),));
		}
	}
	
	private class CommandJoin extends PeerControllerCommand
	{
		public CommandJoin(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() 
		{
			System.out.println("Join Command Ran in PeerCont");
		}
	}
	
	private class CommandGet extends PeerControllerCommand
	{
		public CommandGet(String commandName,String description)
		{
			super(commandName,description);
		}
		
		public void run()
		{
			int resourceID = Integer.parseInt(this.getParameters());
			ID requestID = ID.idFactory();
			UDPMessage udpMessage;
			TimeToLive ttl;
			
			ttl = new TimeToLive(Utilities.randomInt());
			
		}
	}
	
	private class CommandFind extends PeerControllerCommand
	{
		
		public CommandFind(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
			Request request;
			UDPMessage udpMessage;
			TimeToLive ttl;
			
			ttl = new TimeToLive(Utilities.randomInt());
			request = new RequestToFindResources(ID.idFactory());
			getReqMan().insertRequest(request);
			udpMessage = new UDPMessage(request.getID(),ID.idFactory(),ttl,this.getParameters());
			System.out.println("Sending a Find Request.");
			getPartners().send(udpMessage);
		}
	}
	
	private class CommandExit extends PeerControllerCommand
	{
		public CommandExit(String commandName,String description)
		{
			super(commandName,description);
		}
		
		public void run()
		{
			stop();
		}
		
	}
}
