package application;


import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import virtualmachine.Instruction;
import virtualmachine.Type;
import virtualmachine.StackAccess;
import jim.InstructionMapping;
import jim.type.IntegerType;
import parser.Tokenizer;
import parser.ParseUnit;
import parser.ParseFailure;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String fileName = args[0];
		FileReader reader = new FileReader(fileName);
		Tokenizer tokenizer = new Tokenizer(reader);
		InstructionMapping jimMap = new InstructionMapping();
		ParseUnit unit = new ParseUnit(jimMap.get_mapping(), tokenizer);
		Type answer = unit.evaluate_reference("main");
		if (answer == null)
		{
			System.out.println("No main label!");
			return;
		}
		Instruction[] code = unit.evaluate();
		if (code == null)
		{
			for (ParseFailure f : unit.get_failures())
			{
				System.out.println(f);
			}
			return;
		}
		IntegerType entry = (IntegerType) answer;
		VM vm = new VM(code, entry.getInt());
		while (vm.good())
		{
			vm.step();
		}
		IntegerType result = new IntegerType();
		StackAccess.pop(vm, result);
		System.out.println("Result = " + Integer.toString(result.getInt()));
		// vm.load_memory(new byte[0]);
		// vm.load_instructions(unit.evaluate(), entryPoint);
	}
}

