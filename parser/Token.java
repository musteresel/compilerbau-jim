package parser;


/** A single token.
 *
 * A token is a string together with a linenumber. Tokens are usually
 * separated by whitespace, so don't expect a token to contain whitespace.
 *
 * @see parser.Tokenizer
 * */
public class Token
{
	/** String representation of the token.
	 * */
	protected final String text;


	/** Protected member variable to store the line number.
	 *
	 * The number of the line where the token starts (and usually ends).
	 * */
	protected int lineNumber;


	/** Construct a new token from a Stringbuffer and a line number.
	 *
	 * @param lineNumber Linenumber of token's start.
	 * @param buffer The buffer with the token contents.
	 * */
	public Token(int lineNumber, StringBuffer buffer)
	{
		this.text = new String(buffer);
		this.lineNumber = lineNumber;
	}


	/** Returns the line number.
	 *
	 * @return Line number on which this token was found.
	 * */
	public int getLineNumber()
	{
		return this.lineNumber;
	}


	/** Return string representation.
	 *
	 * @return Token string.
	 * */
	public String toString()
	{
		return this.text;
	}
}

