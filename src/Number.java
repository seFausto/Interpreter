import java.util.Map;

public class Number implements IExpression {

	private double number;

	public Number(double number) {
		this.number = number;
	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return this.number;
	}

}
