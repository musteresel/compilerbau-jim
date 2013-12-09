package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;


/** Generic print instruction.
 *
 * This instruction acts as a (mainly debugging) interface between the JIM
 * instructions and the "outside". It pops a value of the underlying type
 * from the stack and prints that to stdout.
 * */
public abstract class BasePrint implements Instruction
{
	/** Type instance to store the data.
	 * */
	protected Type data;


	/** Constructor creating the type instance.
	 *
	 * @param type Underlying type of the instruction.
	 * */
	public BasePrint(Class<? extends Type> type)
	{
		try
		{
			this.data = type.newInstance();
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException(e);
		}
	}


	/** Print the value on top of the stack, consuming it.
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.data);
		System.out.format("Debug print requested: %s%n", this.data.toString());
		FlowControl.step(state);
	}
}

