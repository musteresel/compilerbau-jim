package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.type.DoubleType;
import jim.type.IntegerType;


/** Double compare instruction.
 *
 * This instruction is used to compare two double values. An Integer
 * is pushed as result.
 * */
public class DCmp implements Instruction
{
	/** Type instance to store lhs operand.
	 * */
	protected DoubleType lhs;


	/** Type instance to store rhs operand.
	 * */
	protected DoubleType rhs;


	/** Constructor creating type instances.
	 * */
	public DCmp()
	{
		this.lhs = new DoubleType();
		this.rhs = new DoubleType();
	}


	/** Compare two double values from the stack.
	 *
	 * Two double values are popped from the stack, compared and the resulting
	 * integer is pushed onto the stack.
	 * */
	public void execute_with(MachineState state)
	{
		StackAccess.pop(state, this.rhs);
		StackAccess.pop(state, this.lhs);
		double aD, bD;
		int result;
		aD = this.lhs.get_double();
		bD = this.rhs.get_double();
		if (aD == bD)
		{
			result = 0;
		}
		else if (aD < bD)
		{
			result = -1;
		}
		else
		{
			result = 1;
		}
		IntegerType r = new IntegerType(result);
		StackAccess.push(state, r);
		FlowControl.step(state);
	}
}

