package ProjectDAD;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JProgressBar;

public class SplashScreen extends JFrame {

	private JPanel contentpane;
	private static JProgressBar progressBar;
	private static JLabel label_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		int x;
		SplashScreen frame = new SplashScreen();
		frame.setVisible(true);
		try {
			for(x=0;x<=100;x++) {
				SplashScreen.progressBar.setValue(x);
				Thread.sleep(50);
				SplashScreen.label_1.setText(Integer.toString(x)+"%");	
				if(x==100) {
					Home frame1 = new Home();
					frame1.setVisible(true);
					frame.dispose();
					}
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
				}
		}
			
	/**
	 *create the frame.
	 */
	public SplashScreen() {
	setBounds(100,100,600,400);
	setUndecorated(true);
	contentpane = new JPanel();
	contentpane.setBackground(new Color(255, 182, 193));
	contentpane.setBorder(new EmptyBorder(5,5,5,5));
	setContentPane(contentpane);
	contentpane.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setBackground(new Color(255, 182, 193));
	ImageIcon icon = new ImageIcon(this.getClass().getResource("/loading.gif"));
	lblNewLabel.setIcon(icon);
	lblNewLabel.setBounds(10, 11, 580, 329);
	contentpane.add(lblNewLabel);
	
    progressBar = new JProgressBar();
	progressBar.setBackground(new Color(186, 85, 211));
	progressBar.setBounds(10, 367, 580, 22);
	contentpane.add(progressBar);
	
	label_1 = new JLabel("");
	label_1.setForeground(new Color(255, 255, 255));
	label_1.setBounds(299, 351, 46, 14);
	contentpane.add(label_1);
	
	
	}
}
