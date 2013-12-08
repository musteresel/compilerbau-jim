package jim.type;


import virtualmachine.Type;
import java.nio.ByteBuffer;


public class ArrayReference implements Type
{
	protected int address;

	public ArrayReference()
	{
		// Note: This way, we will fail pretty fast if used uninitialised.
		this.address = -1;
	}

	public ArrayReference(int a)
	{
		this.address = a;
	}

	public int get_address()
	{
		return this.address;
	}


	public int size()
	{
		return 4;
	}

	public void from(byte[] data)
	{
		this.address = ByteBuffer.wrap(data).getInt();
	}

	public boolean from(String text)
	{
		if (text.startsWith("@"))
		{
			try
			{
				this.address = Integer.decode(text.substring(1));
				return true;
			}
			catch (IndexOutOfBoundsException | NumberFormatException e)
			{
				// Note: This is ok, will fall through to return false statement,
				// which is the intended behaviour.
			}
		}
		return false;
	}

	public String toString()
	{
		return "ArrayReference[" + Integer.toString(this.address) + "]";
	}

	public byte[] to()
	{
		return ByteBuffer.allocate(this.size()).putInt(this.address).array();
	}
}

