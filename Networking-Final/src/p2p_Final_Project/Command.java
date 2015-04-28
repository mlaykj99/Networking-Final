package p2p_Final_Project;

public abstract class Command implements ActionInterface, Comparable<Command>, Cloneable {
	
	private String commandName;
	private String description;
	private String parameters;
	
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
	
	public String toString()
	{
		//TODO
		return null;
	}

	public int compareTo(Command other) {
		//not needed for current implementation
		return 0;
	}

	@Override
	public abstract void execute();
	
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
	public String[] getParameters(String delimeter)
	{
		//TODO
		return null;
	}


}
