package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.FrameAccess;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;
import jim.type.ArrayReference;


/** Creates an new array reference to a local location.
 *
 * This instruction is used to create references to local variables or stored
 * array contents.
 * */
public class ARefLocal implements Instruction
{
	/** Offset from the local stack frame.
	 *
	 * This offset marks the begin of the new array, relative to the local stack
	 * frame (the MP register value).
	 * */
	protected int localOffset;


	/** Constructor setting offset.
	 *
	 * @param localOffset The offset of the contents relative to the stack frame.
	 * */
	public ARefLocal(IntegerType localOffset)
	{
		this.localOffset = localOffset.get_int();
	}


	/** Create a new reference to content relative to the current stack frame.
	 *
	 * Pushes an array reference to the stack whose address is the current
	 * mark pointer value plus the specified offset.
	 * */
	public void execute_with(MachineState state)
	{
		int mp = state.get_registerfile().read_register(FrameAccess.get_MP_id());
		ArrayReference aref = new ArrayReference(mp + this.localOffset);
		StackAccess.push(state, aref);
		FlowControl.step(state);
	}
}

