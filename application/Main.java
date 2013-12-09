package application;


import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Arrays;
import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.StackAccess;
import virtualmachine.RandomAccess;
import jim.InstructionMapping;
import jim.type.IntegerType;
import jim.instruction.IMst;
import jim.instruction.Cup;
import jim.instruction.Halt;
import parser.NoCommentReader;
import parser.Tokenizer;
import parser.ParseUnit;
import parser.ParseFailure;


/** Main application class containing static main method.
 * */
public class Main
{
	/** Static instruction mapping used to parse input tokens into instructions.
	 * */
	public static final InstructionMapping jimMap = new InstructionMapping();


	/** Execute JIM.
	 *
	 * @param args The filename, and possible debug options: --debug-step,
	 * --debug-main, --debug-typed-memory, --debug-all | -d
	 *  */
	public static void main(String[] args) throws FileNotFoundException
	{
		// Parsed parameter settings
		boolean debugStep = false;
		boolean debugRandomAccess = false;
		boolean debugMain = false;
		String filename = null;
		// Parse commandline arguments
		for (String arg : args)
		{
			if (arg.equals("--debug-step"))
			{
				debugStep = true;
			}
			else if (arg.equals("--debug-typed-memory"))
			{
				debugRandomAccess = true;
			}
			else if (arg.equals("--debug-main"))
			{
				debugMain = true;
			}
			else if (arg.equals("-d") || arg.equals("--debug-all"))
			{
				debugStep = true;
				debugRandomAccess = true;
				debugMain = true;
			}
			else
			{
				if (filename != null)
				{
					System.err.format("Only one file supported, using \"%s\"%n as input",
							filename);
				}
				else
				{
					filename = arg;
				}
			}
		}

		// Parse and compile variables
		FileReader reader = null;
		Tokenizer tokenizer = null;
		ParseUnit unit = null;
		Instruction[] code = null;
		Instruction[] init = null;
		Type mainReference = null;
		IntegerType entryPoint = null;
		// Open file and parse it
		if (debugMain)
		{
			System.err.format("Opening file \"%s\"...%n",
					filename);
		}
		try
		{
			reader = new FileReader(filename);
		}
		catch (FileNotFoundException fe)
		{
			System.err.format("File \"%s\" not found!%n",
					filename);
			System.exit(1);
		}
		if (debugMain)
		{
			System.err.format("Parse file ...%n");
		}
		tokenizer = new Tokenizer(new NoCommentReader(reader));
		unit = new ParseUnit(jimMap.get_mapping(), tokenizer);
		// Compile parsed unit
		code = unit.evaluate();
		if (code == null)
		{
			System.err.format("Parsing file failed!%n");
			for (ParseFailure f : unit.get_failures())
			{
				System.err.format("  %s%n", f.toString());
			}
			System.exit(1);
		}
		// Get reference to main function
		mainReference = unit.evaluate_reference("main");
		if (mainReference == null)
		{
			System.err.format("\"main\" reference not found!%n");
			System.exit(1);
		}
		if (!(mainReference instanceof IntegerType))
		{
			System.err.format("\"main\" references wrong type \"%s\"%n",
					mainReference.getClass().getName());
			System.exit(1);
		}
		entryPoint = (IntegerType) mainReference;
		// Add init code to call main function
		init = new Instruction[3];
		init[0] = new IMst();
		init[1] = new Cup(new IntegerType(0), entryPoint);
		init[2] = new Halt();
		int initEntry = code.length;
		// Concat unit code and init code
		code = Arrays.copyOf(code, initEntry + init.length);
		for (int iterator = 0; iterator < init.length; iterator++)
		{
			code[iterator + initEntry] = init[iterator];
		}

		// Variables to run
		VM vm = null;
		// Start vm, entry at first init instruction.
		if (debugMain)
		{
			System.err.format("Starting vm ...%n");
		}
		vm = new VM(code, initEntry, 1024);
		vm.set_step_debugging(debugStep);
		RandomAccess.set_debugging(debugRandomAccess);
		
		// Run vm
		while (vm.good())
		{
			vm.step();
		}
	
		// VM exited, get exit code
		if (debugMain)
		{
			System.err.format("VM shut down, reading exit code ...%n");
		}
		IntegerType result = new IntegerType();
		StackAccess.pop(vm, result);
		System.out.println("Exit code = " + Integer.toString(result.get_int()));
	}
}

