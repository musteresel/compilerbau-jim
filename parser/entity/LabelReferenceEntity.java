package parser.entity;


public class LabelReferenceEntity implements Entity
{
	protected Token token;
	public LabelReferenceEntity(Token token)
	{
		this.token = token;
	}
}

