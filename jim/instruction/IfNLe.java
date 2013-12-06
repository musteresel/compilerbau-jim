package jim.instruction;


import jim.instruction.branch.conditional.IfN;
import jim.instruction.branch.conditional.expression.Le;
import jim.type.IntegerType;

/** Integer Less equal branch instruction.
 *
 * The branch is taken if the lhs integer is not less or equal the rhs one.
 * */
public class IfNLe extends IfN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Le is forwarded as boolean
	 * expression, too.
	 * */
	public IfNLe(IntegerType destination)
	{
		super(new Le(), destination);
	}
}

