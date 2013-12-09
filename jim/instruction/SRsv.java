package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.RegisterFile;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Instruction to reserve bytes on the stack.
 *
 * This instruction reserves a given amount of bytes on the stack. Usefull
 * for array creation.
 * Although first class functions would be much better ...
 * */
public class SRsv implements Instruction
{
	/** The number of bytes to reserve.
	 * */
	protected int reserveSize;


	/** Constructor setting reserve size.
	 *
	 * @param reserveSize The size to reserve.
	 * */
	public SRsv(IntegerType reserveSize)
	{
		this.reserveSize = reserveSize.get_int();
	}


	/** Reserve bytes on the stack.
	 *
	 * Increase the stack pointer such that reserveSize bytes are "skipped".
	 * Those bytes can then be used as an array or uninitialized local variable.
	 * */
	public void execute_with(MachineState state)
	{
		RegisterFile file = state.get_registerfile();
		int sp = file.read_register(StackAccess.get_SP_id());
		file.write_register(StackAccess.get_SP_id(), sp + this.reserveSize);
		FlowControl.step(state);
	}
}

