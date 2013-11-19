package jim.instruction;


import jim.instruction.Div;
import jim.type.IntegerType;


/** Div instruction for IntegerTypes.
 * */
public class IDiv extends Div
{
	/** Default constructor forwarding IntegerType as underlying type.
	 * */
	public IDiv()
	{
		super(IntegerType.class);
	}
}

