package KOM;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyFrame extends JFrame {


	public MyFrame(List<Droga> suma) {
		super("Algorytm zachlanny");
		JPanel panel = new MyPanel(suma);
		add(panel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
