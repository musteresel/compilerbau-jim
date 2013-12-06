package jim.instruction;


import jim.instruction.branch.conditional.IfICmpN;
import jim.instruction.branch.conditional.expression.Ge;


/** Integer Greater equal branch instruction.
 *
 * The branch is taken if the lhs integer is not greater or equal the rhs one.
 * */
public class IfICmpNGe extends IfICmpN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Ge is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNGe(int destination)
	{
		super(new Ge(), destination);
	}
}

