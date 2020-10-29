import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



class MyCustomPanel extends JPanel implements ActionListener, KeyListener{
   private Player obj;
   private Background background;
   private Obstacle obstacle;
   private Timer t;
   private boolean dead = false;
   private int surface = 0;
   private boolean surfaceTrue = false;
   
   public MyCustomPanel(){    // constructor- run once when you start the program
	   obj = new Player();
	   background = new Background();
	   obstacle = new Obstacle();
       this.setLayout(new FlowLayout());
       addKeyListener(this);  // addKeyListener to a panel
       setFocusable(true);  // set the panel focusable
       this.requestFocusInWindow();  // request focus for the panel
       t = new Timer(60, this);
       t.start();
   }// end of constructor
   
   //must have this method in order to paint
   public void paintComponent(Graphics g){
	   super.paintComponent(g);
	   background.myDraw(g);
	   obstacle.myDraw(g);
	   obj.myDraw(g);   // draw cat and a hit box
   }

public void actionPerformed(ActionEvent ae) {
	if(ae.getSource()==t) {// if it is a timer
	if(dead == false) {
		
		obstacle.myMove();
		background.moving();
		if(obstacle.x < 0) {
			obstacle.x = 900;
		}
		if(background.x < -900) {
			background.x = 0;
		}
		
    	if(obj.jump == true) {
		  	obj.jump();
	  	}
    	if(obj.jump == false) {
			obj.acceleration += 1;
			obj.y += obj.gravity*obj.acceleration;
		}
		if(surfaceTrue == true) {
			if(obj.y + obj.h >= obstacle.y) { //use this to stabilize landing - just set this to obstacle.top or something and make the bottom a huge obstacle
				obj.grounded = true;
				obj.y = obstacle.y - obj.h;
				obj.acceleration = 1;
			}
		} else if(obj.y >= 425) { //use this to stabilize landing - just set this to obstacle.top or something and make the bottom a huge obstacle
		  	obj.grounded = true;
		  	obj.y = 425;
		  	obj.acceleration = 1;
		  	obj.jump = false;
	  	}
    	if (obstacle.getYCollision(obj.x,obj.y,obj.h,obj.w)) {
   	 		surface = obstacle.y;
   	 		surfaceTrue = true;
    	} else {
    		surfaceTrue = false;
    	}
    	if (obstacle.getXCollision(obj.x,obj.y,obj.h,obj.w) && surfaceTrue == false) {
   	 		dead = true;
   	 		obj.die();
    	}
    	System.out.println(obstacle.y + "" + (obstacle.y+obj.h) + (obj.y+obj.h));
	   repaint();  // call paintComponent method
	}
	}// end of if it is a timer
  }// end of actionPerformed


  public void keyPressed(KeyEvent ke) {
	  if(ke.getKeyCode()==38) {  //up arrow is pressed
		  obj.jump = true;
			 
		 }
	
   }
  
  public void keyReleased(KeyEvent ke) {
  }
  public void keyTyped(KeyEvent ke) {}

}//end of class

public class Frame_Demo{

	  public static void main(String[] args) {
	    JFrame f = new JFrame("JFrame example");//java JFrame object
	   
	    Container cont = f.getContentPane();  // get container - top of the frame
		cont.setLayout(new BorderLayout());  // set Layout to Border 
		
		MyCustomPanel mcp= new MyCustomPanel();  // create an object of our game panel
		cont.add(mcp, BorderLayout.CENTER ); // add this game panel to the center of the frame
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // make frame closed when x button is pressed
		 f.setVisible(true);     // make the frame visible
		 f.setSize(900, 550);  // set the size of the frame
		 
		}//end of main
	}//end of class



