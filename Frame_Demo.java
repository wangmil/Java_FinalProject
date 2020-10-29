import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



class MyCustomPanel extends JPanel implements ActionListener, KeyListener{
   private Cat obj;
   private Background background;
   private Obstacle obstacle;
   private Timer t;
   private boolean jump = false;
   private boolean dead = false;
   private int surface = 0;
   private boolean surfaceTrue = false;
   
   public MyCustomPanel(){    // constructor- run once when you start the program
	   obj = new Cat();
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
		if (obstacle.getYCollision(obj.x,obj.y,obj.h,obj.w)) {
   	 		surface = obstacle.y;
   	 		surfaceTrue = true;
    	} else {
    		surfaceTrue = false;
    	}
    	if(jump == true) {
		  	obj.jump();
		  	if(surfaceTrue == true) {
		  		if(obj.y >= obstacle.y) { //use this to stabilize landing - just set this to obstacle.top or something and make the bottom a huge obstacle
				  	obj.grounded = true;
				  	obj.y = obstacle.y;
				  	obj.acceleration = 1;
				  	jump = false;
			  	}
		  	} else if(obj.y >= 425) { //use this to stabilize landing - just set this to obstacle.top or something and make the bottom a huge obstacle
			  	obj.grounded = true;
			  	obj.y = 425;
			  	obj.acceleration = 1;
			  	jump = false;
		  	}
	  	}
    	
    	if (obstacle.getXCollision(obj.x,obj.y,obj.h,obj.w) && surfaceTrue == false) {
   	 		dead = true;
    	}
	   repaint();  // call paintComponent method
	}
	}// end of if it is a timer
  }// end of actionPerformed


  public void keyPressed(KeyEvent ke) {
	  if(ke.getKeyCode()==38) {  //right arrow is pressed
			jump = true;
			 
		 }
	
   }
  
  public void keyReleased(KeyEvent ke) {}
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



// test