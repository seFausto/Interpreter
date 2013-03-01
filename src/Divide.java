import java.util.Map;

public class Divide implements IExpression {

	IExpression leftOperand;
	IExpression rightOperand;

	public Divide(IExpression left, IExpression right) {
		leftOperand = left;
		rightOperand = right;
	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return leftOperand.interpret(variables)
				/ rightOperand.interpret(variables);
	}

}
