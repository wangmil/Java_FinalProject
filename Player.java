import java.awt.*;
import javax.swing.*;

public class Player {
	 int x,y,w,h;
	 double jumpSpeed = 120,gravity = 3,acceleration = 1, jumpHeight = 100;
	 boolean grounded = true, jump = false, spaceship = false, die = false;;
	 ImageIcon player=null;
	 public Player() {
		 
		 x=425;
		 y=425;
		 w=50;
		 h=50;
	 }
	 public void myDraw(Graphics g) {
		 if(die == true) {
			 player= new ImageIcon("bakaretsu.png");
		 }else if(spaceship == true) {
			 player= new ImageIcon("spaceship.png");
		 } else {
			 player= new ImageIcon("geoDash.png");
		 }
		 g.drawImage(player.getImage(), x, y, w,h,null);
	 }
	 public void jump() {
		 if(grounded == true) {
			 acceleration +=0.4;
			 this.y-=jumpSpeed/acceleration;
			 if(jumpSpeed/acceleration < 15) {
				 grounded = false;
				 jump = false;
			 }
		 }
	 }


}
