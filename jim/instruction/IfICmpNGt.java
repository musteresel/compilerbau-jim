package jim.instruction;


import jim.instruction.IfICmpN;
import jim.type.IntegerType;


/** Integer Greater than branch instruction.
 *
 * The branch is taken if the lhs integer is not greater than the rhs one.
 * */
public class IfICmpNGt extends IfICmpN
{
	/** Implemented boolean expression.
	 * */
	protected static class Gt implements IfICmpN.BooleanExpression
	{
		/** Test whether integer a is greater than b.
		 *
		 * @param a Left hand side.
		 * @param b Right hand side.
		 * @return (a greater than b)
		 * */
		public boolean evaluate(IntegerType a, IntegerType b)
		{
			return (a.getInt() > b.getInt());
		}
	}


	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the inner class Gt is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNGt(int destination)
	{
		super(new Gt(), destination);
	}
}

