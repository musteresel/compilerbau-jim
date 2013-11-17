
/*
 Cycle:
 MachineState -> Code -> MachineState
 */
public class IntegerType implements Type
{
	protected int value;
	public int size()
	{
		return 4;
	}
	public void from (byte[] data)
	{
		value = ByteBuffer.wrap(data).getInt();
	}
	public byte[] to()
	{
		return ByteBuffer.allocate(this.size()).putInt(value).array();
	}
}

public class IAdd implements Instruction
{
	public void executeWith(MachineState state)
	{
		IntegerType a, b
		StackAccess.pop(state, a);
		StackAccess.pop(state, b);
		StackAccess.push(state, a.add(b));
	}
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
