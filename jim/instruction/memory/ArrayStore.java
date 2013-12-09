package jim.instruction.memory;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.RandomAccess;
import virtualmachine.FlowControl;
import jim.type.ArrayReference;
import jim.type.IntegerType;


/** Generic save to array instruction.
 *
 * This instruction is used to save a value to a given array at a given
 * offset.
 * */
public abstract class ArrayStore implements Instruction
{
	/** The array reference to be used.
	 * */
	protected ArrayReference aref;


	/** Type instance to hold the index value.
	 * */
	protected IntegerType index;


	/** Type instance to hold the data.
	 * */
	protected Type data;


	/** Constructor taking concrete underlying type.
	 *
	 * This constructor creates the needed instances for the reference, the
	 * index and the data type.
	 *
	 * @param underlyingType The type of the data to load.
	 * */
	public ArrayStore(Class<? extends Type> underlyingType)
	{
		try
		{
			this.data = underlyingType.newInstance();
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException(e);
		}
		this.aref = new ArrayReference();
		this.index = new IntegerType();
	}


	/** Store data to array at offset.
	 *
	 * This method pops the array reference, then the index and finally the
	 * value from the stack and saves the value to reference + index.
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.data);
		StackAccess.pop(state, this.index);
		StackAccess.pop(state, this.aref);
		RandomAccess.write(state, this.data,
				this.aref.get_address() + this.index.get_int());
		FlowControl.step(state);
	}
}

