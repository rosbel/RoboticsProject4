
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainWindow extends javax.swing.JFrame {
	JPanel panel;
	JLabel label;	  
	Vector<Robot> robots = new Vector<Robot>();
	Vector<Light> lights = new Vector<Light>();
    private keyEvent keyListener = new keyEvent();
    long timerInterval = 250;
    Timer timer = new Timer();
	
	  @Override
	  public void paint(Graphics g) {
		 super.paintComponents(g);
	     Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
		 for(int i=0; i< lights.size(); i++){
			 lights.elementAt(i).draw(g2);
		 }
		 for(int j=0; j< robots.size(); j++){
			 robots.elementAt(j).draw(g2);
		 }
	   }

    public MainWindow() {
        initComponents();
        Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
        Robot newrobot = new Robot(MouseInfo.getPointerInfo().getLocation().getX(),MouseInfo.getPointerInfo().getLocation().getY(), 6);
        robots.add(newrobot);
        Light light = new Light(100,100);
        light.draw(g2);
        lights.add(light);
        this.setTitle("");
    }
    
    private class mouseEvent 
	implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent evt) {
		double x;
		double y;
		int mouseX = evt.getX();
	    int mouseY = evt.getY();
	    int button = evt.getButton();
	    Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();

		
	    //Add robot if left click
	    if (button == 1)
	    {
	    	System.out.println("Left Click Detected!");

    		if(!MainWindow.this.xTextField.getText().equals("X") && !MainWindow.this.yTextField.getText().equals("Y"))
    		{
    			try
    			{
        			x = Integer.valueOf(MainWindow.this.xTextField.getText());
        			y = Integer.valueOf(MainWindow.this.yTextField.getText());
    			}
    			catch(NumberFormatException nfe)
    			{
    				x = mouseX;
    				y = mouseY;
    			}

    		}
    		else
    		{
    			x = mouseX;
    			y = mouseY;
    		}
    		Robot newrobot = new Robot(x,y,0);
    		
    		if(!MainWindow.this.k1.getText().equals("k1") && !MainWindow.this.k2.getText().equals("k2") && !MainWindow.this.k3.getText().equals("k3") && !MainWindow.this.k4.getText().equals("k4"))
    		{
    			try
    			{
	    			double k1 = Double.valueOf(MainWindow.this.k1.getText());
	    			double k2 = Double.valueOf(MainWindow.this.k2.getText());
	    			double k3 = Double.valueOf(MainWindow.this.k3.getText());
	    			double k4 = Double.valueOf(MainWindow.this.k4.getText());
	    			newrobot.setKvalues(k1, k2, k3, k4);
    			}catch(NumberFormatException nfe)
    			{
    				System.out.println("K Values are not numbers");
    			}
    		}
    		
    		newrobot.draw(g2);
    		
    		robots.add(newrobot);
	    }
	    //Add light source if right click
	    else if (button == 3) {
	    	System.out.println("Right Click Detected!");
	    	Light newlight = new Light(mouseX, mouseY);
			newlight.draw(g2);
			lights.add(newlight);
	    }
		//repaint();			
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	}
    
    MyTask task = new MyTask();
    class MyTask extends TimerTask {
        public void run() {
    		System.out.println("Timer - I'm pressing the button for you!");
    		Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
    		for(int i=0; i<robots.size(); i++)
    		{
    			robots.elementAt(i).update(lights);
    			robots.elementAt(i).draw(g2);
    		}
    		repaint();
        }
    }

    public static void main(String args[]) {
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    private javax.swing.JPanel paintCanvasPanel;
    private mouseEvent mouseListener;
    private javax.swing.JTextField k1;
    private javax.swing.JTextField k2;
    private javax.swing.JTextField k3;
    private javax.swing.JTextField k4;
    private javax.swing.JTextField xTextField;
    private javax.swing.JTextField yTextField;
    
    private void initComponents() {
        paintCanvasPanel = new javax.swing.JPanel();
        mouseListener = new mouseEvent();
        paintCanvasPanel.addMouseListener(mouseListener);
        
        k1 = new javax.swing.JTextField();
        k2 = new javax.swing.JTextField();
        k3 = new javax.swing.JTextField();
        k4 = new javax.swing.JTextField();
        xTextField = new javax.swing.JTextField();
        yTextField = new javax.swing.JTextField();
        
        k1.setText("k1");

        k2.setText("k2");

        k3.setText("k3");

        k4.setText("k4");

        xTextField.setText("X");

        yTextField.setText("Y");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));
        paintCanvasPanel.addKeyListener(keyListener);
        paintCanvasPanel.setBackground(new Color(155, 155, 155)); // background color
        paintCanvasPanel.setFocusable(true); //set focus for keyboard

       
        
        javax.swing.GroupLayout paintCanvasPanelLayout = new javax.swing.GroupLayout(paintCanvasPanel);
        paintCanvasPanel.setLayout(paintCanvasPanelLayout);
        paintCanvasPanelLayout.setHorizontalGroup(
            paintCanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        paintCanvasPanelLayout.setVerticalGroup(
            paintCanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintCanvasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(k1)
                            .addComponent(k2)
                            .addComponent(k3)
                            .addComponent(k4))
                        .addComponent(xTextField))
                    .addComponent(yTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintCanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(k1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(k2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(k3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(k4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }     
    
    private class keyEvent implements KeyListener, ActionListener
    {

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
	    	if (code == KeyEvent.VK_SPACE)
	    	{
	    		timer.scheduleAtFixedRate(task, 0, timerInterval);
		        /*Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
	    		for(int i=0; i<robots.size(); i++)
	    		{
	    			robots.elementAt(i).update(lights);
	    			robots.elementAt(i).draw(g2);
	    		}
	    		repaint();*/
	    		//System.out.println("MOVING");
	    	}
	    	else if(code == KeyEvent.VK_R)
	    	{
		        Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
	    		double k1 = Double.valueOf(MainWindow.this.k1.getText());
	    		double k2 = Double.valueOf(MainWindow.this.k2.getText());
	    		double k3 = Double.valueOf(MainWindow.this.k3.getText());
	    		double k4 = Double.valueOf(MainWindow.this.k4.getText());
	    		
	    		double x;
	    		double y;
	    		if(MainWindow.this.xTextField.getText().length() > 0 && MainWindow.this.yTextField.getText().length() > 0)
	    		{
	    			x = Integer.valueOf(MainWindow.this.xTextField.getText());
	    			y = Integer.valueOf(MainWindow.this.yTextField.getText());
	    		}
	    		else
	    		{
	    			x = MouseInfo.getPointerInfo().getLocation().getX();
	    			y = MouseInfo.getPointerInfo().getLocation().getY();
	    		}
	    		Robot newrobot = new Robot(x,y,0);
	    		
	    		if(MainWindow.this.k1.getText().length() > 0 && MainWindow.this.k2.getText().length() > 0 && MainWindow.this.k3.getText().length() > 0 && MainWindow.this.k4.getText().length() > 0)
	    			newrobot.setKvalues(k1, k2, k3, k4);
	    		
	    		newrobot.draw(g2);
	    		
	    		robots.add(newrobot);
	    	}
	    	else if(code == KeyEvent.VK_L)
	    	{
		        Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
				Light newlight = new Light((int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
				newlight.draw(g2);
				lights.add(newlight);
	    	}
		}

		@Override
		public void actionPerformed(ActionEvent e) {			
		}

		@Override
		public void keyTyped(KeyEvent e) {

			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    }
}
