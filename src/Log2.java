import java.util.Map;

public class Log2 implements IExpression {

	IExpression operand;

	public Log2(IExpression op) {
		operand = op;

	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return Math.log(operand.interpret(variables)) / Math.log(2);
	}

}
