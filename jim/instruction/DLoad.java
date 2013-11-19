package jim.instruction;


import jim.instruction.Load;
import jim.type.DoubleType;


/** Load instruction for DoubleTypes.
 * */
public class DLoad extends Load
{
	/** Default constructor forwarding index and DoubleType as
	 * underlying type.
	 * */
	public DLoad(int index)
	{
		super(DoubleType.class, index);
	}
}

