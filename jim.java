
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
	public abstract E pop(VirtualMachine m);
	public abstract void push(VirtualMachine m, E value);
	public abstract E read(VirtualMachine m, int index);
	public abstract void write(VirtualMachine m, int index, E value);
}
public class IntegerType implements Type<Integer>
{
	// TODO
}


public class IAdd implements Code
{
	public void executeOn(VirtualMachine m)
	{
		int a = IntegerType.pop(m);
		int b = IntegerType.pop(m);
		IntegerType.push(m,a+b);
		m.instructionComplete();
	}
}

public class IStore implements Code
{
	protected int index;

	public void executeOn(VirtualMachine m)
	{
		int a = IntegerType.pop(m);
		IntegerType.write(m, index, a);
		m.instructionComplete();
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
