import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Vector;


public class Robot {
	double x;
	double y;
	double orientation;
	Wheel leftwheel;
	Wheel rightwheel;
	Sensor leftsensor;
	Sensor rightsensor;
    private Path2D path;
    private AffineTransform rotation;
    double k11, k12, k21, k22;
    
	
	Robot(double initx, double inity, double initorientation){
		x = new Double(initx);
		y = new Double(inity);
        path = new Path2D.Double();
        rotation = new AffineTransform();
		orientation = new Double(initorientation);
		
		//X's and y's for these will be changed in draw so they don't matter right here
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
		   
		   //Then draw the sensors:
		   leftsensor.setCoord(x, y, (orientation * 180/Math.PI) + 180);
		   leftsensor.draw(g2);
		   rightsensor.setCoord(x, y, (orientation * 180/Math.PI) + 180);
		   rightsensor.draw(g2);
		   
		   //Then draw the wheels:
		   
		   
	}
	
	void update(Vector<Light> lights){
		//Take in readings from sensors, calculate movement, update x and y of robot
		leftsensor.calcReading(lights);
		rightsensor.calcReading(lights);
		double leftwheelmovement = k11 * leftsensor.reading + k12 * rightsensor.reading;
		double rightwheelmovement = k21 * rightsensor.reading + k22 * leftsensor.reading;
		
		//b is distance between wheels
		int b = 5; int t=1;
		double movementsum = (rightwheelmovement + leftwheelmovement);
		double movementdiff = (rightwheelmovement - leftwheelmovement);
		double newx = x + ((b * movementsum) / (2 * movementdiff)) * (Math.sin(((t * movementdiff) / b) + orientation) - Math.sin(orientation));
		double newy = y - ((b * movementsum) / (2 * movementdiff)) * (Math.cos(((t * movementdiff) / b) + orientation) - Math.cos(orientation));
		double neworientation = (movementdiff / b) + orientation;
		
		x = newx;
		y = newy;
		orientation = neworientation;
		
		
	}
}
