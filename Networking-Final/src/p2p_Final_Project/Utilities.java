package p2p_Final_Project;

import java.nio.ByteBuffer;

public class Utilities {

	public static byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}
	public static byte[] arrayCopy(byte[] one, byte[] two, byte[] three, byte[] four,byte[]five)
	{
		return arrayCopy(arrayCopy(one,two,three,four),five);
	}
	public static byte[] arrayCopy(byte[] one, byte[] two, byte[] three, byte[] four)
	{
		return arrayCopy(arrayCopy(one,two,three),four);
	}
	public static byte[] arrayCopy(byte[] one, byte[] two, byte[] three)
	{
		return arrayCopy(arrayCopy(one,two),three);
		
	}
	public static byte[] arrayCopy(byte[] one, byte[] two)
	{
		byte[] combined;
		
		combined = new byte[one.length+two.length];
		
		System.arraycopy(combined, 0, one, 0, one.length);
		System.arraycopy(combined, one.length, two, 0, two.length);
		
		return combined;
		
	}
	public static int randomInt()
	{
		int random;
		random =  (int) (Math.random()*3)+3;
		
		return random;
	}
	/*public static int bytesToInt(byte[] bytes)
	{
		int result = 0;
	    for (int i=0; i<4; i++) {
	      result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
	    }
	    return result;
	}
	*/
}
