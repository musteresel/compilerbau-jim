package jim.instruction;


import jim.instruction.Div;
import jim.type.DoubleType;


/** Div instruction for DoubleTypes.
 * */
public class DDiv extends Div
{
	/** Default constructor forwarding DoubleType as underlying type.
	 * */
	public DDiv()
	{
		super(DoubleType.class);
	}
}

