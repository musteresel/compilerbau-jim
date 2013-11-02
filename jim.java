
public interface VirtualMachine
{
	public abstract void step(void);
	public abstract boolean good(void);

	// access:
	// - CONST mem
	// - STACK mem
}

public class SimpleVM implements VirtualMachine
{
	protected int pc;
	protected int sp;
	protected int mp;

	protected Code[] code;
	protected byte[] data;


	public void step(void)
	{
		code[pc].executeOn(this);
	}
	public boolean good(void)
	{
		return true;
	}
}



public interface Code
{
	public abstract void executeOn(VirtualMachine m);
}
public interface Type<E>
{
	public abstract E fromStack(VirtualMachine m, int index);
	public abstract void toStack(VirtualMachine m, E value);
}
public class IntegerType implements Type<Integer>
{
	// TODO
}


public class IAdd implements Code
{
	public void executeOn(VirtualMachine m)
	{
		int a = IntegerType.fromStack(m, 0);
		int b = IntegerType.fromStack(m, 1);
		IntegerType.toStack(m,a+b);
	}
}


public class Test
{
	static void main(String[] args)
	{
		VirtualMachine machine = new MyVirtualMachine();

		while (machine.good())
		{
			machine.step();
		}
	}
}
