import java.util.Map;

public class Cos implements Expression {

	Expression operand;

	public Cos(Expression op) {
		operand = op;

	}

	@Override
	public double interpret(Map<String, Expression> variables) {
		return Math.cos(operand.interpret(variables));
	}

}
