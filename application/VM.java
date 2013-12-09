package application;


import java.util.Arrays;
import virtualmachine.*;



public class VM implements VirtualMachine, MachineState, RegisterFile, Memory
{
	protected int mp;
	protected int sp;
	protected int pc;

	protected byte[] memory;
	protected Instruction[] code;

	protected boolean running;

	public VM(Instruction[] code, int entry)
	{
		this.code = code;
		this.pc = entry;
		this.sp = 0;
		this.mp = 0;
		FlowControl.set_PC_id(1);
		StackAccess.set_SP_id(2);
		FrameAccess.set_MP_id(3);
		this.memory = new byte[2048];
	}

	public void step()
	{
		System.out.format(
				" vm@ %5d [%05d>%5d]  %s%n",
				this.pc, this.mp, this.sp,
				this.code[this.pc].getClass().getName()
				);
		/*System.out.println(
				"  [VM] @ " + Integer.toString(this.pc) +
				" [" + Integer.toString(this.mp) +
				"  " + Integer.toString(this.sp) + ">  " +
				this.code[this.pc].getClass().getName());*/
		this.code[this.pc].execute_with(this);
	}

	public boolean good()
	{
		return (this.pc != -42);
	}

	public MachineState get_state()
	{
		return this;
	}

	public RegisterFile get_registerfile()
	{
		return this;
	}

	public Memory get_memory()
	{
		return this;
	}

	public byte[] read_memory(int position, int num)
	{
		return Arrays.copyOfRange(this.memory, position, position + num);
	}

	public void write_memory(int position, byte[] data)
	{
		for (byte b : data)
		{
			this.memory[position] = b;
			position++;
		}
	}

	public int read_register(int id)
	{
		if (id == FlowControl.get_PC_id())
		{
			return this.pc;
		}
		if (id == StackAccess.get_SP_id())
		{
			return this.sp;
		}
		if (id == FrameAccess.get_MP_id())
		{
			return this.mp;
		}
		throw new IllegalArgumentException();
	}
	public void write_register(int id, int data)
	{
		if (id == FlowControl.get_PC_id())
		{
			this.pc = data;
		}
		else if (id == StackAccess.get_SP_id())
		{
			this.sp = data;
		}
		else if (id == FrameAccess.get_MP_id())
		{
			this.mp = data;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
}

