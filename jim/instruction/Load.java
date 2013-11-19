package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.Type;
import virtualmachine.FrameAccess;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;


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
	 * @param index The frame index from which something gets loaded.
	 * */
	public Load(Class type, int index)
	{
		this.type = type;
		this.index = index;
	}


	/** Implementation of a generic load instruction.
	 *
	 * A value of the underlying type is read from the frame index and
	 * pushed onto the stack.
	 * */
	public void executeWith(MachineState state)
	{
		Type var = (Type) this.type.newInstance();
		FrameAccess.read(state, var, this.index);
		StackAccess.push(state, var);
		FlowControl.step(state);
	}
}

