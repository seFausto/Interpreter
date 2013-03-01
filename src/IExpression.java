import java.util.Map;

public interface IExpression {
	public double interpret(Map<String, IExpression> variables);
}
