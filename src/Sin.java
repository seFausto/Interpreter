import java.util.Map;

public class Sin implements IExpression {

	IExpression operand;

	public Sin(IExpression op) {
		operand = op;

	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return Math.sin(operand.interpret(variables));
	}

}
