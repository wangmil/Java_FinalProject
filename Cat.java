import java.awt.*;
import javax.swing.*;

public class Cat {
	 int x,y,w,h;
	 double jumpSpeed = 50,gravity = 3,acceleration = 1;
	 boolean grounded = true;
	 ImageIcon catImg=null;
	 public Cat() {
		 catImg= new ImageIcon("geoDash.png");
		 x=425;
		 y=425;
		 w=50;
		 h=50;
	 }
	 public void myDraw(Graphics g) {
		 g.drawImage(catImg.getImage(), x, y, w,h,null);
		 g.drawRect(x, y, w,h);
	 }
	 public void jump() {
		 if(grounded == true) {
			 acceleration +=0.4;
			 this.y-=jumpSpeed/acceleration;
			 if(jumpSpeed/acceleration < 15) {
				 acceleration = 1;
				 grounded = false;
			 }
		 } else {


			acceleration += 1;
			y += gravity*acceleration;
		 }
	 }
	 public void die() {
		 w = 500;
		 h = 500;
	 }


}
