package p2p_GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import p2p_Final_Project.Command;
import p2p_Final_Project.PeerController;
import p2p_Final_Project.SynchronizedLinkedListQueue;
import p2p_Final_Project.UIController;
import javax.swing.JScrollPane;

public class FrameBruh extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	private JButton btnSend;
	private JTextArea textArea;
	private JTextArea textArea2;
	
	private SynchronizedLinkedListQueue ui;
	private SynchronizedLinkedListQueue peer;
	private UIController uic;
	private PeerController pc;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBruh frame = new FrameBruh();
					frame.setVisible(true);
					
					//Frame built. now start everything
					frame.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameBruh() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		setTitle("Networking Final");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		textField = new JTextField();
		textField.setBounds(10, 531, 565, 20);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(585, 530, 89, 23);
		btnSend.setActionCommand("Send");
		btnSend.addActionListener(this);
		
		textArea = new JTextArea(" Enter a command. (Type 'help' for a list of commands)");
		textArea.setBounds(0, 0, 664, 509);
		textArea.setEditable(false);
		
		textArea2 = new JTextArea();
		textArea2.setBounds(0, 0, 500, 509);
		textArea2.setEditable(false);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1184, 562);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 11, 664, 509);
		panel.add(scrollPane);
		
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		scrollPane2.setBounds(684, 11, 500, 509);
		panel.add(scrollPane2);
		
		panel.add(textField);
		panel.add(btnSend);
		//panel.add(textArea);
		//panel.add(textArea2);
		
		textField.addKeyListener(this);
		textField.setFocusable(true);
		textField.requestFocus();
	}
	
	private void start()
	{
		ui = new SynchronizedLinkedListQueue();
		peer = new SynchronizedLinkedListQueue();
		uic = new UIController(ui, peer);
		pc = new PeerController(ui, peer, this);
		uic.setFrame(this);
		
		pc.start();
		uic.start();
	}
	
	public JTextField getTextField()
	{
		return this.textField;
	}
	
	public JButton getBtnSend()
	{
		return this.btnSend;
	}
	
	public JTextArea getTextArea2()
	{
		return textArea2;
	}
	
	public void sendCommand()
	{
		String txt = textField.getText();
		String param = "";
		Command cmd;
		
		txt = txt.toLowerCase();
		if(!txt.equalsIgnoreCase("exit"))
		{
			if(txt.indexOf(" ") > -1)
			{
				param = txt.substring(txt.indexOf(" ")+1);
				txt = txt.substring(0,txt.indexOf(" "));
			}
			
			//send to ui controller
			cmd = uic.getCommandProcessor().getCommand(txt);
			
			if(cmd != null)
			{
				//display on textarea
				updateTextArea(txt +" "+ param);
				
				cmd.setParameters(param);
				cmd.run();
			}
			else
			{
				//Display not a command
				updateTextArea(txt + " is not a command. Type help for a list of commands.");
			}
			this.textField.setText("");
		}
		else
		{
			uic.getCommandProcessor().getCommand("exit").run();
			this.dispose();
		}
	}
	
	public void updateTextArea(String txt)
	{
		this.textArea.setText(this.textArea.getText() + "\n" + txt);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		
		if(cmd.equalsIgnoreCase("Send"))
		{
			sendCommand();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			sendCommand();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
