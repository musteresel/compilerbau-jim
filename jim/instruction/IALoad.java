package jim.instruction;


import jim.instruction.memory.ArrayLoad;
import jim.type.IntegerType;


public class IALoad extends ArrayLoad
{
	public IALoad()
	{
		super(IntegerType.class);
	}
}

