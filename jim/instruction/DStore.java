package jim.instruction;


import jim.instruction.memory.Store;
import jim.type.DoubleType;


/** Store instruction for DoubleTypes.
 * */
public class DStore extends Store
{
	/** Default constructor forwarding index and DoubleType as
	 * underlying type.
	 * */
	public DStore(int index)
	{
		super(DoubleType.class, index);
	}
}

