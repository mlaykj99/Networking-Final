package p2p_Final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Lock;

import javax.swing.JFileChooser;

public class GetResourceRequest extends Request implements Runnable {

	//private ID resourceID;
	private ID resourceID;
	private Long length;
	private ID requestID;
	private PartNumbers numberOfParts;
	private ValuesInRange arrived;
	private OutgoingPacketQueue queue;
	private int currentPartNumber;
	private File file;
	private RandomAccessFile raf;
	private JFileChooser dialogue;
	private  String defaultName = "default";
	private boolean run;
	
	public GetResourceRequest(ID resourceID,ID requestID,Long length)
	{
		super(requestID);
		//this.resourceID = resourceID;
		this.requestID = requestID;
		this.resourceID = requestID;
		this.length = length;
		this.numberOfParts = new PartNumbers((int)Math.ceil((this.length/(double)456)));
		this.arrived = new ValuesInRange(1,this.numberOfParts.get());
		this.queue = queue;
		this.currentPartNumber = 1;
		this.dialogue =  new JFileChooser();
		this.run = true;
		
		
		dialogue.setDialogType(JFileChooser.FILES_ONLY);
		file = null;
		
		dialogue.setApproveButtonText("Save");
		dialogue.setDialogTitle("Save it!");
		dialogue.setSelectedFile(new File(this.defaultName));
	
		if(dialogue.showDialog(null, null) == JFileChooser.APPROVE_OPTION)
		{
			file = dialogue.getSelectedFile();
			try {
				this.raf = new RandomAccessFile(file,"rws");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
		{
			this.run = false;
		}
		
		
		
		
		
		
	}
	@Override
	public void run() {
		// request parts
		UDPMessage message;
		byte[] partNumber;
		byte[] bytes;
		for(int i=1; i<=this.numberOfParts.get() && this.run;i++)
		{
			synchronized(this.arrived)
			{
				System.out.println("Filling the file with stuff");
				
				partNumber = ByteBuffer.allocate(4).putInt(i).array();
				System.out.println(partNumber[0]);
				System.out.println(partNumber[1]);
				System.out.println(partNumber[2]);
				System.out.println(partNumber[3]);
				
				System.out.println(ByteBuffer.wrap(partNumber).getInt());

				bytes = Utilities.arrayCopy(ID.idFactory().getBytes(),partNumber);
				
				System.out.println("This is what in the message for get: "+new String(bytes));
				
				message = new UDPMessage(this.requestID,this.resourceID,new TimeToLive(),bytes);
				
				GossipPartners.getInstance().send(message);
				
				while(!this.arrived.contains(i)){try{this.arrived.wait();}catch (InterruptedException ie){}}
			}
		}
		
		try {
			this.raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	@Override
	public void updateRquest(UDPMessage udpMessage) 
	{
		// gets called with parts and fills the file up;
		
		byte[] buffer;
		DatagramPacket datagramPacket;
		long endingByte;
		long startingByte;
		int partNumber;
		byte[] partNum = new byte[4];
		
		System.arraycopy(udpMessage.getMessage(),ID.getIDLength(),partNum,0,4);
		
		partNumber = ByteBuffer.wrap(partNum).getInt();
		
		System.out.println("Stuff arrived for file");
		
		synchronized(this.arrived)
		{
			if(!this.arrived.contains(partNumber))
			{
				this.arrived.setFor(partNumber);
						
				if(partNumber == numberOfParts.get())
				{
					startingByte = (partNumber-1)*456;
					endingByte = this.length;
				}
				else
				{
					startingByte = (partNumber-1)*456;
					endingByte = startingByte+456;
				}
				
				
				System.out.println("startingByte");
				
				for(int j = 0;j < udpMessage.getMessage().length;j++)
				{
					System.out.println("Byte in message before copy: "+ j+"   "+udpMessage.getMessage()[j]);
				}
				buffer = new byte[(int) (endingByte-startingByte)];
				System.out.println("Buffer length before copy: "+buffer.length);
				System.arraycopy(udpMessage.getMessage(), 20, buffer, 0, buffer.length);

				for(int j = 0;j < udpMessage.getMessage().length;j++)
				{
					System.out.println("Byte in message: "+ j+"   "+udpMessage.getMessage()[j]);
				}
				
				
				
				System.out.println("Starting byte: "+startingByte);
				System.out.println("Bufffer length: "+buffer.length);
				for(int j = 0;j < buffer.length;j++)
				{
					System.out.println("Byte in buffer: "+ j+"   "+buffer[j]);
				}
				
				try {
					this.raf.seek(startingByte);
					this.raf.write(buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			
			
			
			
			this.arrived.notify();
		}
		
	}
	public void startAsThread()
	{
		new Thread(this).start();		
	}

}
