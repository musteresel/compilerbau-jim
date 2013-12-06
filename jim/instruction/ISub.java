package jim.instruction;


import jim.instruction.math.Sub;
import jim.type.IntegerType;


/** Sub instruction for IntegerTypes.
 * */
public class ISub extends Sub
{
	/** Default constructor forwarding IntegerType as underlying type.
	 * */
	public ISub()
	{
		super(IntegerType.class);
	}
}

