package jim.instruction;


import jim.instruction.memory.Store;
import jim.type.IntegerType;
import jim.type.ArrayReference;


/** Store instruction for ArrayReferences.
 * */
public class AStore extends Store
{
	/** Default constructor forwarding index and ArrayReference as
	 * underlying type.
	 * */
	public AStore(IntegerType index)
	{
		super(ArrayReference.class, index);
	}
}

