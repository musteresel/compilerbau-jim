package jim.instruction.math;


import virtualmachine.NumericType;
import jim.instruction.math.MathInstruction;


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
	 * @see jim.instruction.math.MathInstruction
	 *
	 * @param type The underlying type.
	 * */
	public Add(Class<? extends NumericType> type)
	{
		super(type);
	}


	/** Concrete add operation.
	 *
	 * The implementation depends on the underlying type.
	 *
	 * @see jim.instruction.math.MathInstruction#operate()
	 * */
	public void operate()
	{
		this.result.add(this.lhs, this.rhs);
	}
}

