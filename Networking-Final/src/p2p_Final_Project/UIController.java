package p2p_Final_Project;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

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
		
		try 
		{
			this.receiveFromPeer = new DatagramReceiver(new DatagramSocket(incomingPortNumber.get(),peerAddress.getAddress()),this.incomingPacketsFromPeerQueue,packetSize);
		}
		catch (SocketException e) 
		{
			System.out.println("UIContoller failed to open socket to receive from peer.");
			e.printStackTrace();
		}
		try 
		{
			this.sendToPeer = new DatagramSender(new DatagramSocket(outgoingPortNumber.get(),peerAddress.getAddress()),this.outgoingPacketsToPeerQueue,packetSize);
		} 
		catch (SocketException e) 
		{
			System.out.println("UIController failed to open socket to send to peer.");
			e.printStackTrace();
		}

		this.done = false;
		this.commandProcessor = new CommandProcessor(new CommandNone(),new CommandError());
	}
	public void start()
	{
		Scanner keyboard;
		String line;
		commandProcessor.register(new CommandJoin("join","Used to join the peer community."));
		commandProcessor.register(new CommandGet("get","Used to request items from the peer community."));
		commandProcessor.register(new CommandFind("find","Used to find items that others on the peer community have."));
		commandProcessor.register(new CommandExit("exit","Used to exit the peer community."));
		commandProcessor.register(new CommandHelp("help","Shows user available commands"));
		keyboard = new Scanner(System.in);
		
		System.out.println("Please enter a command. For help type help");
		line = keyboard.nextLine();
		commandProcessor.getCommand(line).run();	
		
		while(!line.equals("exit"))
		{
			System.out.println("Please enter a command. For help type help");
			line = keyboard.nextLine();
			commandProcessor.getCommand(line).run();
		}
		commandProcessor.getCommand("exit").run();
		keyboard.close();
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
		
		public void run() 
		{
			Command[] allCommands = getCommandProcessor().getAllCommands();
			for(int i = 0;i < allCommands.length; i++)
			{
				println(allCommands.toString());
			}
			
		}
	}
	private class CommandError extends UIControllerCommand
	{
		public CommandError()
		{
			
		}
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandNone extends UIControllerCommand
	{
		public CommandNone()
		{
			super();
		}

		public void run() {
			println("Sorry that command does not exist. Type help for a list of commands.");
		}
	}
	private class CommandJoin extends UIControllerCommand
	{
		public CommandJoin(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
				println("Joining the Peer Group");
		}
	}
	private class CommandGet extends UIControllerCommand
	{
		public CommandGet(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
			// TODO Auto-generated method stub
			println("Retrieving the stuff");
		}
	}
	private class CommandFind extends UIControllerCommand
	{
		public CommandFind(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandExit extends UIControllerCommand
	{
		public CommandExit(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
			System.out.println("Thank you come again.");
		}
		
	}
}
