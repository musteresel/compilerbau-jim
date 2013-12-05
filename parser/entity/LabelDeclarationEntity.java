package parser.entity;


import parser.Token;
import parser.ParseException;
import parser.entity.Entity;


/** Entity representing a declaration of a new label.
 *
 * This entity represents the declaration of a new label in the source
 * code. Evaluating it to a value will fail.
 * */
public class LabelDeclarationEntity implements Entity
{
	/** The label to be declarated.
	 * */
	protected String label;


	/** Line number where the declaration is found.
	 * */
	protected int lineNumber;


	/** Constructor from Token.
	 *
	 * This constructor takes a token and constructs a new label declaration
	 * from the tokens value (without the trailing ":").
	 *
	 * @param token The token of the declaration.
	 * */
	public LabelDeclarationEntity(Token token)
	{
		this.label = token.substring(0,token.length()-1);
		this.lineNumber = token.getLineNumber();
	}


	/** This will throw.
	 * */
	public int getValue()
	{
		throw new ParseException("Label Declaration \"" +
				this.label + "\" on line " + Integer.toString(this.lineNumber) +
				" cannot be evaluated!");
	}
}

