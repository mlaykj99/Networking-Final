package p2p_Final_Project;

import java.util.ArrayList;

import javax.swing.JFrame;

import p2p_GUI.FrameBruh;

public class ResponsesToOurFinds {

	private ArrayList<ID> responses;
	private FrameBruh display;
	
	public ResponsesToOurFinds(FrameBruh frame)
	{
		this.responses = new ArrayList<ID>();
		this.display = frame;
		
		display.getTextArea2().setText(" Finds.");
	}
	//public boolean contains(ID )
	public void updateResponses(UDPMessage msg)
	{
		String hold;
		String delimeter;
		String[] stuff;
		if(!this.responses.contains(msg.getId1()))
		{
			this.responses.add(msg.getId1());
			//use parsing and fun stuff
			hold = new String(msg.getMessage(),ID.getIDLength(),msg.getMessage().length);
			delimeter = hold.charAt(0)+"";
			hold = hold.substring(1);
			stuff = hold.split(delimeter);
			
			//frame.update
			display.getTextArea2().setText(display.getTextArea2().getText() + "\n Type: " + stuff[0] + "\tLength: " + stuff[1] + "\tDescription: " + stuff[2]);			
		}
	}
	
	
	
}
