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
		// Token to be used in the next mapping.
		Token nextMapping = null;
		// Set to the last added instruction, or null, if last processed token
		// was not an instruction or part thereof.
		InstructionStub lastInstructionStub = null;
		while (tokenizer.hasToken())
		{
			Token token = tokenizer.nextToken();
			// Check whether the token is an instruction.
			if (instructionMap.containsKey(token))
			{
				lastInstructionStub =
					new InstructionStub(instructionMap.get(token));
				// If the previous token was a label declaration, map this
				// instructions address to the label.
				if (nextMapping != null)
				{
					this,referenceMap.put(nextMapping,
							new IntegerType(instructionCounter));
					nextMapping = null;
				}
				instructionCounter++;
				this.instructions.addLast(lastInstructionStub);
			}
			// Check whether token is a label declaration.
			else if (token.endsWith(":"))
			{
				// In case the previous token was a label declaration, there
				// is a parse failure.
				if (nextMapping != null)
				{
					this.log_failure(new ParseFailure("Label declaration of " +
								token + " (Line " + Integer.toString(token.getLineNumber()) +
								") after declaration of " + nextMapping + " (Line " +
								Integer.toString(nextMapping.getLineNumber()) + ")."));
				}
				// Try to proceed in order to evaluate as much as possible, and thus
				// providing detailed error reporting.
				nextMapping = token;
				// A label declaration "ends" the parameter list of a previous
				// instruction.
				lastInstructionStub = null;
			}
			// Neither instruction nor declaration, must be value or reference.
			else
			{
				ValueStub valueStub = null;
				Type type = null;
				// Check if token can be converted to any known value type.
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
				// If token could be converted, use the converted type instance.
				if (type != null)
				{
					// If the previous token was a label declaration, map this
					// type to the label.
					if (nextMapping != null)
					{
						this.referenceMap.put(nextMapping, type);
						nextMapping = null;
					}
					// The type is probably used as a parameter to an instruction.
					else
					{
						valueStub = new EvaluatedValueStub(type);
					}

				}
				// In case the token could not be converted, it must be a reference
				// to a somewhere defined label.
				else
				{
					// If the previous token was a label declaration, fail parsing
					// because we do not support recursive label references.
					if (nextMapping != null)
					{
						this.log_failure(new ParseFailure("Label declaration of " +
									nextMapping + " (Line " +
									Integer.toString(nextMapping.getLineNumber()) +
									") tries to use reference " + token "."));
						nextMapping = null;
					}
					// The reference is probably a parameter to an instruction.
					else
					{
						valueStub = new ReferencedValueStub(token);
					}
				}
				// The token is not used up to this point.
				if (valueStub != null)
				{
					// Try to use it as parameter to the last read instruction.
					if (lastInstructionStub != null)
					{
						lastInstructionStub.push_parameter(valueStub);
					}
					// Token is not used anywhere, this is a failure.
					else
					{
						this.log_failure(new ParseFailure("Orphan token at line " +
									Integer.toString(token.getLineNumber()) + ": " +
									token + "."));
					}
				}
			}
			// Continue with next token.
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

