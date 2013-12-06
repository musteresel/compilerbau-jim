package jim.instruction;


import jim.instruction.branch.conditional.IfN;
import jim.instruction.branch.conditional.expression.Eq;
import jim.type.IntegerType;


/** Integer Equal branch instruction.
 *
 * The branch is taken if the two integer values are not equal.
 * */
public class IfNEq extends IfN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Eq is forwarded as boolean
	 * expression, too.
	 * */
	public IfNEq(IntegerType destination)
	{
		super(new Eq(), destination);
	}
}

