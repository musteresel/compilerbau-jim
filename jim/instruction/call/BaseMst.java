package jim.instruction.call;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FrameAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Generic prepare function call instruction.
 *
 * This instruction reserves space on the stack for the given return type
 * and stores the mark pointer to the stack.
 * */
public abstract class BaseMst implements Instruction
{
	/** Dummy value to avoid unneccessary allocations.
	 * */
	protected static final IntegerType returnAddress = new IntegerType(0);


	/** Dummy instance of the return type.
	 * */
	protected Type returnType;


	/** Constructor setting dummy instance for return type.
	 *
	 * @param type Dummy instance, null for no return type.
	 * */
	protected BaseMst(Type type)
	{
		this.returnType = type;
	}


	/** Prepare function call.
	 *
	 * First, reserve space on the stack by push the return type dummy. Then
	 * push the current mark pointer on the stack. Last reserve space for
	 * the return address.
	 * */
	public void executeWith(MachineState state)
	{
		if (this.returnType != null)
		{
			StackAccess.push(state, this.returnType); // Value does not matter
		}
		int mp = state.get_registerfile().read_register(FrameAccess.get_MP_id());
		IntegerType markPointer = new IntegerType(mp);
		StackAccess.push(state, markPointer);
		StackAccess.push(state, this.returnAddress); // Value does not matter
		FlowControl.step(state);
	}
}

