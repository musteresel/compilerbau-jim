package jim.instruction;


import jim.instruction.memory.Load;
import jim.type.DoubleType;
import jim.type.IntegerType;


/** Load instruction for DoubleTypes.
 * */
public class DLoad extends Load
{
	/** Default constructor forwarding index and DoubleType as
	 * underlying type.
	 * */
	public DLoad(IntegerType index)
	{
		super(DoubleType.class, index);
	}
}

