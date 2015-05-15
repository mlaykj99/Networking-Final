package p2p_Final_Project;

import p2p_GUI.FrameBruh;

public class UIController
{
	private CommandProcessor 	commandProcessor;
	private boolean 			done;
	private SynchronizedLinkedListQueue uiQueue;
	private SynchronizedLinkedListQueue peerQueue;
	//private Scanner keyboard;
	private QueueListener queueListener;
	private FrameBruh frame;
	
	public UIController(SynchronizedLinkedListQueue uiQueue, SynchronizedLinkedListQueue peerQueue)
	{
		this.uiQueue = uiQueue;
		this.peerQueue = peerQueue;
		this.done = false;
		this.commandProcessor = new CommandProcessor(new CommandNone(),new CommandError());
		//this.keyboard = new Scanner(System.in);
		this.queueListener = new QueueListener(uiQueue, commandProcessor);
	}
	public void start()
	{
		String line;
		commandProcessor.register(new CommandJoin("join","Used to join the peer community."));
		commandProcessor.register(new CommandGet("get","Used to request items from the peer community."));
		commandProcessor.register(new CommandFind("find","Used to find items that others on the peer community have."));
		commandProcessor.register(new CommandExit("exit","Used to exit the peer community."));
		commandProcessor.register(new CommandHelp("help","Shows user available commands"));
		commandProcessor.register(new CommandClear("clear","Clears all resources found by find requests"));
		commandProcessor.register(new CommandSave("save","Save a resource"));
		commandProcessor.register(new CommandAllSearch("search","Displays a list of found requests"));
		
		this.queueListener.startAsThread();
		
		/*System.out.println("Please enter a command. For help type help");
		line = keyboard.nextLine();
		commandProcessor.getCommand(line).run();
		
		while(!line.equalsIgnoreCase("exit"))
		{
			System.out.println("Please enter a command. For help type help");
			line = keyboard.nextLine();
			commandProcessor.getCommand(line).run();
		}
		commandProcessor.getCommand("exit").run();
		keyboard.close();*/
		System.out.println("In UI");
	}
	
	public void setFrame(FrameBruh window)
	{
		this.frame = window;
	}
	
	private void insert(CommandCall cc)
	{
		peerQueue.enQueue(cc);
	}
	
	public CommandProcessor getCommandProcessor()
	{
		return this.commandProcessor;
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
			println("Sorry an error occured.");
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
		public void run() 
		{
			CommandCall cc = new CommandCall("join");
			peerQueue.enQueue(cc);
		}
	}
	
	private class CommandGet extends UIControllerCommand
	{
		public CommandGet(String commandName,String description)
		{
			super(commandName,description);
		}
		
		public void run()
		{
			CommandCall test = new CommandCall("get", "dogs");
			insert(test);
			System.out.println("Inserted into peer queue.");
		}
	}
	
	private class CommandFind extends UIControllerCommand
	{
		
		public CommandFind(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run()
		{
			insert(new CommandCall("find","thingToFind"));
		}
	}
	
	private class CommandClear extends UIControllerCommand
	{
		
		public CommandClear(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run()
		{
			RequestManager.newInstance().clearRequestDirectory();
		}
	}
	
	private class CommandSave extends UIControllerCommand
	{
		
		public CommandSave(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run()
		{
			System.out.println("Saved!");
		}
	}
	
	private class CommandAllSearch extends UIControllerCommand
	{
		
		public CommandAllSearch(String commandName,String description)
		{
			super(commandName,description);
		}
		public void run() {
			System.out.println("will return a resource list");
			
		}
	}
	
	private class CommandExit extends UIControllerCommand
	{
		public CommandExit(String commandName,String description)
		{
			super(commandName,description);
		}
		
		public void run()
		{
			System.out.println("Thank you come again.");
			//tell peer controller to be done.
			insert(new CommandCall("stop"));
			
			queueListener.stop();
		}
		
	}
}
