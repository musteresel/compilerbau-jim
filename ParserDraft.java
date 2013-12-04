
import java.nio.charset.StandardCharsets;

public class ParserDraft
{
	public static class Token
	{
		protected int lineNumber;
		protected int start;
		protected int end;
		// protected CharSequence underlyingData;
	}

	public static interface TokenizerI
	{
		public abstract boolean hasToken();
		public abstract Token nextToken();
	}


	public static class Tokenizer
	{
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
		}
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
		public Token nextToken()
		{
			return this.token;
		}
	}




	public void parse(CharSequence input)
	{
		// TODO
		Token token = null;
		int linenumber = 1;
		for (int position = 0; position < input.length(); position++)
		{
			char currentChar = input.charAt(position);

			if (token)
			{
				if (!token.uses(currentChar))
				{
					// TODO save token
					token = null;
				}
				else
				{
					continue;
				}
			}
			if (this.factories.partOfAnyToken(currentChar))
			{
				token = this.factories.matchingToken();
			}
		}
	}

	public static void fromFile(String filename)
	{
		FileInputStream fis = new FileInputStream(filename);
		FileChannel fc = fis.getChannel();
		ByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
	}
}


