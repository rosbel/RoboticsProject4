import java.awt.Color;
import java.awt.Graphics2D;


public class Light {
	double x;
	double y;
	
	Light(double initx, double inity)
	{
		x = initx;
		y = inity;
	}
	
	void draw(Graphics2D g2)
	{
		g2.drawOval((int)x, (int)y, 20, 20);
		g2.setColor(Color.yellow);
	    g2.fillOval((int)x, (int)y, 20, 20);

	}
	
}
