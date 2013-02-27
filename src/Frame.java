import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.*;

public class Frame extends JFrame {

	final int _ARepresentedInInt = 65;

	JPanel pane = new JPanel();
	JTextField cellA = new JTextField();
	JTextField cellB = new JTextField();
	JTextField cellC = new JTextField();
	JTextField cellD = new JTextField();
	JTextField cellE = new JTextField();
	JTextField cellF = new JTextField();
	JTextField cellG = new JTextField();
	JTextField cellH = new JTextField();
	JTextField cellI = new JTextField();
	JLabel lblResult;

	int _startingX = 500;
	int _startingY = 500;
	int _width = 1000;
	int _height = 500;

	Dimension _defaultTextBoxDimension = new Dimension(100, 30);

	Frame() {

		super("Interpreter");
		setBounds(_startingX, _startingY, _width, _height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container con = this.getContentPane();

		con.add(pane);

		// Text boxes

		cellA.setPreferredSize(_defaultTextBoxDimension);

		cellB.setPreferredSize(_defaultTextBoxDimension);

		cellC.setPreferredSize(_defaultTextBoxDimension);

		cellD.setPreferredSize(_defaultTextBoxDimension);

		cellE.setPreferredSize(_defaultTextBoxDimension);

		cellF.setPreferredSize(_defaultTextBoxDimension);

		cellG.setPreferredSize(_defaultTextBoxDimension);

		cellH.setPreferredSize(_defaultTextBoxDimension);

		cellI.setPreferredSize(_defaultTextBoxDimension);

		pane.add(cellA);
		pane.add(cellB);
		pane.add(cellC);
		pane.add(cellD);
		pane.add(cellE);
		pane.add(cellF);
		pane.add(cellG);
		pane.add(cellH);
		pane.add(cellI);

		// Buttons
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				calculate();
			}

			private void calculate() {

				// Hash map with values to use in interpret function
				Map<String, Expression> variables = new HashMap<String, Expression>();

				// if (isValidExpression(expression.trim())) {
				// lblResult.setText("Result: " + expression);
				//
				// } else {
				// lblResult.setText("Result: not good");
				// }

				// Create new expression string
				String expression = "";
				String cellContents = cellA.getText();

				int letterCount = 0;

				// Loop through the whole string
				while (expression == "" || expression.contains("$")) {
					expression = "";
					for (String token : cellContents.split(" ")) {

						if (token.substring(0, 1).matches("[A-Za-z]")) {
							expression += " " + token;
							continue;
						}

						if (isValidReference(token)) {
							// Replace references with value from
							String referenceContent = getValueFromCell(token);
							if (isNumber(referenceContent)
									|| isOperation(referenceContent)) {
								token = referenceContent;
							} else {
								expression += " " + referenceContent;
							}
						}

						if (isOperation(token)) {
							expression += " " + token;
						}

						if (isNumber(token)) {
							char ch = (char) (_ARepresentedInInt + letterCount);
							String s = Character.toString(ch);

							variables.put(s,
									new Number(Double.parseDouble(token)));

							expression += " " + s;

							letterCount++;
						}

					}
					cellContents = expression.trim();
				}
				// Send this expression to the Evaluator
				Evaluator sentence = new Evaluator(expression);

				// Call the interpret function
				double result = sentence.interpret(variables);

				// Show result to result Cell (or wherever)

			}

			private String getValueFromCell(String token) {
				String result = "";

				if (token.equals("$A")) {
					result = cellA.getText();
				} else if (token.equals("$B")) {
					result = cellB.getText();
				} else if (token.equals("$C")) {
					result = cellC.getText();
				} else if (token.equals("$D")) {
					result = cellD.getText();
				} else if (token.equals("$E")) {
					result = cellE.getText();
				} else if (token.equals("$F")) {
					result = cellF.getText();
				} else if (token.equals("$G")) {
					result = cellG.getText();
				} else if (token.equals("$H")) {
					result = cellH.getText();
				} else if (token.equals("$I")) {
					result = cellI.getText();
				}

				return result;
			}

			private Boolean isValidReference(String value) {
				Boolean result = true;

				for (String token : value.split(" ")) {
					if (!token.startsWith("$")) {
						result = false;
					}
				}

				return result;
			}

			private Boolean isValidExpression(String value) {
				Boolean result = true;

				for (String token : value.split(" ")) {

					if (!isNumber(token) && !isOperation(token)
							&& !isValidReference(token)) {
						result = false;
						break;
					}
				}

				return result;

			}

			private Boolean isNumber(String value) {

				try {
					Double.parseDouble(value);
				} catch (NumberFormatException e) {
					return false;
				}

				return true;

			}

			private Boolean isOperation(String value) {
				Boolean result = false;

				if (value.equals("+") || value.equals("-") || value.equals("*")
						|| value.equals("/") || value.equals("log2")
						|| value.equals("sin") || value.equals("cos")) {
					result = true;
				}

				return result;

			}
		});

		pane.add(btnCalculate);

		lblResult = new JLabel("Result will show here");
		lblResult.setMaximumSize(new Dimension(1000, 500));
		pane.add(lblResult);

		setVisible(true);

	}

}
