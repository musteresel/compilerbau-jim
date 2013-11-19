package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.FlowControl;


/** Simple Goto instruction.
 *
 * This instruction is used in loops and conditional evaluations.
 * Please note that this is a lowlevel goto, also called jump instruction.
 * No higher level stack unwinding is done.
 * */
public class Goto implements Instruction
{
	/** The destination of the GOTO instruction.
	 *
	 * The destination is encoded as code address, thus the parsers has to
	 * transform expressions such as "goto label" into a call to
	 * Goto(addressOf("label")).
	 * */
	protected int destination;


	/** Constructor setting destination.
	 *
	 * @param destination The destination of the goto.
	 * */
	public Goto(int destination)
	{
		this.destination = destination;
	}


	/** Executing the goto results in a flow control jump.
	 * */
	public void executeWith(MachineState state)
	{
		FlowControl.jump(state, destination);
	}
}

