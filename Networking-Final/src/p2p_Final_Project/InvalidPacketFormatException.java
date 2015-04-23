package p2p_Final_Project;

import java.net.DatagramPacket;

public class InvalidPacketFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidPacketFormatException(String message,DatagramPacket datagram)
	{
		super(message + datagram.getAddress());
	}
}
