package KOM;

import java.awt.*;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.List;

import KOM.Main;


import javax.swing.JPanel;


public class MyPanel extends JPanel {
	ArrayList<Droga> suma;
	public MyPanel(List<Droga> suma2) {
		setPreferredSize(new Dimension(1400, 900));
		this.suma = (ArrayList<Droga>) suma2;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
				
		for(int i=0;i<Main.tabiszon.length;i++) {
			g2d.setColor(java.awt.Color.RED);
			g2d.fillOval(Main.tabiszon[i].x,Main.tabiszon[i].y, 5, 5);
			g2d.setColor(java.awt.Color.BLACK);
			}
		
		for(int i=0; i < suma.size(); i++) {
			Miasto m1 = suma.get(i).getMiasto1();
			Miasto m2 = suma.get(i).getMiasto2();
			g2d.drawLine(m1.getx(), m1.gety(), m2.getx(), m2.gety());
		}
		
//		Miasto m_last = suma.get(suma.size()-1).getMiasto2();
//		Miasto m_first = suma.get(0).getMiasto1();
//		System.out.println(suma.get(suma.size()-1).getMiasto2());
//		g2d.drawLine(m_last.getx(), m_last.gety(), m_first.getx(), m_first.gety());
		
	
}
}