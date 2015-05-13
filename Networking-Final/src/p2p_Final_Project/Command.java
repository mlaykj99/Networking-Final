package p2p_Final_Project;

import java.io.File;

public abstract class Command implements Runnable, Comparable<Command>, Cloneable {
	
	private String commandName;
	private String description;
	private String parameters;
	private File file;
	
	public Command(String commandName,String description)
	{
		if(commandName == null)
		{
			throw new IllegalArgumentException("Error commandName in class "+ this.getClass()+ " cannot be null.");
		}
		if(description == null)
		{
			throw new IllegalArgumentException("Error description in class "+this.getClass()+" cannot be null");
		}
		this.commandName = commandName;
		this.description = description;
		this.file = null;
	}
	
	public Command()
	{
		
	}
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	public boolean equals(String text)
	{
		//not needed for current implementation
		return false;
	}
	public boolean equals(Object other)
	{
		//not needed for current implementation
		return false;
	}
	
	public int hashCode()
	{
		//not needed for current implementation
		return 0;
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
	public void setParameters(String parameters)
	{
		this.parameters = parameters;
	}
	
	public void setFile(File file)
	{
		this.file = file;
	}
	
	public String toString()
	{
		return "Command Name: " + commandName + " Description: " + description;
	}

	public int compareTo(Command other) {
		//not needed for current implementation
		return 0;
	}

	@Override
	public abstract void run();
	
	public String getCommandName()
	{
		return this.commandName;
	}
	public String getDescription() {
		return this.description;
	}

	public String getParameters() {
		return this.parameters;
	}
	public File getFile() {
		return this.file;
	}
	public String[] getParameters(String delimeter)
	{
		if(delimeter == null)
		{
			throw new IllegalArgumentException("Error: the delimeter cannot be null in class "+this.getClass());
		}
		
		return this.parameters.split(delimeter);
	}


}
