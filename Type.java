
/** Type interface every Type must implement.
 *
 * This interface specifies important operations on types such that
 * specific type instances can be used along with RandomAccess, StackAccess
 * and FrameAccess. Unfortunately, this design is quite memory allocation
 * intensive.
 *
 * @see RandomAccess
 * @see StackAccess
 * @see FrameAccess
 *  */
public interface Type
{
	/** Every type must have a size.
	 *
	 * The size of the type must be known a priori to calls to from. This limits
	 * the capabilities of a builtin type.
	 * 
	 * @return The size of the type
	 * */
	public abstract int size(void);


	/** Every type must be able to construct itself from an array of bytes.
	 *
	 * This byte array must (and will) have the same size as reported by the size
	 * method. After a call to this method the type instance should be in a
	 * useable state.
	 *
	 * @see #size() size method of Type interface
	 *
	 * @param data The byte array passed to construct the type instance.
	 * */
	public abstract void from(byte[] data);


	/** Every type must be able to destruct itself into an array of bytes.
	 *
	 * The byte array returned must have the same size as reported by the size
	 * method. After a call to this method the type instance may retain a valid
	 * state. The type instance must be able to reconstruct itself from the
	 * array returned by this method using the from method.
	 *
	 * @see #from(byte[])
	 *
	 * @return Byte array representing one instance of the type.
	 * */
	public abstract byte[] to();
}

