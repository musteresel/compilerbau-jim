package parser;


import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.lang.reflect.Constructor;
import virtualmachine.Instruction;
import virtualmachine.Type;
import parser.ValueStub;
import parser.ParseUnit;
import parser.ParseFailure;


/** Stub for an instruction.
 *
 * This class represents an instruction with its parameters in not yet
 * evaluated form.
 * */
public class InstructionStub
{
	/** List of parameters.
	 * */
	protected List<ValueStub> parameters;


	/** Class of the represented instruction.
	 *
	 * This is used to find a suitable constructor and build a new instance of
	 * this instruction class on evaluation.
	 * */
	protected Class<Instruction> instruction;


	/** Constructor.
	 *
	 * Initially, no parameters are given.
	 *
	 * @param instruction The class of the represented instruction.
	 * */
	public InstructionStub(Class<Instruction> instruction)
	{
		this.instruction = instruction;
		this.parameters = new LinkedList<ValueStub>();
	}


	/** Add a parameter.
	 *
	 * This method is used by the parsing algorithm to report the instructions
	 * parameters.
	 *
	 * @param parameter Stub of the new parameter.
	 * */
	public void push_parameter(ValueStub parameter)
	{
		this.parameters.add(parameter);
	}


	/** Evaluate instruction stub.
	 *
	 * To evaluate, all parameters must be evaluated first. If every parameter
	 * evaluation succeeds, try to find a matching constructor of the 
	 * represented instruction class. If a suitable constructor is found, a
	 * new instance of the represented instruction is constructed.
	 *
	 * @param unit Unit to do parameter lookups and report failures.
	 * @return A new instruction instance or null if anything went wrong.
	 * */
	public Instruction evaluate_from(ParseUnit unit)
	{
		Type[] evaluatedParameters = new Type[this.parameters.size()];
		ListIterator<ValueStub> iterator = this.parameters.listIterator();
		boolean evaluationFailed = false;
		// Iterate over parameters
		while (iterator.hasNext())
		{
			int index = iterator.nextIndex();
			ValueStub parameter = iterator.next();
			// Try to evaluate parameter
			Type evaluatedParameter = parameter.evaluate_from(unit);
			if (evaluatedParameter == null)
			{
				evaluationFailed = true;
			}
			evaluatedParameters[index] = evaluatedParameter;
		}
		// Some parameter(s) failed to evaluate, do not attempt to construct
		// an instance.
		if (evaluationFailed)
		{
			unit.log_failure(new ParseFailure("Could not evaluate " +
						this.instruction.getName() +
						" due a parameter evaluation failure."));
			return null;
		}
		// All parameters have been successfully evaluated.
		else
		{
		  Constructor[] constructors =
				this.instruction.getConstructors();
			// Iterate over available constructors to find a matching one.
			for (Constructor constructor : constructors)
			{
				try
				{
					// If constructor could create a new instance, evaluation succeeded.
					return (Instruction) constructor.newInstance(
							(Object[]) evaluatedParameters);
				}
				catch (Exception e)
				{
					// NOTE: This is ok here, we're handling this.
					// TODO: Forward this/these exception(s) to log_failure.
				}
			}
			// No constructor was suitable to instantiate a new instance. Report
			// failure.
			unit.log_failure(new ParseFailure("Could not evaluate " +
						this.instruction.getName() +
						" due to parameter mismatch."));
			return null;
		}
	}
}

