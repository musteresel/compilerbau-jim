package parser;


import virtualmachine.Type;
import parser.Token;
import parser.ValueStub;
import parser.ParseUnit;
import parser.ParseFailure;


/** Stub of a referenced value.
 *
 * This class represents a not yet evaluated value.
 * To evaluate, the reference must be looked up in the corresponding units
 * mapping.
 * */
public class ReferencedValueStub implements ValueStub
{
	/** The token containing the label.
	 * */
	protected Token reference;


	/** Constructor.
	 *
	 * @param reference The referenced label.
	 * */
	public ReferencedValueStub(Token reference)
	{
		this.reference = reference;
	}


	/** Evalute the stub by looking up the reference.
	 *
	 * The given unit is used to look up the stubs reference. If there is
	 * no mapping, a failure is logged to the unit.
	 *
	 * @param unit The unit with the mapping. It also receives any failure.
	 * @return The mapped type, or null, if no mapping exists.
	 * */
	public Type evaluate_from(ParseUnit unit)
	{
		Type value = unit.evaluate_reference(this.reference.toString());
		if (value == null)
		{
			unit.log_failure(new ParseFailure("Could not evaluate " +
						this.reference + " on line " +
						Integer.toString(this.reference.getLineNumber()) + "."));
		}
		return value;
	}
}

