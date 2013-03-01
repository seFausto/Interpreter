import java.util.Map;

public class Cos implements IExpression {

	IExpression operand;

	public Cos(IExpression op) {
		operand = op;

	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return Math.cos(operand.interpret(variables));
	}

}
