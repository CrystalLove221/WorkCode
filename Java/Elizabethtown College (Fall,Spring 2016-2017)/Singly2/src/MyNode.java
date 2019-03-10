import javax.swing.JTextField;

public class MyNode extends Node{
	JTextField valueDisplay = new JTextField(3);
	int value;
	
	public MyNode() {
		panel.add(valueDisplay);
	}
	
	public void setValue(int v) {
		value = v;
		valueDisplay.setText(" "+value);
	}
}
