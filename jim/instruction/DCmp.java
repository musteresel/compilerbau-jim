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
	/** Compare two double values from the stack.
	 *
	 * Two double values are popped from the stack, compared and the resulting
	 * integer is pushed onto the stack.
	 * */
	public void execute_with(MachineState state)
	{
		DoubleType a = new DoubleType();
		DoubleType b = new DoubleType();
		StackAccess.pop(state, a);
		StackAccess.pop(state, b);
		double aD, bD;
		int result;
		aD = a.get_double();
		bD = b.get_double();
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

