package jim.instruction;


import jim.instruction.call.BaseReturn;
import jim.type.IntegerType;


public class IReturn extends BaseReturn
{
	public IReturn()
	{
		super(new IntegerType());
	}
}

