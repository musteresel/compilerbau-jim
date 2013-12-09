package jim.instruction.memory;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.Type;
import virtualmachine.FrameAccess;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Generic store instruction.
 *
 * This instruction is the base of all store instructions. A concrete store
 * instruction must have an underlying type and specify this type to this
 * classes constructor.
 * */
public abstract class Store implements Instruction
{
	/** The index to store to.
	 * */
	protected int index;


	/** Type instance storing the data.
	 * */
	protected Type data;


	/** Constructor setting index and creating data type instance.
	 *
	 * @see #data
	 * @see #index
	 *
	 * @param type The underlying type.
	 * @param index The frame index to which something gets stored.
	 * */
	public Store(Class<? extends Type> type, IntegerType index)
	{
		try
		{
			this.data = type.newInstance();
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException(e);
		}
		this.index = index.get_int();
	}


	/** Implementation of a generic store instruction.
	 *
	 * A value of the underlying type is popped from the stack and written
	 * to the frame index specified.
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.data);
		FrameAccess.write(state, this.data, this.index);
		FlowControl.step(state);
	}
}

