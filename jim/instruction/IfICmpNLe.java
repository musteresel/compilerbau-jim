package jim.instruction;


import jim.instruction.IfICmpN;
import jim.type.IntegerType;


/** Integer Less equal branch instruction.
 *
 * The branch is taken if the lhs integer is not less or equal the rhs one.
 * */
public class IfICmpNLe extends IfICmpN
{
	/** Implemented boolean expression.
	 * */
	protected static class Le implements IfICmpN.BooleanExpression
	{
		/** Test whether integer a is less or equal b.
		 *
		 * @param a Left hand side.
		 * @param b Right hand side.
		 * @return (a less or equal b)
		 * */
		public boolean evaluate(IntegerType a, IntegerType b)
		{
			return (a.getInt() <= b.getInt());
		}
	}


	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the inner class Le is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNLe(int destination)
	{
		super(new Le(), destination);
	}
}

