package jim.instruction.math;


import virtualmachine.NumericType;
import jim.instruction.math.MathInstruction;


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
	 * @see jim.instruction.math.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Sub(Class<? extends NumericType> type)
	{
		super(type);
	}


	/** Concrete subtract operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.math.MathInstruction#operate()
	 * */
	public void operate()
	{
		this.result.sub(this.lhs, this.rhs);
	}
}

