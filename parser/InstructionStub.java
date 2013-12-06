package parser;


public class InstructionStub
{
	protected List<ValueStub> parameters;
	protected Class<Instruction> instruction;

	public InstructionStub(Class<Instruction> instruction)
	{
		this.instruction = instruction;
		this.parameters = new LinkedList<ValueStub>();
	}

	public void push_parameter(ValueStub parameter)
	{
		this.parameters.addLast(parameter);
	}

	public Instruction evaluate_from(ParseUnit unit)
	{
		Type[] evaluatedParameters = new Type[this.parameters.length()];
		ListIterator<ValueStub> iterator = this.parameters.listIterator();
		boolean evaluationFailed = false;
		while (iterator.hasNext())
		{
			int index = iterator.nextIndex();
			ValueStub parameter = iterator.next();
			Type evaluatedParameter = parameter.evaluate_from(unit);
			if (evaluatedParameter == null)
			{
				evaluationFailed = true;
			}
			evaluatedParameters[index] = evaluatedParameter;
		}
		if (evaluationFailed)
		{
			unit.log_failure(new ParseFailure("Could not evaluate " +
						this.instruction.getName() +
						" due a parameter evaluation failure."));
			return null;
		}
		else
		{
		  Constructor<Instruction>[] constructors =
				this.instruction.getConstructors();
			for (Constructor<Instruction> constructor : constructors)
			{
				try
				{
					return constructor.newInstance(evaluatedParameters);
				}
				catch (Exception e)
				{
					// NOTE: This is ok here, we're handling this.
					// TODO: Forward this/these exception(s) to log_failure.
				}
			}
			unit.log_failure(new ParseFailure("Could not evaluate " +
						this.instruction.getName() +
						" due to parameter mismatch."));
			return null;
		}
	}
}

