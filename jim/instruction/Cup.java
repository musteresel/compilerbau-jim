package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.FrameAccess;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Do function call instruction.
 *
 * This instruction saves the return address, sets the new markpointer
 * and jumps to the function.
 * */
public class Cup implements Instruction
{
	/** Size of function parameters in bytes.
	 * */
	protected int parameterSize;


	/** Function destination.
	 * */
	protected int destination;


	/** Constructor.
	 *
	 * @param parameterSize Size of parameters.
	 * @param destination The destination of the function call.
	 * */
	public Cup(IntegerType parameterSize, IntegerType destination)
	{
		this.parameterSize = parameterSize.getInt();
		this.destination = destination.getInt();
	}


	/** Execute function call.
	 *
	 * First, calculate new markpointer. With new markpointer set, store
	 * return address at offset 4 (offset 0 is the old markpointer).
	 * */
	public void executeWith(MachineState state)
	{
		int newMp = state.get_registerfile().read_register(StackAccess.get_SP_id());
		newMp -= (this.parameterSize + 4 + 4);
		state.get_registerfile().write_register(FrameAccess.get_MP_id(), newMp);
		int pc = state.get_registerfile().read_register(FlowControl.get_PC_id());
		FrameAccess.write(state,new IntegerType(pc), 4);
		FlowControl.jump(state, this.destination);
	}
}

