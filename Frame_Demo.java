import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



class MyCustomPanel extends JPanel implements ActionListener, KeyListener{
	
   private Player obj;
   private Background background;
   private Obstacle[] obstacles;
   private Timer t;
   private boolean fly = false;
   private boolean dead = false;
   private boolean doubleJump = false;
   private double jumpTimer = 0;
   private int portalCount = 0;
   
   public MyCustomPanel(){    // constructor- run once when you start the program
	   obj = new Player();
	   obstacles = new Obstacle[2];
	   background = new Background();
	   obstacles[0] = new Obstacle(900,385,650,50);
	   obstacles[1] = new Obstacle(1800,345,650,50);
	   
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
	   g.setColor(new Color(255,255,255));
	   g.drawRect(-5,474,905,300);
	   for(Obstacle obstacle: obstacles) {
		   obstacle.myDraw(g);
	   }
	   g.setColor(new Color(0,0,0,150));
	   obj.myDraw(g);
	   g.fillRect(-5,475,905,300);
   }

public void actionPerformed(ActionEvent ae) {
	if(ae.getSource()==t) {// if it is a timer
	if(dead == false) {
		for(Obstacle obstacle: obstacles) { 
			if(obstacle.speed < 80) {
				obstacle.speed += 0.05;
				
			}
		}
		if(background.speed < 50) {
			background.speed += 0.1;
			
		}
		
		background.moving();
		for(Obstacle obstacle: obstacles) {

			obstacle.myMove();
			if(obstacle.x <= -650) {
				obstacle.x = 900;

				if(obj.spaceship == true) {
					obstacle.type = 0;
					portalCount +=1;
					if(portalCount == 2) {
						obstacle.type = 3;
						obstacle.y = 0;
						obstacle.height = 600;
						obstacle.width = 100;
						for(Obstacle reset: obstacles) {
							reset.speed = 30;
						}
						background.speed = 5;
						portalCount = 0;
					}
				} else {
					obj.gravity = 3;
					if((int) (Math.random()*99) < 50) {
						obstacle.type = 0;
					} else if((int) (Math.random()*99) < 90) {
						obstacle.type = 1;
					} else if((int) (Math.random()*99) < 100) {
						obstacle.type = 2;
					}
				}
				if(obstacle.type == 0){
					if(obj.spaceship == true) {
						obstacle.y = (int) (Math.random() * 400);
						obstacle.width = (int) (Math.random() * 500);
						obstacle.height = 100;
					} else {
					obstacle.width = 650;
					obstacle.height = 50;
					if(obj.y > 385) {
						obstacle.y = 385;
					} else {
					obstacle.y = obj.y;
					}
					
					}
				} else if(obstacle.type == 1) {
					obstacle.y = 430;
					obstacle.height = 50;
					obstacle.width = 50;
				
				} else if(obstacle.type == 2) {
					obstacle.y = 0;
					obstacle.height = 600;
					obstacle.width = 100;
				}
		}
		}
		if(background.x < -900) {
			background.x = 0;
		}
		if(fly == true) {
    		obj.acceleration = 1;
    		obj.grounded = true;
    		obj.jumpSpeed = 50;
		  	obj.jump();
		} else {
    		obj.jumpSpeed = 120;
		}
    	if(obj.jump == true) {
		  	obj.jump();
	  	}
    	if(doubleJump == true) {
    		obj.acceleration = 1;
    		obj.grounded = true;
		  	obj.jump();
		  	jumpTimer+=1;
		  	if(jumpTimer >1) {
			  	doubleJump = false;
			  	jumpTimer = 60;
		  	}
	  	}
    	if(jumpTimer >= 0) {
    		jumpTimer -= 0.1;
    	}
    	obj.acceleration += 1;
		obj.y += obj.gravity*obj.acceleration;
		for(Obstacle obstacle: obstacles) {
		if(obstacle.surfaceTrue == true) {

			
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
		if(obj.y <= 0) { //use this to stabilize landing - just set this to obstacle.top or something and make the bottom a huge obstacle
		  	obj.y = 1;
	  	}
		if (obstacle.getYCollision(obj.x,obj.y,obj.h,obj.w) && obstacle.type == 0) {
   	 		obstacle.surfaceTrue = true;
		} else {
			obstacle.surfaceTrue = false;
		}
		
	    if (obstacle.getXCollision(obj.x,obj.y,obj.h,obj.w) && obstacle.surfaceTrue == false) {
	    	if(obstacle.type == 3) {
	 			obj.spaceship = false;
				fly = false;
	 		} else if(obstacle.type == 2) {
	 			obj.spaceship = true;
	 		} else {
	 			dead = true;
		   	 	obj.die = true;
	 		}
	   	 	
	   	}
		
    	System.out.println(portalCount);
		}
	   repaint();  // call paintComponent method
	}
	}// end of if it is a timer
  }// end of actionPerformed


  public void keyPressed(KeyEvent ke) {
	  if(ke.getKeyCode()==38 && obj.spaceship == false) {  //up arrow is pressed
		  obj.jump = true;
			 
	  }
	  if(ke.getKeyCode()==81 && jumpTimer <= 0) {  //up arrow is pressed
		  doubleJump = true;
			 
		 }

	  if(ke.getKeyCode()==69 && obj.spaceship == true) {  //up arrow is pressed
		  fly = true;
			 
		 }
	
   }
  
  public void keyReleased(KeyEvent ke) {
	  if(ke.getKeyCode()==69 && obj.spaceship == true) {  //up arrow is pressed
		  fly = false;
			 
		 }
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



