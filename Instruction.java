import MachineState;

/** Instruction interface, every instruction must implement this.
 *
 *  An instruction executes with a given state, effectively changing this
 *  state during execution.
 *  */
public interface Instruction
{
	/** Execute the instruction with a given state.
	 *
	 *  @param state The state to execute the instruction with.
	 *  */
	public abstract void executeWith(MachineState state);
}

