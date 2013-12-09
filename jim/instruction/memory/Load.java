package jim.instruction.memory;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.Type;
import virtualmachine.FrameAccess;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Generic load instruction.
 *
 * This instruction is the base of all load instructions. A concrete load
 * instruction must have an underlying type and specify this type to this
 * classes constructor.
 * */
public abstract class Load implements Instruction
{
	/** The index to load from.
	 * */
	protected int index;


	/** Instance of underlying type to hold the data.
	 * */
	protected Type data;


	/** Constructor setting index and creating data type instance.
	 *
	 * @see #data
	 * @see #index
	 *
	 * @param type The underlying type.
	 * @param index The frame index from which something gets loaded.
	 * */
	public Load(Class<? extends Type> type, IntegerType index)
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


	/** Implementation of a generic load instruction.
	 *
	 * A value of the underlying type is read from the frame index and
	 * pushed onto the stack.
	 * */
	public void execute_with(MachineState state)
	{
		FrameAccess.read(state, this.data, this.index);
		StackAccess.push(state, this.data);
		FlowControl.step(state);
	}
}

