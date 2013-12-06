package jim.instruction;


import jim.instruction.branch.conditional.IfICmpN;
import jim.instruction.branch.conditional.expression.Eq;


/** Integer Equal branch instruction.
 *
 * The branch is taken if the two integer values are not equal.
 * */
public class IfICmpNEq extends IfICmpN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Eq is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNEq(int destination)
	{
		super(new Eq(), destination);
	}
}

