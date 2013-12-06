package parser;


/** Simple class representing a failure during parsing.
 *
 * TODO: This could be further expanded.
 * */
public class ParseFailure
{
	/** Message of the failure.
	 * */
	protected String message;


	/** Constructor.
	 *
	 * @param message The message.
	 * */
	public ParseFailure(String message)
	{
		this.message = message;
	}
}

