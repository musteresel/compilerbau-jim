package jim.instruction;


import jim.instruction.branch.conditional.IfICmpN;
import jim.instruction.branch.conditional.expression.Lt;


/** Integer Less than branch instruction.
 *
 * The branch is taken if the lhs integer is not less than the rhs one.
 * */
public class IfICmpNLt extends IfICmpN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Lt is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNLt(int destination)
	{
		super(new Lt(), destination);
	}
}

