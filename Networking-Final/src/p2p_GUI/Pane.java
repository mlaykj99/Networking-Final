package p2p_GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Pane extends JScrollPane implements ActionListener, KeyListener
{
	private FrameBruh frame;
	
	public Pane(FrameBruh f)
	{
		this.frame = f;
		frame.getTextField().addKeyListener(this);
		frame.getBtnSend().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		
		if(cmd.equalsIgnoreCase("Send"))
		{
			frame.sendCommand();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			frame.sendCommand();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
