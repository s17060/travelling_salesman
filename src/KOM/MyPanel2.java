package KOM;

import java.awt.*;
import java.awt.font.TextLayout;
import KOM.Main;


import javax.swing.JPanel;


public class MyPanel2 extends JPanel {
	public MyPanel2() {
		setPreferredSize(new Dimension(1400, 900));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Font font1 = new Font("Courier", Font.BOLD, 24);
		Font font2 = new Font("Courier", Font.BOLD, 20);
		
		for(int i=0;i<Main.tabiszon.length;i++) {
		//TextLayout tl = new TextLayout(Integer.toString(i), font1, g2d.getFontRenderContext());
			g2d.setColor(java.awt.Color.RED);
			g2d.fillOval(Main.tabiszon[i].x,Main.tabiszon[i].y, 5, 5);
			g2d.setColor(java.awt.Color.BLACK);
			//tl.draw(g2d,Main.tabiszon[i].x*+15,Main.tabiszon[i].y+30); //+15,+30
		//	TextLayout tl1 = new TextLayout("["+Integer.toString(Main.tabiszon[i].x)+","+Integer.toString(Main.tabiszon[i].y)+"]", font2, g2d.getFontRenderContext());
			//tl1.draw(g2d,Main.tabiszon[i].x+10,Main.tabiszon[i].y+60); //+15,+30
		}
		for(int i=1; i < Main.lista_miast.size(); i++) {
			Miasto m1 = Main.lista_miast.get(i-1);
			Miasto m2 = Main.lista_miast.get(i);
			
			g2d.drawLine(m1.getx(), m1.gety(), m2.getx(), m2.gety());
		}
		
		
		Miasto m_last = Main.lista_miast.get(Main.lista_miast.size()-1);
		Miasto m_first = Main.lista_miast.get(0);
		g2d.drawLine(m_last.getx(), m_last.gety(), m_first.getx(), m_first.gety());
		
	
}
}