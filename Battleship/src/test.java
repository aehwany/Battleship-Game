//import java.applet.Applet;
import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.awt.image.BufferedImage;
import java.applet.Applet;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*; 

public class test extends Applet{
	public void init() 
	{
//JFrame frame=new JFrame("GAME");
//frame.setSize(600,600);
//frame.setVisible(true);
//frame.setBackground(Color.RED);
JPanel main=new JPanel(new FlowLayout());
JLabel label=new JLabel("AHmed");
label.setBounds(0,0,500,500);
label.setPreferredSize(new Dimension(500,500));
main.add(label);


BufferedImage img_ver = null;
BufferedImage img_hor = null;
try {
    img_ver = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_ver.png"));
    img_hor = ImageIO.read(new File("/Users/ahmedelehwany/Desktop/Battleship/ship_hor.png"));
    
} catch (IOException e) {
	System.out.println("failed");
}
JLabel ship_ver=new JLabel("Ahmed");
JLabel ship_hor=new JLabel("Husseinnnnnnnnnnnnnnnnnnnnnnnnn");
//ship_hor.setSize(250,200);
//JLabel ship_ver = new JLabel(new ImageIcon(img_ver));
//JLabel ship_hor = new JLabel(new ImageIcon(img_hor));
//ship_hor.setPreferredSize(new Dimension(200,150));
//ship_hor.setSize(100,50);
//ship_hor.setBounds(0, 0, 50, 10);

//main.add(ship_hor, BorderLayout.LINE_START);
add(main);
//frame.add(myboard);
//frame.getContentPane().add(myboard);
//frame.pack();
	}

}



	


