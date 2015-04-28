package p2p_Final_Project;

public class UIController_CommandTester {
	public static void main(String[] args)
	{
		UIController uic;
		
		uic = new UIController(new PortNumberForReceiving(12345), new PortNumberForSending(12354), 0);
		
		uic.start();
	}
}
