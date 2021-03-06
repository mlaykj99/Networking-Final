package p2p_Final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Resource {
	private String description;
	private RandomAccessFile raf;
	private File file;
	private String mimeType;
	private ID resourceID;
	private PartNumbers partNumbers;
	private long length;
	
	public Resource(ID id,String data)
	{
		String[] temp;
		
		this.resourceID = id;
		
		temp = data.split(" ");
		this.file = new File(temp[0]);
		try {
			this.raf = new RandomAccessFile(this.file,"r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.length = this.file.length();
		this.mimeType = temp[1];
		this.description = temp[2];
		partNumbers = new PartNumbers((int)Math.ceil((this.length/(double)456)));
	}
	public Resource(ID id, String data,char delimeter)
	{
		String[] temp;
		
		this.resourceID = id;

		temp = data.split("" + delimeter);
		
		try {
			this.raf = new RandomAccessFile(temp[0],"r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.mimeType = temp[1];
		this.description = temp[2];
	}
	public String getDescription() {
		return this.description;
	}
	public File getLocation() {
		return this.file;
	}
	public String getMimeType() {
		return this.mimeType;
	}
	public ID getResourceID() {
		return this.resourceID;
	}
	public byte[] getBytes(int partNumber)
	{
		System.out.println("PartNumber in getBytes() " + partNumber);
		if(partNumber > this.partNumbers.get() || partNumber < 0)
		{
			throw new IllegalArgumentException("Error part number reqeusted is out of range. "+this.getClass());
		}
		long start;
		long end;
		if(partNumber == partNumbers.get())
		{
			start = (partNumber-1)*456;
			end = this.length;
		}
		else
		{
			start = (partNumber-1)*456;
			end = start+456;
		}
		
		return getBytes(start,end);
		
	}
	public synchronized byte[] getBytes(long start,long end)
	{
		byte[] part;
		part = new byte[(int) (end-start)];
		try {
			this.raf.seek(start);
			this.raf.read(part);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return part;
		
	}
	public int getTotalParts()
	{
		return this.partNumbers.get();
	}
	public long getSizeInBytes()
	{
		try {
			return raf.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean matches(String searchString)
	{
		searchString = searchString.toLowerCase();
		searchString = searchString.trim();
		String name = file.getName().toLowerCase();
		String des = description.toLowerCase();
		
		//System.out.println("Comparing to "+ searchString);
		
		//System.out.println(name);
		//System.out.println(name.indexOf(searchString));
		return  name.indexOf(searchString) > -1 || des.indexOf(searchString) > -1;
	}

}
