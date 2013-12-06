package jim.instruction.branch.conditional.expression;


import jim.type.IntegerType;
import jim.instruction.branch.conditional.expression.BooleanExpression;


/** Implemented boolean expression.
 * */
public class Le implements BooleanExpression
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

