package virtualmachine;


/** Interface to access registers of the VM.
 *
 * Every register access should be done through this interface. It is
 * used to hide the concrete implementation of the register file.
 * Registers must have type int.
 * */
public interface RegisterFile
{
	/** Read data from a register with a given id.
	 *
	 * @param id The id of the register to be read.
	 * @return The contents of the register.
	 * */
	public abstract int read_register(int id);


	/** Write data to a register with a given id.
	 *
	 * @param id The id of the register to be written.
	 * @param data The new contents of the register.
	 * */
	public abstract void write_register(int id, int data);
}

