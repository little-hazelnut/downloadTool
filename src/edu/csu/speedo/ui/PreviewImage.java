package edu.csu.speedo.ui; 
import java.awt.Dimension;
import java.awt.Image; 
import java.awt.Toolkit;
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
import javax.swing.ImageIcon; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.SwingUtilities; 
/** 
* 加载显示图象,需要JDK1.5或以上 
*/ 
public class PreviewImage extends JFrame { 

//	static String imageName = "";
	
	public PreviewImage(String imageFile) { 
//		this.imageName =imageFile;
		Image image = null; 
		try { 
			image = ImageIO.read(new File(imageFile)); 
			} catch (IOException ex) { 
		} 
		JLabel label = new JLabel(new ImageIcon(image));
		int width =500;
		int height = 500;
		this.setSize(width, height);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		add(label); 
		this.setTitle("图片预览");
//		setDefaultCloseOperation(EXIT_ON_CLOSE); 
//		pack(); 
				
	} 
} 
