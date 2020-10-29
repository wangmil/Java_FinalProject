import java.awt.*;
import javax.swing.*;


public class Background extends Rectangle{
	int x,y,w,h,speed = 5;
	 ImageIcon background=null;
	 public Background() {
		 background = new ImageIcon("background.jpg");
		 x=0;
		 y=0;
		 w=900;
		 h=550;
	 }
	 public void myDraw(Graphics g) {
		 g.drawImage(background.getImage(), x, y, w,h,null);
		 g.drawImage(background.getImage(), x+900, y, w,h,null);
	 }
	 public void moving() {
		 x-=speed;
	 }
	
}
