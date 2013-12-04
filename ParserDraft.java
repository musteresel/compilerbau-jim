
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

	public static void fromFile(String filename)
	{
		FileInputStream fis = new FileInputStream(filename);
		FileChannel fc = fis.getChannel();
		ByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		CharBuffer charBuffer = StandardCharsets.UTF_8.decode(byteBuffer);
	}
}


