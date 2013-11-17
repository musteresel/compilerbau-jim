package virtualmachine;


import virtualmachine.MachineState;
import virtualmachine.Type;
import virtualmachine.RandomAccess;


/** Static helper class to provide typed access to a memory mapped stack frame.
 *
 * This class is used to provide typed access to a stack frame mapped onto the
 * VMs memory.
 *
 * @see virtualmachine.RandomAccess
 * @see virtualmachine.Type
 * */
public class FrameAccess
{
	/** ID of the stackbase register.
	 *
	 * This protected static variable holds the id of the register which is
	 * used as a stackbase register or markpointer register.
	 * */
	protected static int MP;


	/** Public read access to the stackbase register ID.
	 *
	 * @see #MP
	 *
	 * @return The ID of the stackbase register.
	 * */
	public static int get_MP_id()
	{
		return FrameAccess.MP;
	}


	/** Public write access to the stackbase register ID.
	 *
	 * @see #MP
	 *
	 * @param mp The new id to use as stackbase register ID.
	 * */
	public static void set_MP_id(int mp)
	{
		FrameAccess.MP = mp;
	}


	/** Read from stack frame at a given offset into a type instance.
	 *
	 * This static method shall be used to read from an offset to the set
	 * stackbase, assuming its contents have the given type.
	 * Please note that there is no runtime type checking.
	 *
	 * @param state The state to operate on.
	 * @param type The type instance to read into.
	 * @param offset The offset from the stack- or framebase.
	 * */
	public static void read(MachineState state, Type type, int offset)
	{
		int mp = state.get_registerfile().read_register(FrameAccess.MP);
		RandomAccess.read(state, type, mp + offset);
	}


	/** Write to stack framed memory at a given offset from a type instance.
	 *
	 * This static method shall be used to write to an offset to the set
	 * stackbase, using the given type.
	 *
	 * @param state The state to operate on.
	 * @param type The type instance to be written.
	 * @param offset The offset from the stackbase.
	 * */
	public static void write(MachineState state, Type type, int offset)
	{
		int mp = state.get_registerfile().read_register(FrameAccess.MP);
		RandomAccess.write(state, type, mp + offset);
	}
}

