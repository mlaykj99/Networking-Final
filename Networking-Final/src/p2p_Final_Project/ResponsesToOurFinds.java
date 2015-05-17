package p2p_Final_Project;

import java.util.ArrayList;

import javax.swing.JFrame;

public class ResponsesToOurFinds {

	private ArrayList<ID> responses;
	private JFrame display;
	
	public ResponsesToOurFinds(JFrame frame)
	{
		this.responses = new ArrayList<ID>();
		this.display = frame;
	}
	
	public void updateResponses(UDPMessage msg)
	{
		String hold;
		String delimeter;
		String[] stuff;
		if(!this.responses.contains(msg.getId1()))
		{
			this.responses.add(msg.getId1());
			//frame.update
			//use parsing and fun stuff
			hold = new String(msg.getMessage(),ID.getIDLength(),msg.getMessage().length);
			delimeter = hold.charAt(0)+"";
			hold = hold.substring(1);
			stuff = hold.split(delimeter);
		}
	}
	
	
	
}
