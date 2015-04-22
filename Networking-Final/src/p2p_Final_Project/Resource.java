package p2p_Final_Project;

import java.io.File;

public class Resource {
	private String description;
	private File location;
	private String mimeType;
	private ID resourceID;
	
	public Resource(ID id,String data)
	{
		this.resourceID = id;
		//TODO what does the data string have in it and what should we do with it
	}
	public Resource(ID id, String data,char delimeter)
	{
		this.resourceID = id;
		//TODO whats the delimeter for and same as above
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
		return description.contains(searchString);
		//TODO possibly wrong implementation
	}
}
