package parser;


import virtualmachine.Type;
import parser.ValueStub;
import parser.ParseUnit;


/** A stub containing an already evaluated value.
 *
 * This class is used to hold direct specified values from the source.
 * Evaluation simply returns the contained value.
 * */
public class EvaluatedValueStub implements ValueStub
{
	/** The already evaluated value.
	 * */
	protected Type value;


	/** Constructor.
	 *
	 * @param value The value which is represented by this class.
	 * */
	public EvaluatedValueStub(Type value)
	{
		this.value = value;
	}


	/** Evaluation simply returns the contained value.
	 *
	 * @param unit Not used, no lookups or failures can happend here.
	 * @return The value.
	 * */
	public Type evaluate_from(ParseUnit unit)
	{
		return this.value;
	}
}

