package parser;


public class EvaluatedValueStub implements ValueStub
{
	protected Type value;


	public EvaluatedValueStub(Type value)
	{
		this.value = value;
	}


	public Type evaluate_from(ParseUnit unit)
	{
		return this.value;
	}
}

