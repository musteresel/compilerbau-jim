package jim.instruction.math;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.NumericType;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;


/** Abstract math instruction.
 *
 * This instruction can be used to implement various
 * math instructions via the operate method. This class takes
 * care of loading the right values from the stack and storing the
 * result to the stack.
 * */
public abstract class MathInstruction implements Instruction
{
	/** Protected member variable to store left hand side value.
	 * */
	protected NumericType lhs;


	/** Protected member variable to store right hand side value.
	 * */
	protected NumericType rhs;


	/** Protected member variable to store result of operation.
	 * */
	protected NumericType result;


	/** Constructor creating type instances for lhs, rhs and result.
	 *
	 * @see #lhs
	 * @see #rhs
	 * @see #result
	 *
	 * @param type The underlying type.
	 * */
	public MathInstruction(Class<? extends NumericType> type)
	{
		try
		{
			this.lhs = type.newInstance();
			this.rhs = type.newInstance();
			this.result = type.newInstance();
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException(e);
		}
	}


	/** Implementation of a generic math instruction.
	 *
	 * Two values of the same type are popped from the stack, used
	 * in some mathematical operation which set a result value
	 * of the underlying type, which is then pushed onto the stack.
	 *
	 * @see #operate(NumericType, NumericType)
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.rhs);
		StackAccess.pop(state, this.lhs);
		this.operate();
		StackAccess.push(state, this.result);
		FlowControl.step(state);
	}


	/** Mathematical operation.
	 *
	 * This method shall be implemented by derived, concrete math
	 * operations like add, ...
	 *
	 * When this method is called, {@link #lhs} and {@link #rhs} are set to
	 * the correct values. It's this methods responsibility to set {@link #result}
	 * to the result of the operation.
	 * */
	public abstract void operate();
}

