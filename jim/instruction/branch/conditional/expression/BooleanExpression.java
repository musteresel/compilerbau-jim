package jim.instruction.branch.conditional.expression;


import jim.type.IntegerType;


/** Boolean expression interface.
 *
 * This interface defines the neccessary method an object needs to
 * implement to act as an boolean expression for this instruction.
 * */
public interface BooleanExpression
{
	/** Evaluation of the boolean expression.
	 *
	 * Feed two IntegerTypes to the boolean expression, returning
	 * a boolean.
	 *
	 * @param a Left hand side value.
	 * @param b Right hand side value.
	 * @return Boolean result value.
	 * */
	public abstract boolean evaluate(IntegerType a, IntegerType b);
}

