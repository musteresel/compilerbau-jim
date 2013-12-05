package jim.type;


import virtualmachine.NumericType;
import java.nio.ByteBuffer;


public class IntegerType implements NumericType
{
	protected int value;


	public IntegerType()
	{
		this.value = 0;
	}


	public IntegerType(int v)
	{
		this.value = v;
	}


	public int getInt()
	{
		return this.value;
	}


	public int size()
	{
		return 4;
	}


	public void from(byte[] data)
	{
		this.value = ByteBuffer.wrap(data).getInt();
	}


	public boolean from(String text)
	{
		try
		{
			this.value = Integer.decode(text);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}


	public byte[] to()
	{
		return ByteBuffer.allocate(this.size()).putInt(this.value).array();
	}


	protected static IntegerType convert(NumericType o)
	{
		if (!(o instanceof IntegerType))
		{
			throw new UnsupportedOperationException("No type conversion");
		}
		return (IntegerType) o;
	}


	public NumericType add(NumericType o)
	{
		IntegerType i = convert(o);
		return new IntegerType(this.value + i.value);
	}


	public NumericType sub(NumericType o)
	{
		IntegerType i = convert(o);
		return new IntegerType(this.value - i.value);
	}


	public NumericType mul(NumericType o)
	{
		IntegerType i = convert(o);
		return new IntegerType(this.value * i.value);
	}


	public NumericType div(NumericType o)
	{
		IntegerType i = convert(o);
		return new IntegerType(this.value / i.value);
	}
}

