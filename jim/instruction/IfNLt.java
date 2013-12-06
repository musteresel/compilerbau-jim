package jim.instruction;


import jim.instruction.branch.conditional.IfN;
import jim.instruction.branch.conditional.expression.Lt;
import jim.type.IntegerType;


/** Integer Less than branch instruction.
 *
 * The branch is taken if the lhs integer is not less than the rhs one.
 * */
public class IfNLt extends IfN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Lt is forwarded as boolean
	 * expression, too.
	 * */
	public IfNLt(IntegerType destination)
	{
		super(new Lt(), destination);
	}
}

