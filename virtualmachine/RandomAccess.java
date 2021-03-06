package virtualmachine;


import virtualmachine.MachineState;
import virtualmachine.Type;


/** Static helper class to provide random access to the VMs memory.
 *
 * This class is used to provide typed random access to the VMs memory,
 * effectively hiding memory access and providing a typed view of the
 * memory.
 *
 * @see virtualmachine.Type
 * */
public class RandomAccess
{
	/** Protected member to enable or disable debug output.
	 * */
	protected static boolean debug = false;


	/** Enable or disable debugging.
	 *
	 * @param debug True to enable debugging.
	 * */
	public static void set_debugging(boolean debug)
	{
		RandomAccess.debug = debug;
	}


	/** Read from memory at a position into a type instance.
	 *
	 * This static method shall be used to read from a position in memory,
	 * assuming its contents have the given type.
	 * Please note that there is no runtime type checking.
	 *
	 * @param state The state with the memory.
	 * @param type The type instance to read into.
	 * @param position Position in memory.
	 * */
	public static void read(MachineState state, Type type, int position)
	{
		byte[] data = state.get_memory().read_memory(position, type.size());
		type.from(data);
		if (RandomAccess.debug)
		{
			System.err.format("  READ  @ %05d: %s%n", position, type.toString());
		}
	}


	/** Write to memory at a position from a type instance.
	 *
	 * This static method shall be used to access a position in memory,
	 * using the given type.
	 *
	 * @param state The state with the memory.
	 * @param type The type instance to be written.
	 * @param position Position in memory.
	 * */
	public static void write(MachineState state, Type type, int position)
	{
		byte[] data = type.to();
		state.get_memory().write_memory(position, data);
		if (RandomAccess.debug)
		{
			System.err.format("  WROTE @ %05d: %s%n", position, type.toString());
		}
	}
}

