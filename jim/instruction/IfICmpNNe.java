package jim.instruction;


import jim.instruction.branch.conditional.IfICmpN;
import jim.instruction.branch.conditional.expression.Ne;
import jim.type.IntegerType;


/** Integer Not Equal branch instruction.
 *
 * The branch is taken if the two integer values are not "not equal".
 * */
public class IfICmpNNe extends IfICmpN
{
	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the class Ne is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNNe(IntegerType destination)
	{
		super(new Ne(), destination);
	}
}

