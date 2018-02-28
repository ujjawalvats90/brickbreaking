package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class play extends JPanel implements KeyListener,ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean plag=false;
	private int score=0;
	
	private int totalbricks=21;
	
	private int playerx=310;
	
	private Timer time;
	private int delay=8;
	
	private int ballposx=120;
	private int ballposy=350;
	private int ballxdir=-1;
	private int ballydir=-2;
	private brickmap mp;
	
	public play() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time=new Timer(delay,this);
		time.start();
		mp= new brickmap(3,7);
		
	}

	public void paint(Graphics g) {
		
		g.setColor(Color.black);
		
		g.fillRect(1,1,692,592);
		
		mp.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(692,0,3,592);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(""+score, 590, 30);
		
		
		g.setColor(Color.green);
		g.fillRect(playerx, 550, 100, 8);
		

		g.setColor(Color.yellow);
		g.fillOval(ballposx, ballposy, 20, 20);
		if(totalbricks<=0) {
			plag=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.blue);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.drawString("You Won : ", 190, 300);
			g.drawString("Press Enter to Restart", 190, 340);
		}
		
		if(ballposy>570) {
			plag=false;
			ballxdir=0;
			ballydir=0;
			g.setColor(Color.blue);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.drawString("GAME OVER, Score: " + score, 190, 300);
			g.drawString("Press Enter to Restart", 190, 340);
			
		}
		
		
		g.dispose();
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		time.start();
		if(plag)
			{
			if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8))) {
				ballydir= -ballydir;
			}
			
			A:for(int i=0;i<mp.map.length;i++) {
				for(int j=0;j<mp.map[0].length;j++) {
					if(mp.map[i][j]>0) {
						int brickx=j*mp.brickwidth+80;
						int bricky=i*mp.brickheight+50;
						int brickwidth=mp.brickwidth;
						int brickheight=mp.brickwidth;
						
						Rectangle rect= new Rectangle(brickx,bricky,brickwidth,brickheight);
						Rectangle ballRect= new Rectangle(ballposx,ballposy,20,20);
						Rectangle brickrect=rect;
						
						if(ballRect.intersects(brickrect)) {
							mp.setBrick(0, i, j);
							totalbricks--;
							score+=5;
							
							if(ballposx+19<=brickrect.x||ballposx+1>=brickrect.x+brickrect.width) {
								ballxdir=-ballxdir;
							}
							else {
								ballydir=-ballydir;
							}
							break A;
						}
					}
					
				}
			}
			ballposx+=ballxdir;
			ballposy+=ballydir;
			if(ballposx<0)
				ballxdir=-ballxdir;
			if(ballposy<0)
				ballydir=-ballydir;
			if(ballposx>670)
				ballxdir=-ballxdir;
			}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerx>=600)
			{
				playerx=600;
			}
			else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerx<10)
			{
				playerx=10;
			}
			else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!plag) {
				plag=true;
				ballposx=120;
				ballposy=310;
				ballxdir=-1;
				ballydir=-2;
				playerx=310;
				score=0;
				totalbricks=21;
				mp=new brickmap(3,7);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public void moveRight()
	{
		plag = true;
		playerx+=20;
	}
	
	public void moveLeft()
	{
		plag = true;
		playerx-=20;
	}

}
