import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class square extends JButton {	

	public static boolean mousedown = false;
	public boolean alive = false;
	public boolean birth = false;
	public boolean death = false;

/*	private class Listener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0)
		{
			alive = !alive;

			if(alive == true)
				setBackground(Color.BLACK);

			else
				setBackground(null);
		}
	}
	*/

	private class lifedrag implements MouseListener
	{
		public void mouseClicked(MouseEvent e) {		

			alive = !alive;

			if(alive == true)
				setBackground(Color.BLACK);

			else
				setBackground(null);				

		}

		public void mouseEntered(MouseEvent e) {

			if(mousedown == true)
			{
				alive = !alive;

				if(alive == true)
					setBackground(Color.BLACK);

				else
					setBackground(null);
			}
		}

		public void mousePressed(MouseEvent e) {

			mousedown = true;
		}

		public void mouseReleased(MouseEvent e) {

			mousedown = false;
		}

		public void mouseExited(MouseEvent e) {

		}
	}

	public void life()
	{
		addMouseListener(new lifedrag());
	}
}