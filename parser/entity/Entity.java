package parser.entity;


/** Thin layer of abstraction over tokens.
 *
 * This interface is used to provide a base for different entities a single
 * token can evaluate to.
 * */
public interface Entity
{
	/** Returns the associated value, if any.
	 *
	 * @return Value of the entity.
	 * */
	public abstract int getValue();
}

