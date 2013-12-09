package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.FlowControl;

public class Halt implements Instruction
{
	public void execute_with(MachineState state)
	{
		// Do nothing, especially do not change programm counter.
	}
}

