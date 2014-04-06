import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class Robot {
	double x;
	double y;
	double orientation;
	Wheel leftwheel;
	Wheel rightwheel;
	Sensor leftsensor;
	Sensor rightsensor;
	
	Robot(double initx, double inity, double initorientation){
		x = new Double(initx);
		y = new Double(inity);
		orientation = new Double(initorientation);
	}
	
	void draw(Graphics2D g2){
	       g2.setColor(Color.black);
	       g2.setStroke(new BasicStroke(5));
	       g2.drawRect((int)x, (int)y, 100, 200);
	}
}
