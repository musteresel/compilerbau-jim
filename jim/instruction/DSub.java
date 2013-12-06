package jim.instruction;


import jim.instruction.math.Sub;
import jim.type.DoubleType;


/** Sub instruction for DoubleTypes.
 * */
public class DSub extends Sub
{
	/** Default constructor forwarding DoubleType as underlying type.
	 * */
	public DSub()
	{
		super(DoubleType.class);
	}
}

