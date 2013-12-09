package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;


public abstract class BasePrint implements Instruction
{
	protected Type type;

	public BasePrint(Type type)
	{
		this.type = type;
	}


	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.type);
		System.out.println(this.type);
		FlowControl.step(state);
	}
}
