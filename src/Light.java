import java.awt.Color;
import java.awt.Graphics2D;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class Light {
	public int x;
	public int y;
	public int radius;
	public int intensity;
	Color color;

	Light(int X, int Y)
	{
		x = X;
		y = Y;
		radius = 13;
		color = Color.WHITE;
		intensity = 255;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.drawOval(this.x,this.y,this.radius,this.radius);
		g.fillOval(this.x,this.y,this.radius,this.radius);
	}
}
