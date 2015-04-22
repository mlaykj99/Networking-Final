package p2p_Final_Project;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;


//needs testing TODO
public class CommandProcessor {
	private Map<String,Command> 	commandRegistry;
	private Command 				noSuchCommand;
	private Command 				nothingEnteredCommand;
	
	public CommandProcessor(Command noSuchCommand,Command nothingEnteredCommand)
	{
		this.noSuchCommand = noSuchCommand;
		this.nothingEnteredCommand = nothingEnteredCommand;
		this.commandRegistry = new Hashtable<String,Command>();
	}
	public Command[] getAllCommands()
	{
		Collection<Command> allCommands;
		Iterator<Command> allCommandsIterator;
		Command[] result;
		
		allCommands = this.commandRegistry.values();
		allCommandsIterator = allCommands.iterator();
		result = new Command[allCommands.size()];
		
		for(int i = 0; allCommandsIterator.hasNext(); i++)
		{
			result[i] = allCommandsIterator.next();
		}
		
		return result;
	}
	public void register(Command command)
	{
		if(command != null)
		{
			commandRegistry.put(command.getCommandName(),command);
		}
	}
	public Command getCommand(String commandText)
	{
		return commandRegistry.get(commandText);
	}
}
