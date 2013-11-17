
/*
 Cycle:
 MachineState -> Code -> MachineState
 */
public interface Register
{
	public abstract int read_register(int id);
	public abstract void write_register(int id, int data);
}
public interface Memory
{
	public abstract byte[] read_memory(int position, int num);
	public abstract void write_memory(int position, byte[] data);
}

public interface MachineState
{
	public abstract Register get_register(void);
	public abstract Memory get_memory(void);
}

public interface VirtualMachine
{
	public abstract void step(void);
	public abstract boolean good(void);
	public abstract MachineState get_state(void);
}

public interface Instruction
{
	public abstract void executeWith(MachineState state);
}


public interface Type
{
	public abstract int size(void);
	public abstract void from(byte[] data);
	public abstract byte[] to();
}
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

// Type ---> IntegerType
// StackAccess.pop(state, type)
// {
// regs = state.get_register();
// int tmp = regs.read_register(StackAccess.SP);
// regs.write_register(StackAccess.SP, tmp - type.size());
// RandomAccess.read(state, type, tmp - type.size());
// }
//
// RandomAccess.read(state, type, pos)
// {
// byte[] data = state.get_memory().read_memory(pos, type.size());
// type.from(data);
// }
//
// 

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


public class Machine implements StackAccess, FrameAccess, Memory
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
