import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;



public class Obstacle extends Rectangle{ //randomize this - have either spikes or normal blocks
	int  width, height, x,y,type;
	double speed = 30;
	boolean surfaceTrue = false;
	Color c;
	ImageIcon obstacle=null;
	static int cnt=0;
	int id=0;
	public Obstacle(int x, int y,int width, int height) {
		id=cnt++;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public void myDraw(Graphics g) {
		if(type == 0) {
			obstacle= new ImageIcon("platform.png");
			
			
		} else if(type == 1) {
			obstacle= new ImageIcon("spike.png");
			
		} else if(type == 2) {
			obstacle= new ImageIcon("spaceshiportal.png");
			
		} else if(type == 3) {
			obstacle= new ImageIcon("portal.png");
			
		}
		g.drawImage(obstacle.getImage(), x, y, width,height,null);
	}
	public void myMove() {
		x-=speed;
	}
	public boolean getYCollision(int x, int y, int h, int w) {
		if(y + h <= this.y && x +w >= this.x &&  x <= width + this.x) {
			return true;
		} else {
			return false;
		}
	}
	public boolean getXCollision(int x, int y, int h, int w) {
		if(x+w>this.x && x<this.x+width && y +h > this.y && y < this.y+this.height) {
			return true;
		} else {
			return false; 
		}
	}
	
	
}
