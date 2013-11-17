package virtualmachine;


import virtualmachine.MachineState;
import virtualmachine.RegisterFile;


/** Static helper class to ease control flow modification.
 *
 * This class is used to provide an abstraction over control flow modifications
 * such as selecting the next instruction or jumps.
 * */
public class FlowControl
{
	/** ID of programm counter register.
	 *
	 * This protected static variable holds the id of the register which is
	 * used to determine the current position in the instruction stream.
	 * */
	protected static int PC;


	/** Public read access to the programm counter register ID.
	 *
	 * @see #PC
	 *
	 * @return The ID of the programm counter register.
	 * */
	public static int get_PC_id()
	{
		return FlowControl.PC;
	}


	/** Public write access to the programm counter register ID.
	 *
	 * @see #PC
	 *
	 * @param pc The new programm counter register ID.
	 * */
	public static void set_PC_id(int pc)
	{
		FlowControl.PC = pc;
	}


	/** Step to the next instruction.
	 *
	 * This should be called when an instruction is completed without
	 * modifying the programm counter (no jump/branch/...).
	 *
	 * @param state The state to operate on.
	 * */
	public static void step(MachineState state)
	{
		RegisterFile file = state.get_registerfile();
		int pc = file.read_register(FlowControl.PC) + 1;
		file.write_register(FlowControl.PC, pc);
	}


	/** Jump to a given destination in the instruction stream.
	 *
	 * This should be used to implement jumping instructions.
	 *
	 * @param state The state to operate on.
	 * @param destination The destination to jump to.
	 * */
	public static void jump(MachineState state, int destination)
	{
		RegisterFile file = state.get_registerfile();
		file.write_register(FlowControl.PC, destination);
	}
}

