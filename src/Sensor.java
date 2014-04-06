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
			double temp = Math.round(Math.sqrt(((int)lights.elementAt(i).x - (int)x)^2 + ((int)lights.elementAt(i).y - (int)y)^2));
			intensitysum += temp;
		}
		reading = intensitysum;
		System.out.println(reading);
	}
}
