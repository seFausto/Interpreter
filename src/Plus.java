import java.util.Map;

public class Plus implements Expression {

	Expression leftOperand;
	Expression rightOperand;

	public Plus(Expression left, Expression right) {
		leftOperand = left;
		rightOperand = right;
	}

	@Override
	public double interpret(Map<String, Expression> variables) {
		return leftOperand.interpret(variables)
				+ rightOperand.interpret(variables);
	}

}
