import java.util.Map;
import java.util.HashMap;

public class Start {
	public static void main(String args[]) {
		String expression = "A B + C sin *";
		
		Evaluator sentence = new Evaluator(expression);
		
		Map<String, IExpression> variables = new HashMap<String, IExpression>();
		
		variables.put("A", new Number(1.4));
		variables.put("B", new Number(2.3));
		variables.put("C", new Number(3.7));
	
		
		double result = sentence.interpret(variables);
		
		System.out.println(result);
		
		
		new Frame();
	
	}
}
