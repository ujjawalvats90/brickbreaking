package brickbreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame();
		play pl=new play();
		
		frame.setBounds(10,10,700,600);
		frame.setTitle("Break the Brick");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pl);
		frame.setVisible(true);

	}

}
