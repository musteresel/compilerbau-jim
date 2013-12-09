package jim.type;


import virtualmachine.NumericType;
import java.nio.ByteBuffer;


public class DoubleType implements NumericType
{
	protected double value;


	public DoubleType()
	{
		this.value = 0;
	}


	public DoubleType(double v)
	{
		this.value = v;
	}


	public double get_double()
	{
		return this.value;
	}


	public int size()
	{
		return 8;
	}


	public void from(byte[] data)
	{
		this.value = ByteBuffer.wrap(data).getDouble();
	}


	public boolean from(String text)
	{
		boolean success = false;
		try
		{
			Double d = Double.valueOf(text);
			this.value = d;
			success = true;
		}
		catch (NumberFormatException e)
		{
			success = false;
		}
		return success;
	}


	public String toString()
	{
		return "DoubleType[" + Double.toString(this.value) + "]";
	}


	public byte[] to()
	{
		return ByteBuffer.allocate(this.size()).putDouble(this.value).array();
	}


	protected static DoubleType convert(NumericType o)
	{
		if (!(o instanceof DoubleType))
		{
			throw new UnsupportedOperationException("No type conversion");
		}
		return (DoubleType) o;
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

