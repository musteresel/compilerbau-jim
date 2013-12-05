package parser.entity;


import virtualmachine.Type;


public class TypeEntity implements Entity
{
	protected Type typeInstance;


	public TypeEntity(Type type)
	{
		this.type = type;
	}


	public int getValue()
	{
		throw new ParseException();
	}
}

