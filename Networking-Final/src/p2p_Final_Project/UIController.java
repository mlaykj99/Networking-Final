package p2p_Final_Project;

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
		//TODO
	}
	public void start()
	{
		//TODO
	}









	
	
	
	
	private abstract class UIControllerCommand extends Command
	{
		public UIControllerCommand()
		{
			//TODO
		}
		public UIControllerCommand(String commandName,String Description)
		{
			//TODO
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
			//TODO
		}
	}
	private class CommandHelp extends UIControllerCommand
	{
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandError extends UIControllerCommand
	{
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandNone extends UIControllerCommand
	{
	
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandStart extends UIControllerCommand
	{
	
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandGet extends UIControllerCommand
	{

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
	}
	private class CommandFind extends UIControllerCommand
	{
		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
