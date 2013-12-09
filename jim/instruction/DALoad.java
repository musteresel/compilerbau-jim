package jim.instruction;


import jim.instruction.memory.ArrayLoad;
import jim.type.DoubleType;


public class DALoad extends ArrayLoad
{
	public DALoad()
	{
		super(DoubleType.class);
	}
}

