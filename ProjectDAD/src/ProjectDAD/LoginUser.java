package ProjectDAD;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginUser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private Socket socket; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginUser frame = new LoginUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 600, 350);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setBounds(246, 11, 106, 36);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Email : ");
        lblNewLabel_1.setBounds(102, 98, 77, 17);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Password : ");
        lblNewLabel_2.setBounds(102, 192, 77, 17);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(294, 95, 216, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(294, 189, 216, 26);
        contentPane.add(passwordField);
        passwordField.setColumns(10);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = new String(passwordField.getPassword());
               
                // Validate email and password
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginUser.this, "Email cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(LoginUser.this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginUser.this, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidPassword(password)) {
                    JOptionPane.showMessageDialog(LoginUser.this, "Password must be at least 6 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                try {
                    socket = new Socket("localhost", 8081);
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF(email);
                    out.writeUTF(password);

                    new Thread(new ReceiverLogin(socket, LoginUser.this)).start();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginUser.this, "Failed to connect to server: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        btnLogin.setBounds(102, 251, 125, 36);
        contentPane.add(btnLogin);

        JButton btnSignUp = new JButton("SIGN UP");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterUser signUpFrame = new RegisterUser();
                signUpFrame.setVisible(true);
                LoginUser.this.dispose();
            }
        });
        btnSignUp.setBounds(385, 251, 125, 36);
        contentPane.add(btnSignUp);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }
  
 // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }
    
 // Helper method to validate password
    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

}
