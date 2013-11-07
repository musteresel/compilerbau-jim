
public interface StackAccess
{
	public abstract byte[] pop(int num);
	public abstract void push(byte[] data);
}
public interface FrameAccess
{
	public abstract byte[] fread(int offset, int num);
	public abstract void fwrite(int offset, byte[] data);
	public abstract void mark(int offset);
	public abstract int mark(void);
}
public interface RandomAccess
{
	public abstract byte[] read(int pos, int num);
	public abstract void write(int pos, byte[] data);
}


public class Machine implements StackAccess, FrameAccess, RandomAccess
{
	protected byte[] memory;
	protected int mp;
	protected int sp;

	public byte[] pop(int num)
	{
		int temp = sp;
		sp = sp - num;
		return read(sp, temp);
	}
	public void push(byte[] data)
	{
		write(sp, data);
		sp += data.length;
	}

	public byte[] fread(int offset, int num)
	{
		return read(mp + offset, num);
	}
	public void fwrite(int offset, byte[] data)
	{
		write(mp + offset, data);
	}
	public int mark(void)
	{
		return mp;
	}
	public void mark(int m)
	{
		mp = m;
	}

	public byte[] read(int pos, int num)
	{
		return Arrays.copyOfRange(memory, pos, pos + num);
	}
	public void write(int pos, byte[] data)
	{
		for (int i = 0; i < data.length; i++)
		{
			memory[pos] = data[i];
			pos++;
		}
	}


}	

// ----------XXXX
//               ^ pos
//               ^ limit
//           ^ pos
//           ^ limit

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
