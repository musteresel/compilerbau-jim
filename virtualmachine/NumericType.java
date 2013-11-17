package virtualmachine;


import virtualmachine.Type;


/** General abstraction of numeric types.
 *
 * This interface defines general methods of a numeric type. It can be
 * used to implement generic mathematical operations.
 * */
public interface NumericType extends Type
{
	/** Add this value to another, returning a new value.
	 *
	 * @param o The other summand.
	 * @return The sum of this and o.
	 * */
	public abstract NumericType add(NumericType o);


	/** Subtract another value from this, returning a new value.
	 *
	 * @param o The value to subtract.
	 * @return The difference of this and o.
	 * */
	public abstract NumericType sub(NumericType o);
	
	
	/** Multiply this value and another, returning a new value.
	 *
	 * @param o The other factor.
	 * @return The product of this and o.
	 * */
	public abstract NumericType mul(NumericType o);
	
	
	/** Divide this value by another, returning a new value.
	 *
	 * @param o The divisor.
	 * @return The quotient of this and o.
	 * */
	public abstract NumericType div(NumericType o);
}

