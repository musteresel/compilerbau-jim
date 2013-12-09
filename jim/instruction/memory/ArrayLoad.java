package jim.instruction.memory;


import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.RandomAccess;
import virtualmachine.FlowControl;
import jim.type.ArrayReference;
import jim.type.IntegerType;


/** Generic load from array instruction.
 *
 * This instruction is used to load a value from a given array with a given
 * offset.
 * */
public abstract class ArrayLoad implements Instruction
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
	public ArrayLoad(Class<? extends Type> underlyingType)
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


	/** Load data from array with offset.
	 *
	 * This method pops the array reference and then the index from the
	 * stack and loads the value from reference + index. This data is then
	 * pushed ontop the stack.
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.aref);
		StackAccess.pop(state, this.index);
		RandomAccess.read(state, this.data,
				this.aref.get_address() + this.index.get_int());
		StackAccess.push(state, this.data);
		FlowControl.step(state);
	}
}

