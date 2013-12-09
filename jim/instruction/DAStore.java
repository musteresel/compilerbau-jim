package jim.instruction;


import jim.instruction.memory.ArrayStore;
import jim.type.DoubleType;


public class DAStore extends ArrayStore
{
	public DAStore()
	{
		super(DoubleType.class);
	}
}

