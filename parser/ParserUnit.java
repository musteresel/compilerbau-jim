package parser;


public class ParserUnit
{
	protected List<InstructionStub> instructions;
	protected Map<Token, Type> referenceMap;
	protected List<ParseFailure> failures;


	public ParseUnit(Map<String, Class> instructionMap, Tokenizer tokenizer)
	{
		this.instructions = new LinkedList<InstructionStub>();
		this.referenceMap = new HashMap<Token, Type>();
		int instructionCounter = 0;
		Token nextMapping = null;
		InstructionStub lastInstructionStub = null;
		while (tokenizer.hasToken())
		{
			Token token = tokenizer.nextToken();
			if (instructionMap.containsKey(token))
			{
				lastInstructionStub =
					new InstructionStub(instructionMap.get(token));
				if (nextMapping != null)
				{
					this,referenceMap.put(nextMapping,
							new IntegerType(instructionCounter));
					nextMapping = null;
				}
				instructionCounter++;
				this.instructions.addLast(lastInstructionStub);
			}
			else if (token.endsWith(":"))
			{
				if (nextMapping != null)
				{
					this.log_failure(new ParseFailure("Label declaration of " +
								token + " (Line " + Integer.toString(token.getLineNumber()) +
								") after declaration of " + nextMapping + " (Line " +
								Integer.toString(nextMapping.getLineNumber()) + ")."));
				}
				nextMapping = token;
				lastInstructionStub = null;
			}
			else
			{
				ValueStub valueStub = null;
				Type type = null;
				IntegerType iType = new IntegerType();
				DoubleType dType = new DoubleType();
				if (iType.from(token))
				{
					type = iType;
				}
				else if (dType.from(token))
				{
					type = dType;
				}
				if (type != null)
				{
					if (nextMapping != null)
					{
						this.referenceMap.put(nextMapping, type);
						nextMapping = null;
					}
					else
					{
						valueStub = new EvaluatedValueStub(type);
					}

				}
				else
				{
					if (nextMapping != null)
					{
						this.log_failure(new ParseFailure("Label declaration of " +
									nextMapping + " (Line " +
									Integer.toString(nextMapping.getLineNumber()) +
									") tries to use reference " + token "."));
						nextMapping = null;
					}
					else
					{
						valueStub = new ReferencedValueStub(token);
					}
				}
				if (valueStub != null)
				{
					if (lastInstructionStub != null)
					{
						lastInstructionStub.push_parameter(valueStub);
					}
					else
					{
						this.log_failure(new ParseFailure("Orphan token at line " +
									Integer.toString(token.getLineNumber()) + ": " +
									token + "."));
					}
				}
			}
		}
		this.failures = new LinkedList<ParseFailure>();
	}


	public void log_failure(ParseFailure failure)
	{
		this.failures.addLast(failure);
	}



	public Type evaluate_reference(Token reference)
	{
		return this.referenceMap.get(reference);
	}
}

