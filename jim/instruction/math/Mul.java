package jim.instruction.math;


import virtualmachine.NumericType;
import jim.instruction.math.MathInstruction;


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
	 * @see jim.instruction.math.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Mul(Class<? extends NumericType> type)
	{
		super(type);
	}


	/** Concrete mul operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.math.MathInstruction#operate()
	 * */
	public void operate()
	{
		this.result.mul(this.lhs, this.rhs);
	}
}

