package parser;


import java.io.Reader;
import java.io.LineNumberReader;
import java.io.IOException;


/** Reader class which ignores comments for its source.
 *
 * A comment is started by a " ;" and ends at the end of a line.
 * */
public class NoCommentReader extends LineNumberReader
{
	/** Protected member variable to track if the previous read character
	 * was a white space.
	 * */
	protected boolean wasWhite;


	/** Constructor forwarding to super class.
	 * */
	public NoCommentReader(Reader r)
	{
		super(r);
		this.wasWhite = false;
	}


	/** Overloaded read method ignoring comments.
	 *
	 * @return The next character of the input that does not belong to a comment.
	 * */
	public int read() throws IOException
	{
		int c = super.read();
		if (Character.isWhitespace(c))
		{
			this.wasWhite = true;
		}
		else if (this.wasWhite && c == ';')
		{
			while (super.read() != '\n');
			c = '\n';
		}
		else
		{
			this.wasWhite = false;
		}
		return c;
	}
}

