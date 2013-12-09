package jim.instruction;


import jim.instruction.memory.Load;
import jim.type.IntegerType;
import jim.type.ArrayReference;


/** Load instruction for ArrayReferences.
 * */
public class ALoad extends Load
{
	/** Default constructor forwarding index and ArrayReference as
	 * underlying type.
	 * */
	public ALoad(IntegerType index)
	{
		super(ArrayReference.class, index);
	}
}

