package jim.instruction;


import jim.instruction.branch.conditional.IfICmpN;
import jim.instruction.branch.conditional.expression.Le;


/** Integer Less equal branch instruction.
 *
 * The branch is taken if the lhs integer is not less or equal the rhs one.
 * */
public class IfICmpNLe extends IfICmpN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Le is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNLe(int destination)
	{
		super(new Le(), destination);
	}
}

