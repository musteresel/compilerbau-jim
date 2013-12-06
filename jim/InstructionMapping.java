package jim;


import java.util.Map;
import java.util.HashMap;
import virtualmachine.Instruction;
import jim.instruction.*;


/** Mapping providing a map that maps strings to instruction classes.
 * */
public class InstructionMapping
{
	/** Map from string to instruction class.
	 * */
	protected Map<String, Class<? extends Instruction>> m;


	/** Constructor.
	 *
	 * This constructor fills the map with all implemented instructions.
	 * */
	public InstructionMapping()
	{
		this.m = new HashMap<String, Class<? extends Instruction>>();

		this.m.put("iadd", IAdd.class);
		this.m.put("isub", ISub.class);
		this.m.put("imul", IMul.class);
		this.m.put("idiv", IDiv.class);

		this.m.put("dadd", DAdd.class);
		this.m.put("dsub", DSub.class);
		this.m.put("dmul", DMul.class);
		this.m.put("ddiv", DDiv.class);

		
		this.m.put("istore", IStore.class);
		this.m.put("iload", ILoad.class);
		
		this.m.put("dstore", DStore.class);
		this.m.put("dload", DLoad.class);

		this.m.put("ldc", Ldc.class);


		this.m.put("dcmp", DCmp.class);


		this.m.put("if_icmp!eq", IfICmpNEq.class);
		this.m.put("if_icmp!ne", IfICmpNNe.class);
		this.m.put("if_icmp!lt", IfICmpNLt.class);
		this.m.put("if_icmp!le", IfICmpNLe.class);
		this.m.put("if_icmp!ge", IfICmpNGe.class);
		this.m.put("if_icmp!gt", IfICmpNGt.class);

		this.m.put("if!eq", IfNEq.class);
		this.m.put("if!ne", IfNNe.class);
		this.m.put("if!lt", IfNLt.class);
		this.m.put("if!le", IfNLe.class);
		this.m.put("if!ge", IfNGe.class);
		this.m.put("if!gt", IfNGt.class);

		this.m.put("goto", Goto.class);
	}


	/** Access the map with the mappings.
	 *
	 * @return Map with string to instruction class mapping.
	 * */
	public Map<String, Class<? extends Instruction>> get_mapping()
	{
		return this.m;
	}
}

