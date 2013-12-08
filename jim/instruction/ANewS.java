package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.RegisterFile;
import virtualmachine.FlowControl;
import jim.type.IntegerType;
import jim.type.ArrayReference;


/** Create a new array on the stack.
 *
 * This instruction is used to create a new, runtime sized array on the current
 * execution stack. Please note that, as there is no runtime typing at all,
 * the array is completely untyped. As such, it may contain different types or
 * even act as a C like union.
 *
 * <strong>Warning:</strong> Do not attempt to return such an array if it has
 * been created in the current stack frame!
 * */
public class ANewS implements Instruction
{
	/** Creates a new array of a given size on the current stack frame.
	 *
	 * First, the size (IntegerType) is popped from the stack. The respective
	 * amount of memory is then reserved (not zeroed!) by increasing the stack
	 * pointer. Finally, the reference to the array is pushed ontop of the stack.
	 * */
	public void execute_with(MachineState state)
	{
		IntegerType size = new IntegerType();
		StackAccess.pop(state, size);
		RegisterFile file = state.get_registerfile();
		int sp = file.read_register(StackAccess.get_SP_id());
		ArrayReference aref = new ArrayReference(sp);
		file.write_register(StackAccess.get_SP_id(), sp + size.get_int());
		StackAccess.push(state, aref);
		FlowControl.step(state);
	}
}

