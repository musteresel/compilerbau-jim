package jim.instruction;


import jim.instruction.memory.Store;
import jim.type.IntegerType;


/** Store instruction for IntegerTypes.
 * */
public class IStore extends Store
{
	/** Default constructor forwarding index and IntegerType as
	 * underlying type.
	 * */
	public IStore(IntegerType index)
	{
		super(IntegerType.class, index);
	}
}

