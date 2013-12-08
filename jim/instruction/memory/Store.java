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


	/** Protected member variable storing the class of the underlying type.
	 *
	 * This member is used to create new instances of the underlying type.
	 * */
	protected Class type;


	/** Constructor setting type class variable and index.
	 *
	 * @see #type
	 * @see #index
	 *
	 * @param type The underlying type.
	 * @param index The frame index to which something gets stored.
	 * */
	public Store(Class type, IntegerType index)
	{
		this.type = type;
		this.index = index.get_int();
	}


	/** Implementation of a generic store instruction.
	 *
	 * A value of the underlying type is popped from the stack and written
	 * to the frame index specified.
	 * */
	public void execute_with(MachineState state)
	{
		Type var;
		try
		{
			var	= (Type) this.type.newInstance();
		} catch (Exception e)
		{
			throw new UnsupportedOperationException(e);
		}
		StackAccess.pop(state, var);
		FrameAccess.write(state, var, this.index);
		FlowControl.step(state);
	}
}

