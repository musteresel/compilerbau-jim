package parser;


import java.io.LineNumberReader;
import java.io.Reader;
import parser.Token;


/** Tokenizer providing a stream based access to whitespace separated tokens.
 *
 * This class reads from a provided Reader and splits the stream of characters
 * into tokens. They are separated by whitespace. The token will also take
 * a line number.
 * */
public class Tokenizer
{
	/** The source of the characters / tokens.
	 * */
	protected LineNumberReader input;


	/** The current token.
	 * */
	protected Token token;


	/** Construct a tokenizer, reading from the provided Reader.
	 *
	 * If the reader is not an instance of LineNumberReader, it will be wrapped
	 * into one.
	 *
	 * @param reader The reader to read from.
	 * */
	public Tokenizer(Reader reader)
	{
		if (reader instanceof LineNumberReader)
		{
			this.input = reader;
		}
		else
		{
			this.input = new LineNumberReader(reader);
		}
		this.token = null;
	}


	/** Return the last read token.
	 *
	 * This method is just returning the last read token, it does not
	 * perform any reading.
	 *
	 * @return Last read token or null, if there are no more tokens available.
	 * */
	public Token nextToken()
	{
		return this.token;
	}


	/** Look for the next token.
	 *
	 * This method scans for the next token, reporting whether it found
	 * a next token.
	 *
	 * @return True if a next token has been found.
	 * */
	public boolean hasToken()
	{
		Token currentToken = null;
		while (this.token == null)
		{
			int nextFromReader = this.input.read();
			if (nextFromReader == -1)
			{
				this.token = currentToken;
				break;
			}
			char nextChar = (char) nextFromReader;
			if (Character.isWhitespace(nextChar))
			{
				this.token = currenToken;
				continue;
			}
			if (currentToken == null)
			{
				currenToken = new Token(this.input.getLineNumber());
			}
			currentToken.consume(nextChar);
		}
		return (this.token != null);
	}
}

