package p2p_Final_Project;

import java.io.File;

public class Resource {
	private String description;
	private File location;
	private String mimeType;
	private ID resourceID;
	
	public Resource(ID id,String data)
	{
		
	}
	public Resource(ID id, String data,char delimeter)
	{
		
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
		return 0;
	}
	public boolean matches(String searchString)
	{
		return false;
	}
}
