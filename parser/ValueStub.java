package parser;


import virtualmachine.Type;
import parser.ParseUnit;


/** Value stub used by instruction stubs to represent their parameters.
 * */
public interface ValueStub
{
	/** Evalutation may need a parse unit to lookup references or to report
	 * failures.
	 *
	 * @param unit The unit to work with.
	 * @return Evaluated type instance.
	 * */
	public abstract Type evaluate_from(ParseUnit unit);
}

