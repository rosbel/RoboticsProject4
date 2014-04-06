
import java.awt.Color;
import java.awt.Graphics2D;

public class Light {
	public int x;
	public int y;
	public int radius;
	Color color;
	
	Light(int X, int Y)
	{
		x = X;
		y = Y;
		radius = 10;
		color = Color.red;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.drawOval(this.x,this.y,this.radius,this.radius);
		g.fillOval(this.x,this.y,this.radius,this.radius);
	}
}
