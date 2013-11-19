package jim.instruction;


import jim.instruction.IfICmpN;
import jim.type.IntegerType;


/** Integer Greater equal branch instruction.
 *
 * The branch is taken if the lhs integer is not greater or equal the rhs one.
 * */
public class IfICmpNGe extends IfICmpN
{
	/** Implemented boolean expression.
	 * */
	protected static class Ge implements IfICmpN.BooleanExpression
	{
		/** Test whether integer a is greater or equal b.
		 *
		 * @param a Left hand side.
		 * @param b Right hand side.
		 * @return (a greater or equal b)
		 * */
		public boolean evaluate(IntegerType a, IntegerType b)
		{
			return (a.getInt() >= b.getInt());
		}
	}


	/** Constructor forwarding destination to super class.
	 *
	 * An instance of the inner class Ge is forwarded as boolean
	 * expression, too.
	 * */
	public IfICmpNGe(int destination)
	{
		super(new Ge(), destination);
	}
}

