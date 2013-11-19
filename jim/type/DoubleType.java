package jim.type;


import virtualmachine.NumericType;
import java.nio.ByteBuffer;


public class DoubleType implements NumericType
{
	protected double value;


	public DoubleType(double v)
	{
		this.value = v;
	}


	public int size()
	{
		return 8;
	}


	public void from(byte[] data)
	{
		this.value = ByteBuffer.wrap(data).getDouble();
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


	public NumericType add(NumericType o)
	{
		DoubleType i = convert(o);
		return new DoubleType(this.value + i.value);
	}


	public NumericType sub(NumericType o)
	{
		DoubleType i = convert(o);
		return new DoubleType(this.value - i.value);
	}


	public NumericType mul(NumericType o)
	{
		DoubleType i = convert(o);
		return new DoubleType(this.value * i.value);
	}


	public NumericType div(NumericType o)
	{
		DoubleType i = convert(o);
		return new DoubleType(this.value / i.value);
	}
}
