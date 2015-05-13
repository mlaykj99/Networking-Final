package p2p_Final_Project;

import java.io.File;

public class CommandCall
{
	private String command;
	private String parameters;
	private File file;
	
	public CommandCall(String command, String parameters, File file)
	{
		this.command = command;
		this.parameters = parameters;
		this.file = file;
	}
	
	public CommandCall(String command, String parameters)
	{
		this(command, parameters, null);
	}
	
	public CommandCall(String command)
	{
		this(command,null,null);
	}

	public String getCommand()
	{
		return command;
	}

	public String getParameters()
	{
		return parameters;
	}

	public File getFile()
	{
		return file;
	}
	
}
