package jim.instruction.branch.conditional.expression;


import jim.type.IntegerType;
import jim.instruction.branch.conditional.expression.BooleanExpression;


/** Implemented boolean expression.
 * */
public class Gt implements BooleanExpression
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

