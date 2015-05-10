package p2p_Final_Project;

import java.io.File;

public class Resource {
	private String description;
	private File location;
	private String mimeType;
	private ID resourceID;
	
	public Resource(ID id,String data)
	{
		String[] temp;
		
		this.resourceID = id;
		
		temp = data.split(" ");
		this.location = new File(temp[0]);
		this.mimeType = temp[1];
		this.description = temp[2];
	}
	public Resource(ID id, String data,char delimeter)
	{
		String[] temp;
		
		this.resourceID = id;

		temp = data.split("" + delimeter);
		this.location = new File(temp[0]);
		this.mimeType = temp[1];
		this.description = temp[2];
	}
	public String getDescription() {
		return this.description;
	}
	public File getLocation() {
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
		return location.length();
	}
	public boolean matches(String searchString)
	{
		return  description.contains(searchString) && 
				mimeType.contains(searchString) && 
				location.getName().contains(searchString);
	}
}
