import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

public class Sensor {
	double x;
	double y;
	double reading;
	double orientation;
	
	Sensor(double initx, double inity, double initorientation)
	{
		x=initx;
		y=inity;
		reading = 0;
		orientation = initorientation;
	}
	
	void draw(Graphics2D g2){
		g2.setColor(Color.black);
		g2.drawArc((int)x, (int)y, 20, 20, (int) orientation, 180);
		
	}
	
	void setCoord(double newx, double newy, double neworientation)
	{
		x = newx;
		y = newy;
		orientation = neworientation;
	}
	
	void calcReading(Vector<Light> lights)
	{
		double intensitysum = 0;
		for(int i=0; i< lights.size(); i++)
		{
			double temp = 1/(Math.sqrt(Math.pow((lights.elementAt(i).x - x),2) + Math.pow((lights.elementAt(i).y - y),2)));
			intensitysum += temp;
		}
		reading = intensitysum;
	}
}
