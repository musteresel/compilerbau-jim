package parser;


import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringWriter;
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
			this.input = (LineNumberReader) reader;
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
	public Token next_token()
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
	public boolean has_token()
	{
		int nextFromReader;
		int startLineNumber;
		do
		{
			try
			{
				nextFromReader = this.input.read();
			}
			catch (IOException e)
			{
				nextFromReader = -1;
			}
			if (nextFromReader == -1)
			{
				return false;
			}
		} while (Character.isWhitespace(nextFromReader));
		// nextFromReader contains a non whitespace character
		StringWriter writer = new StringWriter();
		startLineNumber = this.input.getLineNumber();
		do
		{
			writer.write(nextFromReader);
			try
			{
				nextFromReader = this.input.read();
			}
			catch (IOException e)
			{
				nextFromReader = -1;
			}
		} while (nextFromReader != -1 && !Character.isWhitespace(nextFromReader));
		// TODO Does every call to read return -1 after the first -1 result?
		this.token = new Token(startLineNumber, writer.getBuffer());
		return true;
	}
}

