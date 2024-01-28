package main_1;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CafeStartEnd extends JFrame{
	
	Container cp;
	JButton btn1;
	JPanel panel1;
	
	public CafeStartEnd(String title) {
		super(title);
		
		cp = this.getContentPane();
		this.setBounds(800, 100, 400, 600);
		this.setBackground(Color.white);
		
		this.setVisible(true);
		
	}
	
	

	public static void main(String[] args) {
	
		new CafeMenu("주문을 해주세요");

	}

}
