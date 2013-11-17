
/** Memory interface abstraction.
 *
 * This interface is used to access the VM memory. Its purpose is
 * to hide the implementation of the Memory from its users.
 * */
public interface Memory
{
	/** Read a chunk from memory.
	 *
	 * This method shall be used to read a chunk from memory. The
	 * chunk is specified by its position and size.
	 *
	 * @param position The position from where to read the chunk.
	 * @param num The size of the chunk in number of bytes.
	 * @return A byte array with the contents of the chunk.
	 * */
	public abstract byte[] read_memory(int position, int num);


	/** Write a chunk of memory.
	 *
	 * This method shall be used to write a chunk of memory. The
	 * chunk is specified by its position and the length of the
	 * given byte array.
	 *
	 * @param position The position to which data will be written.
	 * @param data The data (and the number of bytes) to be written.
	 * */
	public abstract void write_memory(int position, byte[] data);
}


