import java.util.Map;
import java.util.Stack;

public class Evaluator implements Expression {

	private Expression syntaxTree;

	public Evaluator(String expression) {
		Stack<Expression> expressionStack = new Stack<Expression>();

		for (String token : expression.split(" ")) {
			if (token.equals("+")) {
				// We don't care the order in which this operates
				Expression subExpression = new Plus(expressionStack.pop(),
						expressionStack.pop());
				expressionStack.push(subExpression);
			} else if (token.equals("-")) {
				// since we do care the order on the minus operation
				// we pop the right part of the expression first
				Expression right = expressionStack.pop();
				// then the left
				Expression left = expressionStack.pop();

				Expression subExpression = new Minus(left, right);
				expressionStack.push(subExpression);
			} else if (token.equals("*")) {
				Expression subExpression = new Multiply(expressionStack.pop(),
						expressionStack.pop());
				expressionStack.push(subExpression);
			} else if (token.equals("/")) {
				Expression right = expressionStack.pop();
				Expression left = expressionStack.pop();

				Expression subExpression = new Divide(left, right);
				expressionStack.push(subExpression);
			} else if (token.equals("sin")) {
				Expression subExpression = new Sin(expressionStack.pop());
				expressionStack.push(subExpression);

			} else if (token.equals("cos")) {
				Expression subExpression = new Cos(expressionStack.pop());
				expressionStack.push(subExpression);
			} else if (token.equals("log2")) {
				Expression subExpression = new Log2(expressionStack.pop());
				expressionStack.push(subExpression);
			} else {
				expressionStack.push(new Variable(token));

			}
		}
		
		syntaxTree = expressionStack.pop();

	}

	@Override
	public double interpret(Map<String, Expression> variables) {
		return syntaxTree.interpret(variables);
	}

}
