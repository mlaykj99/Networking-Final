package p2p_Final_Project;

public abstract class Command<T> implements ActionInterface, Comparable<T>, Cloneable {
	
	private String commandName;
	private String description;
	private String parameters;
	
	public Command(String commandName,String description)
	{
		this.commandName = commandName;
		this.description = description;
		//What does parameters do TODO
	}
	
	public Command()
	{
		//Figure out what to do here
	}
	public Object clone()
	{
		//TODO
		//new Command(this.commandName,this.description);
		return null;
	}
	public boolean equals(String text)
	{
		//TODO
		return false;
	}
	public boolean equals(Object other)
	{
		//TODO
		return false;
	}
	
	public int hashCode()
	{
		//TODO
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
		// TODO Auto-generated method stub
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
