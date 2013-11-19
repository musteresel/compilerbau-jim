package jim.instruction;


import virtualmachine.NumericType;
import jim.instruction.MathInstruction;


/** Generic add instruction.
 *
 * This instruction is the base of all add instructions. A concrete add
 * instruction must have an underlying type and specify this type to this
 * classes constructor.
 * */
public abstract class Add extends MathInstruction
{
	/** Constructor forwarding underlying types class to MathInstruction.
	 *
	 * @see jim.instruction.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Add(Class type)
	{
		super(type);
	}


	/** Concrete add operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.MathInstruction#operate(NumericType, NumericType)
	 * */
	public NumericType operate(NumericType a, NumericType b)
	{
		return a.add(b);
	}
}

