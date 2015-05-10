package p2p_Final_Project;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

//class is a singleton
public class ResourceManager {
	private static Map<ID,Resource> resourceDirectory;
	private static ArrayList<Resource> listOfMap;
	private static ResourceManager resourceManager = new ResourceManager();
	
	private ResourceManager()
	{
		this.resourceDirectory = new Hashtable<ID,Resource>();
		this.listOfMap = new ArrayList<Resource>();
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
		try 
		{
			RandomAccessFile raf;
			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
			String data;
			Resource temp;
			
			raf = new RandomAccessFile(file, "r");
			data = file.getPath() + " " + mimeTypesMap.getContentType(file) + " " + raf.readLine();
			temp = new Resource(ID.idFactory(),data);
			
			resourceDirectory.put(temp.getResourceID(), temp);
			listOfMap.add(temp);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public Resource[] getResourcesThatMatch(String searchString)
	{
		ArrayList<Resource> resources;
		
		resources = new ArrayList<Resource>();
		
		//Search Map for resources
		for(int i = 0; i < listOfMap.size(); i++)
		{
			if(listOfMap.get(i).getDescription().contains(searchString))
			{
				resources.add(listOfMap.get(i));
			}
		}
		
		return (Resource[])resources.toArray().clone();
	}
}



