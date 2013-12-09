package jim.instruction;


import jim.instruction.memory.ArrayStore;
import jim.type.IntegerType;


public class IAStore extends ArrayStore
{
	public IAStore()
	{
		super(IntegerType.class);
	}
}

