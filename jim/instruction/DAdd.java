package jim.instruction;


import jim.instruction.math.Add;
import jim.type.DoubleType;


/** Add instruction for DoubleTypes.
 * */
public class DAdd extends Add
{
	/** Default constructor forwarding DoubleType as underlying type.
	 * */
	public DAdd()
	{
		super(DoubleType.class);
	}
}

