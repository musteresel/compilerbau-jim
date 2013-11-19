package jim.instruction;


import jim.instruction.IfICmpN;
import jim.type.IntegerType;


/** Integer Equal branch instruction.
 *
 * The branch is taken if the two integer values are not equal.
 * */
public class IfICmpNEq extends IfICmpN
{
	/** Implemented boolean expression.
	 * */
	protected static class Eq implements IfICmpN.BooleanExpression
	{
		/** Test whether integers a and b are equal.
		 *
		 * @param a Left hand side.
		 * @param b Right hand side.
		 * @return (a == b)
		 * */
		public boolean evaluate(IntegerType a, IntegerType b)
		{
			return (a.getInt() == b.getInt());
		}
	}


	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the inner class Eq is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNEq(int destination)
	{
		super(new Eq(), destination);
	}
}

