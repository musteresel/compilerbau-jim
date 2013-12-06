package jim.instruction;


import jim.instruction.memory.Store;
import jim.type.DoubleType;
import jim.type.IntegerType;


/** Store instruction for DoubleTypes.
 * */
public class DStore extends Store
{
	/** Default constructor forwarding index and DoubleType as
	 * underlying type.
	 * */
	public DStore(IntegerType index)
	{
		super(DoubleType.class, index);
	}
}

