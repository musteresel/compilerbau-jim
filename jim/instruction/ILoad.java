package jim.instruction;


import jim.instruction.memory.Load;
import jim.type.IntegerType;


/** Load instruction for IntegerTypes.
 * */
public class ILoad extends Load
{
	/** Default constructor forwarding index and IntegerType as
	 * underlying type.
	 * */
	public ILoad(int index)
	{
		super(IntegerType.class, index);
	}
}

