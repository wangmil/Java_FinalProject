import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Obstacle extends Rectangle{ //randomize this - have either spikes or normal blocks
	int  width, height;
	Color c;
	static int cnt=0;
	int id=0;
	public Obstacle() {
		id=cnt++;
		x= 900;
		y= 425;
		width = 400;
		height = 50;
		c= new Color(0,0,0);
	}
	public void myDraw(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
		g.drawString("ID: " +id, x, y-25);
		g.setColor(new Color(255,255,255));
		g.drawRect(x, y, width ,height);
	}
	public void myMove() {
		x-=30;
	}
	public boolean getYCollision(int x, int y, int h, int w) {
		if(y + h <= this.y && x +w >= this.x &&  x <= width + this.x) {
			return true;
		} else {
			return false;
		}
	}
	public boolean getXCollision(int x, int y, int h, int w) {
		if(x+w>=this.x && x<this.x+width && y +h > this.y) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
