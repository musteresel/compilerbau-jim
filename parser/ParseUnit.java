package parser;


import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import virtualmachine.Type;
import virtualmachine.Instruction;
import jim.type.IntegerType;
import jim.type.DoubleType;
import parser.Tokenizer;
import parser.Token;
import parser.InstructionStub;
import parser.ValueStub;
import parser.EvaluatedValueStub;
import parser.ReferencedValueStub;
import parser.ParseFailure;


/** A unit to be parsed.
 *
 * An instance of this class represents a parsed unit, with possible failures.
 * The token source must be provided to the constructor.
 * */
public class ParseUnit
{
	/** List of instruction stubs.
	 *
	 * Ordered list of instruction stubs, in the same order as they appear
	 * in the input token stream.
	 * */
	protected List<InstructionStub> instructions;


	/** Mapping of labels to type instances.
	 *
	 * This map contains the declared mappings of the parse unit.
	 * */
	protected Map<Token, Type> referenceMap;


	/** List of parse failures.
	 *
	 * This list contains failures that happened during parsing, in order of
	 * their occurence during parsing.
	 * */
	protected List<ParseFailure> failures;


	/** Construct a parse unit.
	 *
	 * To construct a parse unit - or parse an unit - one must supply a
	 * mapping of String values to instruction classes and a tokenizer
	 * as the source to parse.
	 *
	 * @param instructionMap Mapping of instruction names to their classes.
	 * @param tokenizer Source to parse.
	 * */
	public ParseUnit(
			Map<String, Class<Instruction>> instructionMap,
			Tokenizer tokenizer)
	{
		this.instructions = new LinkedList<InstructionStub>();
		this.referenceMap = new HashMap<Token, Type>();
		int instructionCounter = 0;
		// Token to be used in the next mapping.
		Token nextMapping = null;
		// Set to the last added instruction, or null, if last processed token
		// was not an instruction or part thereof.
		InstructionStub lastInstructionStub = null;
		while (tokenizer.hasToken())
		{
			Token token = tokenizer.nextToken();
			// Check whether the token is an instruction.
			if (instructionMap.containsKey(token))
			{
				lastInstructionStub =
					new InstructionStub(instructionMap.get(token));
				// If the previous token was a label declaration, map this
				// instructions address to the label.
				if (nextMapping != null)
				{
					this.referenceMap.put(nextMapping,
							new IntegerType(instructionCounter));
					nextMapping = null;
				}
				instructionCounter++;
				this.instructions.add(lastInstructionStub);
			}
			// Check whether token is a label declaration.
			else if (token.endsWith(":"))
			{
				// In case the previous token was a label declaration, there
				// is a parse failure.
				if (nextMapping != null)
				{
					this.log_failure(new ParseFailure("Label declaration of " +
								token + " (Line " + Integer.toString(token.getLineNumber()) +
								") after declaration of " + nextMapping + " (Line " +
								Integer.toString(nextMapping.getLineNumber()) + ")."));
				}
				// Try to proceed in order to evaluate as much as possible, and thus
				// providing detailed error reporting.
				nextMapping = token;
				// A label declaration "ends" the parameter list of a previous
				// instruction.
				lastInstructionStub = null;
			}
			// Neither instruction nor declaration, must be value or reference.
			else
			{
				ValueStub valueStub = null;
				Type type = null;
				// Check if token can be converted to any known value type.
				IntegerType iType = new IntegerType();
				DoubleType dType = new DoubleType();
				if (iType.from(token))
				{
					type = iType;
				}
				else if (dType.from(token))
				{
					type = dType;
				}
				// If token could be converted, use the converted type instance.
				if (type != null)
				{
					// If the previous token was a label declaration, map this
					// type to the label.
					if (nextMapping != null)
					{
						this.referenceMap.put(nextMapping, type);
						nextMapping = null;
					}
					// The type is probably used as a parameter to an instruction.
					else
					{
						valueStub = new EvaluatedValueStub(type);
					}

				}
				// In case the token could not be converted, it must be a reference
				// to a somewhere defined label.
				else
				{
					// If the previous token was a label declaration, fail parsing
					// because we do not support recursive label references.
					if (nextMapping != null)
					{
						this.log_failure(new ParseFailure("Label declaration of " +
									nextMapping + " (Line " +
									Integer.toString(nextMapping.getLineNumber()) +
									") tries to use reference " + token + "."));
						nextMapping = null;
					}
					// The reference is probably a parameter to an instruction.
					else
					{
						valueStub = new ReferencedValueStub(token);
					}
				}
				// The token is not used up to this point.
				if (valueStub != null)
				{
					// Try to use it as parameter to the last read instruction.
					if (lastInstructionStub != null)
					{
						lastInstructionStub.push_parameter(valueStub);
					}
					// Token is not used anywhere, this is a failure.
					else
					{
						this.log_failure(new ParseFailure("Orphan token at line " +
									Integer.toString(token.getLineNumber()) + ": " +
									token + "."));
					}
				}
			}
			// Continue with next token.
		}
		this.failures = new LinkedList<ParseFailure>();
	}


	/** Central failure logging method.
	 *
	 * This method is used by any step of the parsing that may fail.
	 * The failure is then logged and the parsing proceeds the best it
	 * can.
	 *
	 * @param failure The new failure to log.
	 * */
	public void log_failure(ParseFailure failure)
	{
		this.failures.add(failure);
	}


	/** Access to this units label mapping.
	 *
	 * This method provides access to the mapping of labels to values. This
	 * is used during evaluation of this unit.
	 *
	 * @param reference The reference to look up.
	 * @return Type instance mapped to the reference, or null for no mapping.
	 * */
	public Type evaluate_reference(Token reference)
	{
		return this.referenceMap.get(reference);
	}


	/** Evaluate this unit.
	 *
	 * Evaluationg returns an array of instructions, or null if anything
	 * fails.
	 *
	 * @return Array of instructions, or null in case of any failure.
	 * */
	public Instruction[] evaluate()
	{
		List<Instruction> evaluatedInstructions = new LinkedList<Instruction>();
		boolean evaluationFailed = false;
		for (InstructionStub instruction : this.instructions)
		{
			Instruction evaluatedInstruction = instruction.evaluate_from(this);
			if (evaluatedInstruction == null)
			{
				evaluationFailed = true;
			}
		}
		if (evaluationFailed)
		{
			this.log_failure(new ParseFailure("Unit evaluation failed due to " +
					"failed instruction evaluation."));
			return null;
		}
		else
		{
			return evaluatedInstructions.toArray(
					new Instruction[evaluatedInstructions.size()]);
		}
	}
}

