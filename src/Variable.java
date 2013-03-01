import java.util.Map;


public class Variable implements IExpression{

	private String name;

	public Variable(String name){
		this.name = name;
	}
	
	
	@Override
	public double interpret(Map<String, IExpression> variables) {
		if (variables.get(this.name) == null)
			return 0;
		else
			return variables.get(this.name).interpret(variables);
	}

}
