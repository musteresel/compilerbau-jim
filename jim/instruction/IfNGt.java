package jim.instruction;


import jim.instruction.branch.conditional.IfN;
import jim.instruction.branch.conditional.expression.Gt;
import jim.type.IntegerType;


/** Integer Greater than branch instruction.
 *
 * The branch is taken if the lhs integer is not greater than the rhs one.
 * */
public class IfNGt extends IfN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Gt is forwarded as boolean
	 * expression, too.
	 * */
	public IfNGt(IntegerType destination)
	{
		super(new Gt(), destination);
	}
}

