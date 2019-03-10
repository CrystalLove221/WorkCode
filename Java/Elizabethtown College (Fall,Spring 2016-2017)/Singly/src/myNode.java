import javax.swing.JTextField;

public class myNode extends Node {

	int value;
	JTextField disp = new JTextField(3);

	public myNode()
	{
		panel.add(disp);
		disp.setEnabled(false);
		setVisible(true);
	}

	public void setV(int v) //set value inside display box
	{
		value = v;
		disp.setText(""+value);
	}
}
