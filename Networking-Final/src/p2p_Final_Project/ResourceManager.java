package p2p_Final_Project;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

//class is a singleton
public class ResourceManager {
	private Map<ID,Resource> resourceDirectory;
	private static ResourceManager resourceManager = new ResourceManager();
	
	private ResourceManager()
	{
		this.resourceDirectory = new Hashtable<ID,Resource>();
	}
	public static ResourceManager getInstance()
	{
		return resourceManager;
	}
	public static ResourceManager newInstance()
	{
		return getInstance();
	}
	public static void loadResourcesFrom(File file)
	{
		//TODO file reader
	}
	public Resource[] getResourcesThatMatch(String searchString)
	{
		//TODO ask jarvis
		return null;
	}
}



