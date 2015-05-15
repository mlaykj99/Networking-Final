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

import p2p_Final_Project.PeerController;
import p2p_Final_Project.SynchronizedLinkedListQueue;
import p2p_Final_Project.UIController;

public class FrameBruh extends JFrame implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	private JButton btnSend;
	private JTextArea textArea;
	
	private static SynchronizedLinkedListQueue ui;
	private static SynchronizedLinkedListQueue peer;
	private static UIController uic;
	private static PeerController pc;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBruh frame = new FrameBruh();
					frame.setVisible(true);
					
					//Frame built. now start everything
					start();
					
					//uic.setFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameBruh() 
	{
		System.out.println("Starting Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setTitle("Networking Final");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 884, 562);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 531, 765, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(this);
		
		btnSend = new JButton("Send");
		btnSend.setBounds(785, 530, 89, 23);
		btnSend.addActionListener(this);
		btnSend.setActionCommand("Send");
		panel.add(btnSend);
		
		textArea = new JTextArea("Enter a command. (Type 'help' for a list of commands)");
		textArea.setBounds(10, 11, 864, 509);
		textArea.setEditable(false);
		panel.add(textArea);
		
		textField.setFocusable(true);
		textField.requestFocus();
		System.out.println("Ending Frame");
	}
	
	private static void start()
	{
		System.out.println("Starting other");
		ui = new SynchronizedLinkedListQueue();
		peer = new SynchronizedLinkedListQueue();
		uic = new UIController(ui, peer);
		pc = new PeerController(ui, peer);
		
		//pc.start();
		uic.start();
		System.out.println("Ending Other");
	}
	
	private void sendCommand()
	{
		String txt = textField.getText();
		
		if(!txt.equalsIgnoreCase("exit"))
		{
			//send to ui controller
			uic.getCommandProcessor().getCommand(txt).run();
			
			//display on textarea
			this.textArea.setText(this.textArea.getText() + "\n" + txt);
			this.textField.setText("");
		}
		else
		{
			uic.getCommandProcessor().getCommand("exit").run();
			this.dispose();
		}
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
