package p2p_Final_Project;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UIController
{
	private CommandProcessor 	commandProcessor;
	private boolean 			done;
	private IncomingPacketQueue incomingPacketsFromPeerQueue;
	private OutgoingPacketQueue outgoingPacketsToPeerQueue;
	private InetSocketAddress 	peerAddress;
	private DatagramReceiver 	receiveFromPeer;
	private DatagramSender 		sendToPeer;
	
	public UIController(PortNumberForReceiving incomingPortNumber, PortNumberForSending outgoingPortNumber,int packetSize)
	{
		this.incomingPacketsFromPeerQueue = new IncomingPacketQueue();
		this.outgoingPacketsToPeerQueue = new OutgoingPacketQueue();
		this.peerAddress = new InetSocketAddress("192.168.255.0",packetSize);
		this.receiveFromPeer = new DatagramReceiver(new DatagramSocket(incomingPortNumber.get(),peerAddress.getAddress()),this.incomingPacketsFromPeerQueue,packetSize)
		this.sendToPeer = new DatagramSender(new DatagramSocket(outgoingPortNumber.get(),peerAddress.getAddress()),this.outgoingPacketsFromPeerQueue,packetSize)
		this.done = false;
		this.commandProcessor = new CommandProcessor(new CommandNone(),new CommandError());
	}
	public void start()
	{
		commandProcessor.register(new CommandJoin("join","Used to join the peer community."));
		commandProcessor.register(new CommandGet("get","Used to request items from the peer community."));
		commandProcessor.register(new CommandFind("find","Used to find items that others on the peer community have."));
	
	
	
	
	}
	private abstract class UIControllerCommand extends Command
	{
		public UIControllerCommand()
		{
			super();
		}
		public UIControllerCommand(String commandName,String description)
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
		public void sendToPeer()
		{
			print("Sending to Peer for Execution");
		}
	}
	private class CommandHelp extends UIControllerCommand
	{
		public CommandHelp(String commandName,String description)
		{
			super(commandName,description);
		}
		public void execute() {
			Command[] allCommands = getCommandProcessor().getAllCommands();
			for(int i = 0;i < allCommands.length; i++)
			{
				println(allCommands[i].getCommandName() + ": "+ allCommands[i].getDescription());
			}
		}
	}
	private class CommandError extends UIControllerCommand
	{
		public CommandError()
		{
			
		}
		public void execute() 
		{
			println("Sorry an error occur.");
		}
	}
	private class CommandNone extends UIControllerCommand
	{
		public CommandNone()
		{
			super();
		}
		public void execute() 
		{
			println("Sorry that command does not exist. Type help for a list of commands.");
		}
	}
	private class CommandJoin extends UIControllerCommand
	{
		public CommandJoin(String commandName,String description)
		{
			super(commandName,description);
		}
		public void execute() 
		{
			println("Joining the Peer Group");
		}
	}
	private class CommandGet extends UIControllerCommand
	{
		public CommandGet(String commandName,String description)
		{
			super(commandName,description);
		}
		public void execute() 
		{
			println("Retrieving the stuff");
			
		}
	}
	private class CommandFind extends UIControllerCommand
	{
		public CommandFind(String commandName,String description)
		{
			super(commandName,description);
		}
		public void execute() 
		{
			println("Finding the data.");
		}
		
	}
}
