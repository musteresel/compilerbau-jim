package jim.instruction;


import jim.instruction.math.Mul;
import jim.type.IntegerType;


/** Mul instruction for IntegerTypes.
 * */
public class IMul extends Mul
{
	/** Default constructor forwarding IntegerType as underlying type.
	 * */
	public IMul()
	{
		super(IntegerType.class);
	}
}

