package jim.instruction.call;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.RegisterFile;
import virtualmachine.StackAccess;
import virtualmachine.FrameAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Generic return instruction.
 *
 * If the return instruction has a return type, it pops this type from
 * the stack and stores it at the predefined location. Then, stack- and
 * markpointers are restored and pc is set to the return address.
 * */
public abstract class BaseReturn implements Instruction
{
	/** Return type, if any.
	 * */
	protected Type returnType;


	/** Constructor.
	 *
	 * @param returnType Return type. Null for no return type.
	 * */
	public BaseReturn(Type returnType)
	{
		this.returnType = returnType;
	}


	/** Return from function.
	 * */
	public void execute_with(MachineState state)
	{
		if (this.returnType != null)
		{
			StackAccess.pop(state, this.returnType);
			FrameAccess.write(state, this.returnType, -this.returnType.size());
		}
		RegisterFile file = state.get_registerfile();
		int newSp = file.read_register(FrameAccess.get_MP_id());
		file.write_register(StackAccess.get_SP_id(), newSp);
		IntegerType returnAddress = new IntegerType();
		IntegerType markPointer = new IntegerType();
		FrameAccess.read(state, markPointer, 0);
		FrameAccess.read(state, returnAddress, 4);
		file.write_register(FrameAccess.get_MP_id(), markPointer.get_int());
		file.write_register(FlowControl.get_PC_id(), returnAddress.get_int());
		FlowControl.step(state);
	}
}

