import java.awt.*;
import java.awt.event.*;
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
	
	int _startingX=500;
	int _startingY=500;
	int _width=1000;
	int _height= 500;
	
	Dimension _defaultTextBoxDimension = new Dimension(100,30);
	
	Frame() {

		super("My Simple Frame");
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
		pane.add(cellG);
		pane.add(cellC);
		pane.add(cellH);
		pane.add(cellD);
		pane.add(cellI);
		pane.add(cellE);
		pane.add(cellF);
		
		
		//Buttons
		JButton btnCalculate = new JButton("Calculate");
		//btnCalculate.addActionListener(arg0);
		pane.add(btnCalculate);
		
		
		JLabel lblResult = new JLabel("Result will show here");
		
		pane.add(lblResult);
		
		setVisible(true);

	}
	
	public void calculate(){
	
	}

}
