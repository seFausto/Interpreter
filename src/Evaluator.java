import java.util.Map;
import java.util.Stack;

public class Evaluator implements IExpression {

	private IExpression syntaxTree;

	public Evaluator(String expression) {
		Stack<IExpression> expressionStack = new Stack<IExpression>();

		for (String token : expression.split(" ")) {
			if (token.equals("+")) {
				// We don't care the order in which this operates
				IExpression subExpression = new Plus(expressionStack.pop(),
						expressionStack.pop());
				expressionStack.push(subExpression);
			} else if (token.equals("-")) {
				// since we do care the order on the minus operation
				// we pop the right part of the expression first
				IExpression right = expressionStack.pop();
				// then the left
				IExpression left = expressionStack.pop();

				IExpression subExpression = new Minus(left, right);
				expressionStack.push(subExpression);
			} else if (token.equals("*")) {
				IExpression subExpression = new Multiply(expressionStack.pop(),
						expressionStack.pop());
				expressionStack.push(subExpression);
			} else if (token.equals("/")) {
				IExpression right = expressionStack.pop();
				IExpression left = expressionStack.pop();

				IExpression subExpression = new Divide(left, right);
				expressionStack.push(subExpression);
			} else if (token.equals("sin")) {
				IExpression subExpression = new Sin(expressionStack.pop());
				expressionStack.push(subExpression);

			} else if (token.equals("cos")) {
				IExpression subExpression = new Cos(expressionStack.pop());
				expressionStack.push(subExpression);
			} else if (token.equals("log2")) {
				IExpression subExpression = new Log2(expressionStack.pop());
				expressionStack.push(subExpression);
			} else {
				expressionStack.push(new Variable(token));

			}
		}
		
		syntaxTree = expressionStack.pop();

	}

	@Override
	public double interpret(Map<String, IExpression> variables) {
		return syntaxTree.interpret(variables);
	}

}
