package jim.instruction;


import jim.instruction.IfICmpN;
import jim.type.IntegerType;


/** Integer Not Equal branch instruction.
 *
 * The branch is taken if the two integer values are not "not equal".
 * */
public class IfICmpNNe extends IfICmpN
{
	/** Implemented boolean expression.
	 * */
	protected static class Ne implements IfICmpN.BooleanExpression
	{
		/** Test whether integers a and b are not equal.
		 *
		 * @param a Left hand side.
		 * @param b Right hand side.
		 * @return (a != b)
		 * */
		public boolean evaluate(IntegerType a, IntegerType b)
		{
			return (a.getInt() != b.getInt());
		}
	}


	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the inner class Ne is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNNe(int destination)
	{
		super(new Ne(), destination);
	}
}

