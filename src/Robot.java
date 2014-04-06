import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.Vector;

public class Robot {
	double x;
	double y;
	double orientation;
	//Might not even need wheels. See update function.
	Wheel leftwheel;
	Wheel rightwheel;
	Sensor leftsensor;
	Sensor rightsensor;
    private Path2D path;
    private AffineTransform rotation;
    double k11 =1000 , k12 = 1, k21 = 1000, k22 = 1;
    
	
	Robot(double initx, double inity, double initorientation){
		x = new Double(initx);
		y = new Double(inity);
        path = new Path2D.Double();
        rotation = new AffineTransform();
		orientation = new Double(initorientation);
		
		//x's and y's for these will be changed in draw so they don't matter right here
		leftsensor = new Sensor(x,y,orientation);
		rightsensor = new Sensor(x,y,orientation);
	}
	
	void draw(Graphics2D g2){
			//First draw the robot:
		   rotation.setToTranslation(x, y);
		   rotation.rotate(orientation, 32, 32);
		   Rectangle rect1 = new Rectangle(0, 0, 64, 64);
		   path = new Path2D.Double(rect1, rotation);
		   g2.fill(path);

		   PathIterator bounderiter = path.getPathIterator(null);
		   float[] coords1 = new float[2];
		   float[] coords2 = new float[2];
		   bounderiter.next();
		   bounderiter.currentSegment(coords1);
		   bounderiter.next();
		   bounderiter.currentSegment(coords2);
		   //Then draw the sensors !NEED TO CALCULATE SENSOR POSITIONS!
		   leftsensor.setCoord(coords1[0], coords1[1], (orientation * (180/Math.PI)));
		   leftsensor.draw(g2);
		   rightsensor.setCoord(coords2[0], coords2[1], (orientation * (180/Math.PI)));
		   rightsensor.draw(g2);
		   
		   //Then draw the wheels?
		   
	}
	
	void update(Vector<Light> lights){
		//Take in readings from sensors
		leftsensor.calcReading(lights);
		rightsensor.calcReading(lights);
		
		//Calculate how each wheel should turn
		double leftwheelmovement = k11 * leftsensor.reading + k12 * rightsensor.reading;
		double rightwheelmovement = k21 * rightsensor.reading + k22 * leftsensor.reading;
		
		System.out.println("Right wheel movement: " + rightwheelmovement);
		System.out.println("Left wheel movement: " + leftwheelmovement);
		System.out.println("Right sensor reading:  " + rightsensor.reading);
		System.out.println("Left sensor reading: " + leftsensor.reading);
		
		//b is distance between wheels, t is time but set to 1 because time step should be the same each time
		int b = 5; int t=1;
		
		//Calculate the new x, new y, and orientation of the robot
		//Equations from http://rossum.sourceforge.net/papers/DiffSteer/
		double movementsum = (rightwheelmovement + leftwheelmovement);
		double movementdiff = (rightwheelmovement - leftwheelmovement);
		//if(movementdiff == 0) movementdiff = 1;
		double newx = Math.round(x + (((b * movementsum) / (2 * movementdiff)) * (Math.sin((movementdiff / b) + orientation) - Math.sin(orientation))));
		double newy = y - ((b * movementsum) / (2 * movementdiff)) * (Math.cos((movementdiff / b) + orientation) - Math.cos(orientation));
		double neworientation = (movementdiff / b) + orientation;
		
		//Set new values
		x = newx;
		y = newy;
		orientation = neworientation;
	}
	
	void setKvalues(double newk11, double newk12, double newk21, double newk22)
	{
		k11 = newk11;
		k12 = newk12;
		k21 = newk21;
		k22 = newk22;
	}
}
	
