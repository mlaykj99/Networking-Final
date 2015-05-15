package p2p_Final_Project;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
	
	public PeerController(SynchronizedLinkedListQueue uiQueue, SynchronizedLinkedListQueue peerQueue)
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
		this.packetMan = new PacketManager(this);
		try
		{
			this.socket = new DatagramSocket(54321);
			this.receiveFromPeers = new DatagramReceiver(socket, incomingPacketsFromPeerQueue, 512);
			this.sendToPeers = new DatagramSender(socket, outgoingPacketsToPeerQueue, 512);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
		
		this.sendToPeers.stop();
		this.receiveFromPeers.stop();
		this.queueListener.stop();
	}
	
	private void stop(){ this.done = true; }
	
	private void insert(CommandCall cc)
	{
		uiQueue.enQueue(cc);
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
			CommandCall test = new CommandCall("save");
			insert(test);
			System.out.println("Inserted into ui queue.");
		}
	}
	
	private class CommandFind extends PeerControllerCommand
	{
		
		public CommandFind(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
			// TODO Auto-generated method stub
			
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
			//End
		}
		
	}
}
