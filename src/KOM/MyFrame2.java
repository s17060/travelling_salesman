package KOM;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyFrame2 extends JFrame {


	public MyFrame2() {
		super("Algorytm wspinaczkowy");
		JPanel panel = new MyPanel2();
		add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
