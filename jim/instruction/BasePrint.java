package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;


public abstract class BasePrint implements Instruction
{
	protected Type type;

	public BasePrint(Type type)
	{
		this.type = type;
	}


	public void executeWith(MachineState state)
	{
		StackAccess.pop(state, this.type);
		System.out.println(this.type);
	}
}

