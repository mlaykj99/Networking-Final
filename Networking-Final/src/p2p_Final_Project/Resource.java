package p2p_Final_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Resource {
	private String description;
	private RandomAccessFile location;
	private String mimeType;
	private ID resourceID;
	
	public Resource(ID id,String data)
	{
		String[] temp;
		
		this.resourceID = id;
		
		temp = data.split(" ");
		
		try {
			this.location = new RandomAccessFile(temp[0],"r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.mimeType = temp[1];
		this.description = temp[2];
	}
	public Resource(ID id, String data,char delimeter)
	{
		String[] temp;
		
		this.resourceID = id;

		temp = data.split("" + delimeter);
		
		try {
			this.location = new RandomAccessFile(temp[0],"r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.mimeType = temp[1];
		this.description = temp[2];
	}
	public String getDescription() {
		return this.description;
	}
	public RandomAccessFile getLocation() {
		return this.location;
	}
	public String getMimeType() {
		return this.mimeType;
	}
	public ID getResourceID() {
		return this.resourceID;
	}
	public long getSizeInBytes()
	{
		try {
			return location.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean matches(String searchString)
	{
		return  description.contains(searchString);
	}

}
