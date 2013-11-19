package jim.instruction;


import virtualmachine.NumericType;
import jim.instruction.MathInstruction;


/** Generic subtract instruction.
 *
 * This instruction is the base of all subtract instructions. A concrete sub
 * instruction must have an underlying type and specify this type to this
 * classes constructor.
 * */
public abstract class Sub extends MathInstruction
{
	/** Constructor forwarding underlying types class to MathInstruction.
	 *
	 * @see jim.instruction.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Sub(Class type)
	{
		super(type);
	}


	/** Concrete subtract operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.MathInstruction#operate(NumericType, NumericType)
	 * */
	public NumericType operate(NumericType a, NumericType b)
	{
		return a.sub(b);
	}
}

