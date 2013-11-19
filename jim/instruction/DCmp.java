package jim.instruction;


import virtualmachine.Instruction;
import virtualmachine.MachineState;
import virtualmachine.StackAccess;
import virtualmachine.FlowControl;
import jim.instruction.DoubleType;
import jim.instruction.IntegerType;


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
	public void executeWith(MachineState state)
	{
		DoubleType a, b;
		StackAccess.pop(state, a);
		StackAccess.pop(state, b);
		double aD, aD;
		aD = a.getDouble();
		bD = b.getDouble();
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
		IntegerType r = result;
		StackAccess.push(state, r);
		FlowControl.step(state);
	}
}

