package jim.instruction.branch.conditional.expression;


import jim.type.IntegerType;
import jim.instruction.branch.conditional.expression.BooleanExpression;


/** Implemented boolean expression.
 * */
public class Eq implements BooleanExpression
{
	/** Test whether integers a and b are equal.
	 *
	 * @param a Left hand side.
	 * @param b Right hand side.
	 * @return (a == b)
	 * */
	public boolean evaluate(IntegerType a, IntegerType b)
	{
		return (a.get_int() == b.get_int());
	}
}

