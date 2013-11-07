
public interface StackAccess
{
	public abstract ByteBuffer pop(int num);
	public abstract ByteBuffer push(int num);
}
public interface FrameAccess
{
	public abstract ByteBuffer fread(int offset, int num);
	public abstract ByteBuffer fwrite(int offset, int num);
	public abstract void mark(int offset);
	public abstract int mark(void);
}
public interface RandomAccess
{
	public abstract ByteBuffer read(int pos, int num);
	public abstract ByteBuffer write(int pos, int num);
}


public class Machine implements StackAccess, FrameAccess, RandomAccess
{
	protected byte[] data;
	protected ByteBuffer stack;
	protected ByteBuffer frame;
	protected int mp;
	protected int sp;

	public ByteBuffer pop(int num)
	{
		stack.position(stack.position - num);
		ByteBuffer slice = stack.slice();
		slice.limit(num);
		stack.limit(stack.position);
		return slice;

		List<int> l = data.asList();
		List<int> s = l.subList(sp - num, sp);
	}
	public ByteBuffer push(int num)
	{
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
