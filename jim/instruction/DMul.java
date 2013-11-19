package jim.instruction;


import jim.instruction.Mul;
import jim.type.DoubleType;


/** Mul instruction for DoubleTypes.
 * */
public class DMul extends Mul
{
	/** Default constructor forwarding DoubleType as underlying type.
	 * */
	public DMul()
	{
		super(DoubleType.class);
	}
}

