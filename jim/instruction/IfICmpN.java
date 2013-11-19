package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;


/** Generic Integer Compare branch instruction.
 *
 * This instruction is the base class of all conditional branch instructions.
 * An boolean expression is evaluated, and if it is false a jump is executed.
 * */
public abstract class IfICmpN implements Instruction
{
	/** Boolean expression interface.
	 *
	 * This interface defines the neccessary method an object needs to
	 * implement to act as an boolean expression for this instruction.
	 * */
	public interface BooleanExpression
	{
		/** Evaluation of the boolean expression.
		 *
		 * Feed two IntegerTypes to the boolean expression, returning
		 * a boolean.
		 *
		 * @param a Left hand side value.
		 * @param b Right hand side value.
		 * @return Boolean result value.
		 * */
		public abstract boolean evaluate(IntegerType a, IntegerType b);
	}


	/** The destination to jump to if the expression is false.
	 * */
	protected int destination;


	/** The expression which determines whether to jump or not.
	 * */
	protected BooleanExpression expression;


	/** Constructor setting expression and destination.
	 *
	 * @param expression The expression to evaluate.
	 * @param destination The destination to eventually jump to.
	 * */
	public IfICmpN(BooleanExpression expression, int destination)
	{
		this.expression = expression;
		this.destination = destination;
	}


	/** Generic conditional branch instruction.
	 *
	 * Two integer values are popped from the stack and then somehow evaluated to
	 * a boolean value. If the result is false, a jump to the destination is
	 * executed, otherwise a step to the next instruction requested.
	 * */
	public void executeWith(MachineState state)
	{
		IntegerType a = new IntegerType();
		IntegerType b = new IntegerType();
		StackAccess.pop(state, a);
		StackAccess.pop(state, b);
		if (this.expression.evaluate(a, b))
		{
			FlowControl.step(state);
		}
		else
		{
			FlowControl.jump(state, this.destination);
		}
	}
}

