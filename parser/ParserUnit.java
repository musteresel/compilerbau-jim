package parser;


public class ParserUnit
{
	protected Entity[] entities;
	public ParserUnit(Map<String, Class> instructionMap, Tokenizer tokenizer)
	{
		List<Entity> parseEntities = new LinkedList<Entity>();
		int instructionCounter = 0;
		while (tokenizer.hasToken())
		{
			Token token = tokenizer.nextToken();
			Entity nextEntity = null;
			if (instructionMap.containsKey(token))
			{
				nextEntity = new InstructionEntity(
						instructionCounter,
						instructionMap.get(token),
						token.getLineNumber());
				instructionCounter++;
			}
			else if (token.endsWith(":"))
			{
				nextEntity = new LabelDeclarationEntity(token);
			}
			else
			{
				DoubleType dType = new DoubleType();
				IntegerType iType = new IntegerType();
				Type type = null;
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
					nextEntity = new TypeEntity(type);
				}
				else
				{
					// Not a number, must be label reference
					nextEntity = new LabelReferenceEntity(token);
				}
			}
			parseEntities.addLast(nextEntity);
		}
		/*
		ListIterator<Entity> iterator = parseEntities.listIterator();
		while (iterator.hasNext())
		{
			Entity entity = iterator.next();
			if (entity instanceof LabelDeclarationEntity)
			{
				// TODO
				iterator.remove();
			}
		}
		*/
		this.entities = parseEntities.toArray();
	}
}

