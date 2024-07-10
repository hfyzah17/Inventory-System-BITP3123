package ProjectDAD;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Home extends JFrame {

	private JPanel contentPane;
	private JMenu fileMenu;
	private JMenuItem LoginUser;
	private JMenuItem RegisterItem;
	private JMenuBar menuBar;
	
	//image slider
	private JLabel pic;
	Timer ts;
	int x = 0;
	String[] list = {
			"image/inventory2.png",
			"image/inventory1.jpg",
			"image/inventory3.jpg"
	};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 620, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(new Color(255, 182, 193));
		setResizable(false);
	
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String s = "Welcome To Our Inventory Management System";
		MarqueePanel mp = new MarqueePanel(s, 70);
		mp.setBounds(10, 39, 580, 57);
		mp.setBackground(new Color(255, 182, 193));
		contentPane.add(mp);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(10, 11, 580, 22);
		contentPane.add(menuBar);
		
		fileMenu = new JMenu("USER MENU");
		LoginUser = new JMenuItem("Login");
		
		RegisterItem = new JMenuItem("Register");
		fileMenu.add(LoginUser);
		fileMenu.add(RegisterItem);
		menuBar.add(fileMenu);
		contentPane.add(menuBar);
		mp.start();
		
		// Add action listeners to menu items
		LoginUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle login action
            	LoginUser frame1 = new LoginUser();
                frame1.setVisible(true); // Show the login frame
                dispose();
            }
        });

        RegisterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle register action
                RegisterUser frame2 = new RegisterUser();
                frame2.setVisible(true);
                dispose();
            }
        });

		
		//imageslider
		pic = new JLabel();
		pic.setBounds(10, 115, 580, 208);
		SetImageSize(2);
		
		ts = new Timer(1300, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SetImageSize(x);
				x += 1;
				if(x >= list.length)
					x = 0;
				
			}
		});
		
		getContentPane().add(pic);
		ts.start();
		getContentPane().setLayout(null);
		
		
	}
	
	public void SetImageSize(int i) {
		ImageIcon icon = new ImageIcon(list[i]);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);
		pic.setIcon(newImc);
	}
}

class MarqueePanel extends JPanel implements ActionListener {
	private static final int Rate = 12;
	final Timer t;
	final JLabel l = new JLabel();
	final String s;
	final int n;
	int index;

	public MarqueePanel(String s, int n) {
		if (s == null || n < 1) {
			throw new IllegalArgumentException("Null String or n < 1");
		}

		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			sb.append(' ');
		}

		this.s = sb + s + sb;
		this.n = n;
		l.setFont(new Font("Serif", Font.BOLD, 36));
		l.setText(sb.toString());
		this.add(l);
		this.t = new Timer(1000 / Rate, this);
	}

	public void start() {
		t.start();
	}

	public void stop() {
		t.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		index++;
		if (index > s.length() - n) {
			index = 0;
		}
		l.setText(s.substring(index, index + n));
	}
}