/** General interface to the Virtual Machine.
 *
 * This interface is intented to provide access to the VM from
 * "outside".
 * */
public interface VirtualMachine
{
	/** Step method to execute the next instruction.
	 *
	 * This method is used to execute the next instruction, providing
	 * the user the possibility to run the VM in single step mode.
	 * */
	public abstract void step();


	/** Check whether everything is fine.
	 *
	 * This method shall be used to check if the VM is in a valid state and
	 * can continue execution.
	 *
	 * @return True if execution can continue.
	 * */
	public abstract boolean good();


	/** Provide access to the VMs state.
	 *
	 * This method shall be used by the user to inspect or even change the
	 * state of the VM inbetween instructions.
	 *
	 * @return State of the VM.
	 * */
	public abstract MachineState get_state();
}

