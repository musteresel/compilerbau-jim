
import java.nio.charset.StandardCharsets;

public class ParserDraft
{
	public static void t()
	{
		while (tokenizer.hasToken())
		{
			Token token = tokenizer.nextToken();
			if (isLabel(token))
			{
				// register label with value
				// if next token is instruction token, register number
				// else register token itself
			}
			else if (isInstruction(token))
			{
				// Must be an instruction token
				// parse instruction tokens parameters
			}
			else
			{
				// Error
			}
		}
	}

	public static void p()
	{
		int instructionCounter = 0;
		int instructionParameters = 0;
		Token labelToRegister = null;
		while (tokenizer.hasToken())
		{
			Token token = tokenzier.nextToken();
			if (isLabel(token))
			{
				if (labelToRegister == null)
				{
					labelToRegister = token;
					continue;
				}
				else
				{
					throw new ParseException();
				}
			}
			if (isInstruction(token))
			{
				// get instruction
				// read instruction Parameter count
			}
		}
	}

	public static class InstructionEntity implements ParseEntity
	{
		protected Class instructionClass;
		protected int instructionAddress;
		protected int lineNumber;
		public InstructionEntity(int address, Class instructionClass, int line)
		{
			this.instructionAddress = address;
			this.instructionClass = instructionClass;
			this.lineNumber = line;
		}
		public int getValue()
		{
			return this.instructionAddress;
		}
	}


	public static void v()
	{
		int instructionCounter = 0;
		List<ParseEntity> parseEntities = new LinkedList<ParseEntity>();
		while (tokenizer.hasToken())
		{
			Token token = tokenizer.nextToken();
			if (instructionMap.containsKey(token))
			{
				InstructionEntity instructionEntity =
					new InstructionEntity(
							instructionCounter,
							instructionMap.get(token),
							token.getLineNumber());
				parseEntities.addLast(instructionEntity);
			}
			else if (token.endsWith(":"))
			{
				LabelDeclarationEntity lde =
					new LabelDeclarationEntity(token);
				parseEntities.addLast(lde);
			}
			else
			{
				DoubleType d = new DoubleType();
				IntegerType i = new IntegerType();
				TypeEntity typeEntity = null;
				if (d.from(token))
				{
					typeEntity = new TypeEntity(d);
				}
				else if (i.from(token))
				{
					typeEntity = new TypeEntity(i);
				}
				if (typeEntity != null)
				{
					parseEntities.addLast(typeEntity);
				}
				else
				{
					// Not a number, must be a label
					LabelReferenceEntity lre =
						new LabelReferenceEntity(token);
					parseEntities.addLast(lre);
				}
			}
		}
	}


	public static interface ParseEntity
	{
		public abstract int getValue();
	}
}


