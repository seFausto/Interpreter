import java.util.Map;

public class Sin implements Expression {

	Expression operand;

	public Sin(Expression op) {
		operand = op;

	}

	@Override
	public double interpret(Map<String, Expression> variables) {
		return Math.sin(operand.interpret(variables));
	}

}
