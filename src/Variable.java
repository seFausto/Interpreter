import java.util.Map;


public class Variable implements Expression{

	private String name;

	public Variable(String name){
		this.name = name;
	}
	
	
	@Override
	public double interpret(Map<String, Expression> variables) {
		if (variables.get(this.name) == null)
			return 0;
		else
			return variables.get(this.name).interpret(variables);
	}

}
