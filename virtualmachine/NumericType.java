package virtualmachine;


import virtualmachine.Type;


/** General abstraction of numeric types.
 *
 * This interface defines general methods of a numeric type. It can be
 * used to implement generic mathematical operations.
 * */
public interface NumericType extends Type
{
	/** Set this value to the sum of the two operands.
	 *
	 * @param lhs Left hand side operand.
	 * @param rhs Right hand side operand.
	 * */
	public abstract void add(NumericType lhs, NumericType rhs);


	/** Set this value to the difference of the two operands.
	 *
	 * @param lhs Left hand side operand.
	 * @param rhs Right hand side operand.
	 * */
	public abstract void sub(NumericType lhs, NumericType rhs);
	
	
	/** Set this value to the product of the two operands.
	 *
	 * @param lhs Left hand side operand.
	 * @param rhs Right hand side operand.
	 * */
	public abstract void mul(NumericType lhs, NumericType rhs);
	
	
	/** Set this value to the quotient of the two operands.
	 *
	 * @param lhs Left hand side operand.
	 * @param rhs Right hand side operand.
	 * */
	public abstract void div(NumericType lhs, NumericType rhs);
}

