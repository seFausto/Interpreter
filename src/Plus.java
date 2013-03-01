import java.util.Map;

public class Plus implements IExpression {

	IExpression leftOperand;
	IExpression rightOperand;

	public Plus(IExpression left, IExpression right) {
		leftOperand = left;
		rightOperand = right;
	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return leftOperand.interpret(variables)
				+ rightOperand.interpret(variables);
	}

}
