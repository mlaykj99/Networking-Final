package p2p_Final_Project;


public class ValuesInRange
{
	/*
		Jacob R. Braaten
		Feburary 12, 2015

		Class Variables
			bitMap
				an int a array that holds multiple bitMaps.
			maximumValueInRange
				the maximum value allowed to be held by the range
			minimumValueInRnage
				the minimum value allowed to be held by the range
		Constructors
			ValuesInRange(int minimumValueInrange, int maximumValueInRange)
				sets the minimum and maximum values and also sets the max size of the bitMap
				array to the (max-min)/(sizeOfASingleBitMap)
		Methods
			public int getMaximumValueInRange()
				returns the max value of the range
			public int getMinimumValueInRange()
				returns the min value of the range
			public void setFor(int value)
				changes the bitMap to say that the value sent
				in is contained in the bitMap
			public boolean contains(int value)
				determines if a value is contained in the bitMap
			public void clearFor(int value)
				changes the bitMap so a value is no longer
				contained in it
			private void checkValue(int value)
				error checking to be sure that the value
				is in the range of accepted values
			private int getBitNumberFor(int value)
				returns the bitNumber for the value sent in
			private int getElementNumberFor(int value)
				returns which position in the array of bitMaps
				the requested value is in
			private int getNumberOfBitsInEachBitMap()
				returns 32 because integers can have a
				maximum of 32 bits
		Modification History
			Feburary 12, 2015
				Original Version
	*/
	private int[] bitMap;
	private int maximumValueInRange;
	private int minimumValueInRange;

	public ValuesInRange(int minimumValueInRange, int maximumValueInRange)
	{
		if(maximumValueInRange < minimumValueInRange)
		{throw new IllegalArgumentException("The maximum value parameter " + "("+maximumValueInRange+")"
				+ " is less than the minimum value parameter " + "(" + minimumValueInRange + ")");}
		this.maximumValueInRange = maximumValueInRange;
		this.minimumValueInRange = minimumValueInRange;
		bitMap = new int[(this.maximumValueInRange - this.minimumValueInRange +1)/getNumberOfBitsInEachBitMap()+1];
	}
	public int getMaximumValueInRange()
	{//returns the max value of the range
		return this.maximumValueInRange;
	}
	public int getMinimumValueInRange()
	{//returns the min value of the range
		return this.minimumValueInRange;
	}
	public void setFor(int value)
	{//changes the bitMap to say that the value sent
	//in is contained in the bitMap
		int bitNumber = getBitNumberFor(value);
		int elementNumber = getElementNumberFor(value);

		checkValue(value);

		bitMap[elementNumber] = bitMap[elementNumber] | (1<<bitNumber);
	}
	public boolean contains(int value)
	{//determines if a value is contained in the bitMap
		int bitNumber = this.getBitNumberFor(value);
		int elementNumber = this.getElementNumberFor(value);

		checkValue(value);

		return ((bitMap[elementNumber] >>> (bitNumber))&1) == 1;
	}
	public void clearFor(int value)
	{//changes the bitMap so a value is no longer
	 //contained in it
		int bitNumber = getBitNumberFor(value);
		int elementNumber = getElementNumberFor(value);

		checkValue(value);

		bitMap[elementNumber] = bitMap[elementNumber] & ~(1<<bitNumber);

	}
	private void checkValue(int value)
	{//error checking to be sure that the value
	 //is in the range of accepted values
		if(value < this.minimumValueInRange){throw new IllegalArgumentException( "Parameter value ("+value+") "
				+ "is less than the minimum value in the range ("  +this.minimumValueInRange+")");}
		else if(value > this.maximumValueInRange){throw new IllegalArgumentException("Parameter value ("+value+") "
				+ "is greater than the maximum value in the range ("+this.maximumValueInRange+")");}
	}
	private int getBitNumberFor(int value)
	{//returns the bitNumber for the value sent in
		return (value-this.minimumValueInRange)%getNumberOfBitsInEachBitMap();
	}
	private int getElementNumberFor(int value)
	{//returns which position in the array of bitMaps
	 //the requested value is in
		return (value-this.minimumValueInRange)/getNumberOfBitsInEachBitMap();
	}
	private int getNumberOfBitsInEachBitMap()
	{//returns 32 because integers can have a
	 //maximum of 32 bits
		return 32;
	}
}

