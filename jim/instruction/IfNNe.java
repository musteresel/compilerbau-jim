package jim.instruction;


import jim.instruction.branch.conditional.IfN;
import jim.instruction.branch.conditional.expression.Ne;
import jim.type.IntegerType;


/** Integer Not Equal branch instruction.
 *
 * The branch is taken if the two integer values are not "not equal".
 * */
public class IfNNe extends IfN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Ne is forwarded as boolean
	 * expression, too.
	 * */
	public IfNNe(IntegerType destination)
	{
		super(new Ne(), destination);
	}
}

