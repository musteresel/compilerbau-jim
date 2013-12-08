package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;


public class Ldc implements Instruction
{
	protected Type type;

	public Ldc(Type type)
	{
		this.type = type;
	}

	public void execute_with(MachineState state)
	{
		StackAccess.push(state, this.type);
		FlowControl.step(state);
	}
}

