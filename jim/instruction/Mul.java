package jim.instruction;


import virtualmachine.NumericType;
import jim.instruction.MathInstruction;


/** Generic multiplication instruction.
 *
 * This instruction is the base of all mul instructions. A concrete mul
 * instruction must have an underlying type and specify this type to this
 * classes constructor.
 * */
public abstract class Mul extends MathInstruction
{
	/** Constructor forwarding underlying types class to MathInstruction.
	 *
	 * @see jim.instruction.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Mul(Class type)
	{
		super(type);
	}


	/** Concrete mul operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.MathInstruction#operate(NumericType, NumericType)
	 * */
	public NumericType operate(NumericType a, NumericType b)
	{
		return a.mul(b);
	}
}

