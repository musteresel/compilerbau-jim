package jim.instruction;


import jim.instruction.math.Add;
import jim.type.IntegerType;


/** Add instruction for IntegerTypes.
 * */
public class IAdd extends Add
{
	/** Default constructor forwarding IntegerType as underlying type.
	 * */
	public IAdd()
	{
		super(IntegerType.class);
	}
}

