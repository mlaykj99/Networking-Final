package p2p_Final_Project;

public class TesterForMethods
{
	public static void main(String[] args)
	{
		ID id1 = ID.idFactory();
		ID id2 = ID.idFactory();
		
		//Hex test
		System.out.println("Hex test: " + id1.getAsHex());
		System.out.println("Hex test: " + id2.getAsHex());
		
		//To String
		System.out.println("To String: " + id1.toString());
		
		//Equals test
		System.out.println(id1.equals(id2));
		System.out.println(id1.equals(id1));
		
		//Zero test
		System.out.println(ID.getZeroID().isZero());
		System.out.println(id1.isZero());
		
		//Reference test
		System.out.println(id1.getBytes());
	}
}
