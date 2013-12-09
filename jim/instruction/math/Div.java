package jim.instruction.math;


import virtualmachine.NumericType;
import jim.instruction.math.MathInstruction;


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
	 * @see jim.instruction.math.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Div(Class<? extends NumericType> type)
	{
		super(type);
	}


	/** Concrete div operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.math.MathInstruction#operate()
	 * */
	public void operate()
	{
		this.result.div(this.lhs, this.rhs);
	}
}

