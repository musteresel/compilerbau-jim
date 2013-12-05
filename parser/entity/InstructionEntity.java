package parser.entity;


import parser.entity.Entity;


/** Entity representing an instruction.
 * */
public class InstructionEntity implements Entity
{
	/** The class of the represented instruction.
	 *
	 * This class will be used to later instantiate an object of the actual
	 * instruction.
	 * */
	protected Class instructionClass;


	/** Linear address of the instruction in the code space.
	 *
	 * This will be the index in the final instruction array. This is mainly
	 * used by Labels to reference instructions.
	 * */
	protected int address;


	/** Line number where the instruction was read.
	 * */
	protected int line;


	/** Constructor taking address, Class and line number.
	 *
	 * @param address The address of the instruction.
	 * @param instructionClass Class used to instantiate the instruction.
	 * @param line Line number the instruction was found on.
	 * */
	public InstructionEntity(int address, Class instructionClass, int line)
	{
		this.address = address;
		this.instructionClass = instructionClass;
		this.line = line;
	}


	/** Return value representation of the instruction.
	 *
	 * @return Address of the instruction.
	 * */
	public int getValue()
	{
		return this.address;
	}
}

