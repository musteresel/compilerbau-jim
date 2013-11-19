
public class SimpleVM implements VirtualMachine
{
	protected int pc;
	protected int sp;
	protected int mp;

	protected Code[] code;
	protected byte[] data;


	public void step()
	{
		code[pc].executeOn(this);
	}
	public boolean good()
	{
		return true;
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
