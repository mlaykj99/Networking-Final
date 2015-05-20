package p2p_Final_Project;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import p2p_GUI.FrameBruh;

public class ResponsesToOurFinds {

	//private HashMap<ID,Long> responses;
	private FrameBruh display;
	private ArrayList<UDPMessage> responses;
	public ResponsesToOurFinds(FrameBruh frame)
	{
		//this.responses = new HashMap<ID,Long>();
		this.display = frame;
		this.responses = new ArrayList<UDPMessage>();
		display.getTextArea2().setText(" Finds.\n");
	}
	//public boolean contains(ID )
	public void updateResponses(UDPMessage msg)
	{
		String hold;
		String delimiter;
		String[] stuff;
		if(!this.responses.contains(msg))
		{
			//System.out.println(msg.getId1());
			//System.out.println(msg.getId2());
			//System.out.println(msg.getTimeToLive());


			
			//use parsing and fun stuff
			/*System.out.println(msg.getMessage().length);
			for(int i = 0;i< msg.getMessage().length;i++)
			{
				System.out.println(msg.getMessage()[i]);
			}*/
			hold = new String(msg.getMessage(),ID.getIDLength(),msg.getMessage().length-ID.getIDLength());
			//System.out.println(new String(msg.getMessage(),ID.getIDLength(),msg.getMessage().length-ID.getIDLength()));
			//System.out.println();
			delimiter = hold.charAt(0)+"";
			//System.out.println(delimeter);
			hold = hold.substring(1);
			//System.out.println(hold);
			stuff = hold.split(delimiter);
			this.responses.add(msg);
			//frame.update
			display.getTextArea2().setText(display.getTextArea2().getText() + "\f Resrouce ID: " +responses.size()+"Type: " + stuff[0] + " | Length: " + Long.parseLong(stuff[1]) + " | Description: " + stuff[2] + "\n");			
		}
	}
	public Long lengthOfResource(int i)
	{
		byte[] bytes = this.responses.get(i).getMessage();
		String hold;
		String delimiter;
		String[] stuff;
		
		hold = new String(this.responses.get(i).getMessage(),ID.getIDLength(),this.responses.get(i).getMessage().length-ID.getIDLength());
		delimiter = hold.charAt(0)+"";
		hold = hold.substring(1);
		stuff = hold.split(delimiter);
		
		return Long.parseLong(stuff[1]);
	}
	public ID resourceID(int i)
	{
		return this.responses.get(i).getId1();
	}
}
