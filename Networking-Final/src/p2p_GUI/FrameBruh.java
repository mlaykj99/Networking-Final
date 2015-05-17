package p2p_GUI;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import p2p_Final_Project.Command;
import p2p_Final_Project.PeerController;
import p2p_Final_Project.SynchronizedLinkedListQueue;
import p2p_Final_Project.UIController;

public class FrameBruh extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JScrollPane panel;
	private JButton btnSend;
	private JTextArea textArea;
	
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
		setBounds(100, 100, 900, 600);
		setTitle("Networking Final");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		textField = new JTextField();
		textField.setBounds(10, 531, 765, 20);
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(785, 530, 89, 23);
		btnSend.setActionCommand("Send");
		
		textArea = new JTextArea("Enter a command. (Type 'help' for a list of commands)");
		textArea.setBounds(10, 11, 864, 509);
		textArea.setEditable(false);
		
		panel = new Pane(this);
		panel.setBounds(0, 0, 884, 562);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.add(textField);
		panel.add(btnSend);
		panel.add(textArea);
		
		textField.setFocusable(true);
		textField.requestFocus();
	}
	
	private void start()
	{
		ui = new SynchronizedLinkedListQueue();
		peer = new SynchronizedLinkedListQueue();
		uic = new UIController(ui, peer);
		pc = new PeerController(ui, peer);
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
				updateTextArea(txt + param);
				
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
}
