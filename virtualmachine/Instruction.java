package virtualmachine;


import virtualmachine.MachineState;


/** Instruction interface, every instruction must implement this.
 *
 * An instruction executes with a given state, effectively changing this
 * state during execution.
 *  */
public interface Instruction
{
	/** Execute the instruction with a given state.
	 *
	 * Note that there is no modification of the programm counter by default.
	 * This has to be implemented by every instruction, preferably using the
	 * FlowControl helper.
	 *
	 * @see virtualmachine.FlowControl
	 *
	 * @param state The state to execute the instruction with.
	 *  */
	public abstract void executeWith(MachineState state);
}

