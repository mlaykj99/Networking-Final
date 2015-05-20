package p2p_Final_Project;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import p2p_GUI.FrameBruh;

public class ResponsesToOurFinds {

	private HashMap<ID,Long> responses;
	private FrameBruh display;
	
	public ResponsesToOurFinds(FrameBruh frame)
	{
		this.responses = new HashMap<ID,Long>();
		this.display = frame;
		
		display.getTextArea2().setText(" Finds.\n");
	}
	//public boolean contains(ID )
	public void updateResponses(UDPMessage msg)
	{
		String hold;
		String delimeter;
		String[] stuff;
		if(!this.responses.containsKey(msg.getId1()))
		{
			System.out.println(msg.getId1());
			System.out.println(msg.getId2());
			System.out.println(msg.getTimeToLive());


			
			//use parsing and fun stuff
			/*System.out.println(msg.getMessage().length);
			for(int i = 0;i< msg.getMessage().length;i++)
			{
				System.out.println(msg.getMessage()[i]);
			}*/
			hold = new String(msg.getMessage(),ID.getIDLength(),msg.getMessage().length-ID.getIDLength());
			System.out.println(new String(msg.getMessage(),ID.getIDLength(),msg.getMessage().length-ID.getIDLength()));
			System.out.println();
			delimeter = hold.charAt(0)+"";
			System.out.println(delimeter);
			hold = hold.substring(1);
			System.out.println(hold);
			stuff = hold.split(delimeter);
			this.responses.put(msg.getId1(),Long.parseLong(stuff[1]));
			//frame.update
			display.getTextArea2().setText(display.getTextArea2().getText() + "\f Type: " + stuff[0] + " | Length: " + Long.parseLong(stuff[1]) + " | Description: " + stuff[2] + "\n");			
		}
	}
	public Long lengthOfResource(ID id)
	{
		return this.responses.get(id);
	}
}
