import java.util.Map;

public class Log2 implements Expression {

	Expression operand;

	public Log2(Expression op) {
		operand = op;

	}

	@Override
	public double interpret(Map<String, Expression> variables) {
		return Math.log(operand.interpret(variables)) / Math.log(2);
	}

}
