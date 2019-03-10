import javax.swing.JFrame;
public class Frame {

	public static void main(String[] args)
	{
		JFrame x = new JFrame();
		
		x.setVisible(true);
		x.setTitle("First win");
		x.setSize(400, 400);
		
		x.setLocation(800, 600);
		
		JFrame y = new JFrame();
		
		y.setVisible(true);
		
		y.setTitle("yay.");
		y.setSize(300, 300);
		y.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		y.setLocationRelativeTo(x);
		
	}
}
