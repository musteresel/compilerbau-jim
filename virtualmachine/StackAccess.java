package virtualmachine;


import virtualmachine.MachineState;
import virtualmachine.Type;
import virtualmachine.RandomAccess;


/** Static helper class to provide typed access to the VMs stack.
 *
 * This class is used to provide typed access to a stack mapped ontop the
 * VMs memory.
 *
 * @see virtualmachine.RandomAccess
 * @see virtualmachine.Type
 * */
public class StackAccess
{
	/** ID of the stackpointer register.
	 *
	 * This protected static variable holds the id of the register which is
	 * used as a stackpointer.
	 * */
	protected static int SP;


	/** Public read access to the stackpointer register ID.
	 *
	 * @see #SP
	 *
	 * @return The ID of the stackpointer register.
	 * */
	public static int get_SP_id()
	{
		return StackAccess.SP;
	}


	/** Public write access to the stackpointer register ID.
	 *
	 * @see #SP
	 *
	 * @param sp The new id to use as the stackpointer register ID.
	 * */
	public static void set_SP_id(int sp)
	{
		StackAccess.SP = sp;
	}


	/** Pop from memory mapped stack into type instance.
	 *
	 * This static method shall be used to pop a typed value from the
	 * set memory mapped stack into a type instance.
	 * Please note that there is no runtim type checking.
	 *
	 * @param state The state to operate on.
	 * @param type The type instance to read into.
	 * */
	public static void pop(MachineState state, Type type)
	{
		RegisterFile file = state.get_registerfile();
		int new_sp = file.read_register(StackAccess.SP) - type.size();
		file.write_register(StackAccess.SP, new_sp);
		RandomAccess.read(state, type, new_sp);
	}


	/** Push to memory mapped stack from type instance.
	 *
	 * This static method provides typed write access to the set memory
	 * mapped stack.
	 *
	 * @param state The state to operate on.
	 * @param type The type instance which will be pushed onto the stack.
	 * */
	public static void push(MachineState state, Type type)
	{
		RegisterFile file = state.get_registerfile();
		int sp = file.read_register(StackAccess.SP);
		/* Before write because type may be invalid after write */
		int new_sp = sp + type.size();
		RandomAccess.write(state, type, sp);
		file.write_register(StackAccess.SP, new_sp);
	}
}

