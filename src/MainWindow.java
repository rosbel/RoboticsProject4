
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainWindow extends javax.swing.JFrame {
	JPanel panel;
	JLabel label;	  
	Vector<Robot> robots = new Vector<Robot>();
	Vector<Light> lights = new Vector<Light>();
	
	
	  @Override
	  public void paint(Graphics g) {
		 super.paintComponents(g);
	        Robot newrobot = new Robot(200,250, 2.5);
	        Graphics2D g2 = (Graphics2D) paintCanvasPanel.getGraphics();
	        newrobot.draw(g2);
	        
	  }

    public MainWindow() {
        initComponents();
        this.setTitle("");
    }
    
    private class mouseEvent 
    		implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent evt) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = screenSize.height;
			int mouseX = evt.getX();
	        int mouseY = evt.getY();
	    	repaint();			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
    
    private void initComponents() {
        paintCanvasPanel = new javax.swing.JPanel();
        mouseListener = new mouseEvent();
        paintCanvasPanel.addMouseMotionListener(mouseListener);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        paintCanvasPanel.setBackground(new Color(255, 255, 255)); // background color
        paintCanvasPanel.setFocusable(true); //set focus for keyboard
        
        javax.swing.GroupLayout paintCanvasPanelLayout = new javax.swing.GroupLayout(paintCanvasPanel);
        paintCanvasPanel.setLayout(paintCanvasPanelLayout);
        paintCanvasPanelLayout.setHorizontalGroup(
            paintCanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );
        paintCanvasPanelLayout.setVerticalGroup(
            paintCanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintCanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintCanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );

        pack();
    }                      
}
