package parser;


public class ReferencedValueStub implements ValueStub
{
	protected Token reference;


	public ReferencedValueStub(Token reference)
	{
		this.reference = reference;
	}


	public Type evaluate_from(ParseUnit unit)
	{
		Type value = unit.evaluate_reference(this.reference);
		if (value == null)
		{
			unit.log_failure(new ParseFailure("Could not evaluate " +
						this.reference + " on line " +
						Integer.toString(this.reference.getLineNumber()) + "."));
		}
		return value;
	}
}

