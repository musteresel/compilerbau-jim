package jim.instruction;


import virtualmachine.Instruction;
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
	/** Protected member variable storing the class of the underlying type.
	 *
	 * This member is used to create new instances of the underlying type.
	 * */
	protected Class type;


	/** Constructor setting type class variable.
	 *
	 * @see #type
	 *
	 * @param type The underlying type.
	 * */
	public Add(Class type)
	{
		this.type = type;
	}


	/** Implementation of a generic math instruction.
	 *
	 * Two values of the same type are popped from the stack, used
	 * in some mathematical operation which produces a result value
	 * of the underlying type, which is then pushed onto the stack.
	 *
	 * @see #operate(NumericType, NumericType)
	 * */
	public void executeWith(MachineState state)
	{
		NumericType a = (NumericType) this.type.newInstance();
		NumericType b = (NumericType) this.type.newInstance();
		StackAccess.pop(state, a);
		StackAccess.pop(state, b);
		StackAccess.push(state, this.operate(a, b));
		FlowControl.step(state);
	}


	/** Mathematical operation.
	 *
	 * This method shall be implemented by derived, concrete math
	 * operations like add, ...
	 *
	 * @param a This is the left hand value of the operation.
	 * @param b This is the right hand value of the operation.
	 * @return The result of the operation.
	 * */
	public abstract NumericType operate(NumericType a, NumericType b);
}

