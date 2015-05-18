package p2p_Final_Project;

import java.io.File;
import java.io.FileNotFoundException;
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
		RandomAccessFile files;
		String filePath;
		String[] stuff;

		try 
		{
			files = new RandomAccessFile(file, "r");
			stuff = files.readLine().split(",");
			filePath = stuff[0];
			
			while(filePath != null)
			{
					MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
					String data;
					Resource temp;
					File resource = new File(filePath);
					
					data = resource.getPath() + " " + mimeTypesMap.getContentType(resource) + " " + stuff[1];
					System.out.println("Loaded: " + data);
					temp = new Resource(ID.idFactory(),data);
					
					resourceDirectory.put(temp.getResourceID(), temp);
					listOfMap.add(temp);
					
					filePath = files.readLine();
					if(filePath != null){stuff = filePath.split(",");
					filePath = stuff[0];}
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public Resource[] getResourcesThatMatch(String searchString)
	{
		ArrayList<Resource> resources;
		Resource[] result;
		resources = new ArrayList<Resource>();
		
		//Search Map for resources
		for(int i = 0; i < listOfMap.size(); i++)
		{
			if(listOfMap.get(i).matches(searchString))
			{
				resources.add(listOfMap.get(i));
			}
		}
		result = new Resource[resources.size()];
		resources.toArray(result);
		
		return result;
	}
	public Resource getResourceByID(ID id)
	{
		return resourceDirectory.get(id);
	}
}



