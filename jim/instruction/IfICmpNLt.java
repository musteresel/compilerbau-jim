package jim.instruction;


import jim.instruction.IfICmpN;
import jim.type.IntegerType;


/** Integer Less than branch instruction.
 *
 * The branch is taken if the lhs integer is not less than the rhs one.
 * */
public class IfICmpNLt extends IfICmpN
{
	/** Implemented boolean expression.
	 * */
	protected static class Lt implements IfICmpN.BooleanExpression
	{
		/** Test whether integer a is less than b.
		 *
		 * @param a Left hand side.
		 * @param b Right hand side.
		 * @return (a less than b)
		 * */
		public boolean evaluate(IntegerType a, IntegerType b)
		{
			return (a.getInt() < b.getInt());
		}
	}


	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the inner class Lt is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNLt(int destination)
	{
		super(new Lt(), destination);
	}
}

