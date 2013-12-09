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


	public int get_int()
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


	public String toString()
	{
		return "IntegerType[" + Integer.toString(this.value) + "]";
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


	public void add(NumericType lhs, NumericType rhs)
	{
		this.value = convert(lhs).value + convert(rhs).value;
	}


	public void sub(NumericType lhs, NumericType rhs)
	{
		this.value = convert(lhs).value - convert(rhs).value;
	}


	public void mul(NumericType lhs, NumericType rhs)
	{
		this.value = convert(lhs).value * convert(rhs).value;
	}


	public void div(NumericType lhs, NumericType rhs)
	{
		this.value = convert(lhs).value / convert(rhs).value;
	}
}

