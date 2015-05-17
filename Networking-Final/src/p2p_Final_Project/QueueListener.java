package p2p_Final_Project;

import java.util.Scanner;

public class QueueListener implements Runnable
{
	private SynchronizedLinkedListQueue queue;
	private CommandProcessor commandProcessor;
	private boolean done;
	
	public QueueListener(SynchronizedLinkedListQueue queue, CommandProcessor commandProcessor)
	{
		this.queue = queue;
		this.commandProcessor = commandProcessor;
		this.done = false;
	}
	
	@Override
	public void run()
	{
		Command command;
		CommandCall call;
		
		while(!done)
		{
			if(!queue.isEmpty())
			{
				call = (CommandCall)queue.deQueue();
				command = commandProcessor.getCommand(call.getCommand());
				System.out.println(call.getCommand());
				System.out.println(command);
				command.setParameters(call.getParameters());
				command.setFile(call.getFile());
				command.run();
			}
			
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void stop()
	{
		this.done = true;
	}
	
	public void startAsThread()
	{
		new Thread(this).start();
	}

}
