package jim.instruction;


import virtualmachine.NumericType;
import jim.instruction.MathInstruction;


/** Generic divide instruction.
 *
 * This instruction is the base of all div instructions. A concrete div
 * instruction must have an underlying type and specify this type to this
 * classes constructor.
 * */
public abstract class Div extends MathInstruction
{
	/** Constructor forwarding underlying types class to MathInstruction.
	 *
	 * @see jim.instruction.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Div(Class type)
	{
		super(type);
	}


	/** Concrete div operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.MathInstruction#operate(NumericType, NumericType)
	 * */
	public NumericType operate(NumericType a, NumericType b)
	{
		return a.div(b);
	}
}

