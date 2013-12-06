package jim.instruction;


import jim.instruction.branch.conditional.IfICmpN;
import jim.instruction.branch.conditional.expression.Gt;


/** Integer Greater than branch instruction.
 *
 * The branch is taken if the lhs integer is not greater than the rhs one.
 * */
public class IfICmpNGt extends IfICmpN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Gt is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNGt(int destination)
	{
		super(new Gt(), destination);
	}
}

