package virtualmachine;


/** Interface to access the state of the VM.
 *
 * This interface is intented to encapsulate all state of the VM.
 * The state currently consits of the register file and the memory.
 * 
 * @see virtualmachine.RegisterFile
 * @see virtualmachine.Memory
 * */
public interface MachineState
{
	/** Provide access to the register file.
	 *
	 * This method returns a reference to the register file, thus
	 * providing access to the registers of the VM.
	 *
	 * @return The register file.
	 * */
	public abstract RegisterFile get_registerfile();


	/** Provide access to VMs memory.
	 *
	 * This method returns a reference to the memory interface, which
	 * in turn provides access to the VMs memory.
	 *
	 * @return The memory interface.
	 * */
	public abstract Memory get_memory();
}

