import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final class SaveState implements KeyListener {
		public void saveFrameValues() {
			FrameValues newFrame = new FrameValues();

			newFrame.cellA = ((Frame) Frame.getFrames()[0]).cellA.getText();
			newFrame.cellB = ((Frame) Frame.getFrames()[0]).cellB.getText();
			newFrame.cellC = ((Frame) Frame.getFrames()[0]).cellC.getText();
			newFrame.cellD = ((Frame) Frame.getFrames()[0]).cellD.getText();
			newFrame.cellE = ((Frame) Frame.getFrames()[0]).cellE.getText();
			newFrame.cellF = ((Frame) Frame.getFrames()[0]).cellF.getText();
			newFrame.cellG = ((Frame) Frame.getFrames()[0]).cellG.getText();
			newFrame.cellH = ((Frame) Frame.getFrames()[0]).cellH.getText();
			newFrame.cellI = ((Frame) Frame.getFrames()[0]).cellI.getText();

			UndoHistory.undoHistory.add(newFrame);
		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}

		@Override
		public void keyReleased(KeyEvent arg0) {

		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			saveFrameValues();

		}
	}

	private final class ExpressionProcessor implements ActionListener {
		List<String> references = new ArrayList<String>();

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				JTextField cell;
				JTextField resultCell;

				for (int i = 0; i < _NumberOfCells; i++) {
					references = new ArrayList<String>();

					switch (i) {
					case 0:
						references.add("$A");
						cell = cellA;
						resultCell = resultA;
						break;
					case 1:
						references.add("$B");
						cell = cellB;
						resultCell = resultB;
						break;
					case 2:
						references.add("$C");
						cell = cellC;
						resultCell = resultC;
						break;
					case 3:
						references.add("$D");
						cell = cellD;
						resultCell = resultD;
						break;
					case 4:
						references.add("$E");
						cell = cellE;
						resultCell = resultE;
						break;
					case 5:
						references.add("$F");
						cell = cellF;
						resultCell = resultF;
						break;
					case 6:
						references.add("$G");
						cell = cellG;
						resultCell = resultG;
						break;
					case 7:
						references.add("$H");
						cell = cellH;
						resultCell = resultH;
						break;
					case 8:
						references.add("$I");
						cell = cellI;
						resultCell = resultI;
						break;
					default:
						cell = new JTextField();
						resultCell = new JTextField();
					}

					calculate(cell, resultCell);
				}
			} catch (Exception e) {
				// throw error
			}
		}

		private void calculate(JTextField contentCell, JTextField resultCell)
				throws Exception {

			// Hash map with values to use in interpret function
			Map<String, IExpression> variables = new HashMap<String, IExpression>();

			// Create new expression string
			String expression = "";
			String cellContents = contentCell.getText().trim();
			int letterCount = 0;

			if (cellContents.length() > 0) {
				// Loop through the whole string
				while (expression == "" || expression.contains("$")
						|| expression.matches(".*\\d.*")) {
					expression = "";
					for (String token : cellContents.split(" ")) {

						if (token.substring(0, 1).matches("[A-Za-z]")) {
							expression += " " + token;
							continue;
						}

						if (isValidReference(token)) {
							if (references.contains(token)) {
								expression = "Error";
								break;

							}
							// Replace references with value from
							String referenceContent = getValueFromCell(token);

							if (isNumber(referenceContent)
									|| isOperation(referenceContent)) {
								token = referenceContent;
							} else {
								expression += " " + referenceContent;
							}
							references.add(token);
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

				if (!expression.equals("Error")) {
					// Send this expression to the Evaluator
					Evaluator sentence = new Evaluator(expression);
					// Call the interpret function
					double result = sentence.interpret(variables);
					// Show result to result Cell (or wherever)
					resultCell.setText(String.valueOf(result));
				} else {
					resultCell.setText("Circula Reference");

				}
			}

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

			if (result.trim().length() == 0) {
				result = "0";
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
	}

	final int _ARepresentedInInt = 65;

	JPanel pane = new JPanel();
	public JTextField cellB = new JTextField();
	public JTextField cellC = new JTextField();
	public JTextField cellD = new JTextField();
	public JTextField cellE = new JTextField();
	public JTextField cellF = new JTextField();
	public JTextField cellG = new JTextField();
	public JTextField cellA = new JTextField();
	public JTextField cellH = new JTextField();
	public JTextField cellI = new JTextField();

	JTextField resultA = new JTextField();
	JTextField resultB = new JTextField();
	JTextField resultC = new JTextField();
	JTextField resultD = new JTextField();
	JTextField resultE = new JTextField();
	JTextField resultF = new JTextField();
	JTextField resultG = new JTextField();
	JTextField resultH = new JTextField();
	JTextField resultI = new JTextField();
	JLabel lblResult;

	final int _startingX = 500;
	final int _startingY = 500;
	final int _width = 1000;
	final int _height = 500;

	final int _NumberOfCells = 9;

	Dimension _defaultTextBoxDimension = new Dimension(100, 30);

	public void saveState() {

	}

	Frame() {

		super("Interpreter");
		setBounds(_startingX, _startingY, _width, _height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container con = this.getContentPane();

		con.add(pane);

		// Text boxes

		cellA.setPreferredSize(_defaultTextBoxDimension);
		cellA.addKeyListener(new SaveState());

		cellB.setPreferredSize(_defaultTextBoxDimension);
		cellB.addKeyListener(new SaveState());

		cellC.setPreferredSize(_defaultTextBoxDimension);
		cellC.addKeyListener(new SaveState());

		cellD.setPreferredSize(_defaultTextBoxDimension);
		cellD.addKeyListener(new SaveState());

		cellE.setPreferredSize(_defaultTextBoxDimension);
		cellE.addKeyListener(new SaveState());

		cellF.setPreferredSize(_defaultTextBoxDimension);
		cellF.addKeyListener(new SaveState());

		cellG.setPreferredSize(_defaultTextBoxDimension);
		cellG.addKeyListener(new SaveState());

		cellH.setPreferredSize(_defaultTextBoxDimension);
		cellH.addKeyListener(new SaveState());

		cellI.setPreferredSize(_defaultTextBoxDimension);
		cellI.addKeyListener(new SaveState());

		pane.add(cellA);
		pane.add(cellB);
		pane.add(cellC);
		pane.add(cellD);
		pane.add(cellE);
		pane.add(cellF);
		pane.add(cellG);
		pane.add(cellH);
		pane.add(cellI);

		resultA.setPreferredSize(_defaultTextBoxDimension);

		resultB.setPreferredSize(_defaultTextBoxDimension);

		resultC.setPreferredSize(_defaultTextBoxDimension);

		resultD.setPreferredSize(_defaultTextBoxDimension);

		resultE.setPreferredSize(_defaultTextBoxDimension);

		resultF.setPreferredSize(_defaultTextBoxDimension);

		resultG.setPreferredSize(_defaultTextBoxDimension);

		resultH.setPreferredSize(_defaultTextBoxDimension);

		resultI.setPreferredSize(_defaultTextBoxDimension);

		pane.add(resultA);
		pane.add(resultB);
		pane.add(resultC);
		pane.add(resultD);
		pane.add(resultE);
		pane.add(resultF);
		pane.add(resultG);
		pane.add(resultH);
		pane.add(resultI);

		// Buttons
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ExpressionProcessor());

		pane.add(btnCalculate);

		JButton btnRecallState = new JButton("Undo");
		btnRecallState.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (UndoHistory.undoHistory.size() > 0) {
					setFrameValues();
					UndoHistory.undoHistory.remove(UndoHistory.undoHistory
							.size() - 1);
				}

			}

			public void setFrameValues() {
				((Frame) Frame.getFrames()[0]).cellA
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellA);
				((Frame) Frame.getFrames()[0]).cellB
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellB);
				((Frame) Frame.getFrames()[0]).cellC
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellC);
				((Frame) Frame.getFrames()[0]).cellD
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellD);
				((Frame) Frame.getFrames()[0]).cellE
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellE);
				((Frame) Frame.getFrames()[0]).cellF
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellF);
				((Frame) Frame.getFrames()[0]).cellG
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellG);
				((Frame) Frame.getFrames()[0]).cellH
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellH);
				((Frame) Frame.getFrames()[0]).cellI
						.setText(UndoHistory.undoHistory
								.get(UndoHistory.undoHistory.size() - 1).cellI);

			}
		});

		pane.add(btnRecallState);

		setVisible(true);

	}
}
