import java.util.Map;

public class Multiply implements IExpression {

	IExpression leftOperand;
	IExpression rightOperand;

	public Multiply(IExpression left, IExpression right) {
		leftOperand = left;
		rightOperand = right;
	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return leftOperand.interpret(variables)
				* rightOperand.interpret(variables);
	}

}
