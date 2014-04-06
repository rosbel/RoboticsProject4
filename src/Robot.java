import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;


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
	
	Robot(double initx, double inity, double initorientation){
		x = new Double(initx);
		y = new Double(inity);
        path = new Path2D.Double();
        rotation = new AffineTransform();
		orientation = new Double(initorientation);
	}
	
	void draw(Graphics2D g2){
		   rotation.setToTranslation(x, y);
		   rotation.rotate(orientation, 32, 32);
		   Rectangle rect1 = new Rectangle(0, 0, 64, 64);
		   path = new Path2D.Double(rect1, rotation);
		   g2.fill(path);
	}
}
