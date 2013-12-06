package jim.instruction;


import jim.instruction.branch.conditional.IfN;
import jim.instruction.branch.conditional.expression.Ge;
import jim.type.IntegerType;


/** Integer Greater equal branch instruction.
 *
 * The branch is taken if the lhs integer is not greater or equal the rhs one.
 * */
public class IfNGe extends IfN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Ge is forwarded as boolean
	 * expression, too.
	 * */
	public IfNGe(IntegerType destination)
	{
		super(new Ge(), destination);
	}
}

