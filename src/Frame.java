import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Frame extends JFrame {

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
	
	int _startingX=500;
	int _startingY=500;
	int _width=1000;
	int _height= 500;
	
	Dimension _defaultTextBoxDimension = new Dimension(100,30);
	
	Frame() {

		super("Interpreter");
		setBounds(_startingX, _startingY, _width, _height);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container con = this.getContentPane();
		
		con.add(pane);
		
		//Text boxes
		
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
		
		
		//Buttons
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calculate();
				
			}

			private void calculate() {
				String expression ="A B " + cellC.getText();
				
				Evaluator sentence = new Evaluator(expression);
				
				Map<String, Expression> variables = new HashMap<String, Expression>();
				
				variables.put("A", new Number(Double.parseDouble(cellA.getText())));
				variables.put("B", new Number(Double.parseDouble(cellB.getText())));
				
				double result = sentence.interpret(variables);
				
				lblResult.setText("Result: " + result);
			}
		});
		
		pane.add(btnCalculate);
		
		
		lblResult = new JLabel("Result will show here");
		lblResult.setMaximumSize(new Dimension(1000,500));
		pane.add(lblResult);
		
		setVisible(true);

	}


}
