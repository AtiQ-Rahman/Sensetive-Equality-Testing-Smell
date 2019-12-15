package sensetive_Equality;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class SensetiveEquality {

  public static void main(String[] args) {

	

	  
	  ImagePanel panel = new ImagePanel(
        new ImageIcon(("res\\f1.png")).getImage());

    JFrame frame = new JFrame();
    //frame.setVisible(true);

	JButton btn = new JButton("JAVA PROJECT");
	btn.setFont(new Font("Tahoma", Font.BOLD, 14));
	btn.setBackground(new Color(255, 255, 51));
	btn.setBounds(425, 350, 145, 32);
	
	panel.add(btn);
	frame.getContentPane().add(panel);
	frame.pack();
	frame.setVisible(true);
    frame.setResizable(false);
	
	
	btn.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			
			JFileChooser j = new JFileChooser();

			j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int r = j.showOpenDialog(null);

			if (r == JFileChooser.APPROVE_OPTION)

			{

				 
	          
				String com = j.getSelectedFile().toString();
				//String c = "C:\\Users\\Atiq\\Desktop\\SmellDectetorTool";
				frame.setVisible(false);
				frame.dispose();
				
			
				new Processing(com);

			}

			
		
		}
		
	});
	
	
	
	
    
  }
}

	class ImagePanel extends JPanel {
	
	  private Image img;
	
	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }
	
	  public ImagePanel(Image img) {
	
		  this.img = img;
		    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
	  }
	
	  public void paintComponent(Graphics g) {
		  g.drawImage(img, 0, 0,690,400, this);
	  }
	
	}