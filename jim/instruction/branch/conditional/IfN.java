package jim.instruction.branch.conditional;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.IntegerType;
import jim.instruction.branch.conditional.expression.BooleanExpression;


/** Generic Integer Compare with 0 branch instruction.
 *
 * This instruction is the base class of all conditional branch instructions
 * with a single argument. If the boolean expression evaluates to false,
 * a jump is executed.
 * */
public abstract class IfN implements Instruction
{
	/** The destination to jump to if the expression is false.
	 * */
	protected int destination;


	/** The expression which determines whether to jump or not.
	 * */
	protected BooleanExpression expression;


	/** Type instance to store left hand side operand of the expression.
	 * */
	protected IntegerType lhs;


	/** Type instance to store right hand side operand of the expression.
	 *
	 * This is fixed to have a zero value.
	 * */
	protected final IntegerType rhs;


	/** Constructor setting expression and destination.
	 *
	 * @param expression The expression to evaluate.
	 * @param destination The destination to eventually jump to.
	 * */
	public IfN(BooleanExpression expression, IntegerType destination)
	{
		this.expression = expression;
		this.destination = destination.get_int();
		this.lhs = new IntegerType();
		this.rhs = new IntegerType(0);
	}


	/** Generic conditional branch instruction.
	 *
	 * A single integer value is popped from the stack and together with a
	 * zero fed to the boolean expression. If the result is false, a jump
	 * to the destination is executed. Otherwise the instruction requests
	 * stepping to the next instruction.
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.lhs);
		if (this.expression.evaluate(this.lhs, this.rhs))
		{
			FlowControl.step(state);
		}
		else
		{
			FlowControl.jump(state, this.destination);
		}
	}
}

