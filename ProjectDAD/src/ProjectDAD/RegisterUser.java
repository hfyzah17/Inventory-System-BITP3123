package ProjectDAD;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class RegisterUser extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;
    private JTextField textField_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterUser frame = new RegisterUser();
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
    public RegisterUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 600, 350);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("New Register User");
        lblNewLabel.setBounds(169, 11, 254, 36);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Fullname :");
        lblNewLabel_1.setBounds(10, 98, 77, 17);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Email :");
        lblNewLabel_2.setBounds(10, 150, 46, 14);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Mobile Number :");
        lblNewLabel_3.setBounds(280, 95, 112, 22);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Password :");
        lblNewLabel_4.setBounds(280, 149, 71, 17);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_4);

        textField = new JTextField();
        textField.setBounds(81, 98, 175, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(399, 98, 175, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("REGISTER");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // action for register
                String name = textField.getText().trim();
                String phone = textField_1.getText().trim();
                String email = textField_2.getText().trim();
                String password = new String(passwordField.getText()).trim();
                
                // Validate required
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterUser.this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(RegisterUser.this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterUser.this, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (phone.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterUser.this, "Phone Number cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!isValidPassword(password)) {
                    JOptionPane.showMessageDialog(RegisterUser.this, "Password must be at least 6 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (!isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(RegisterUser.this, "Phone must be 10 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    URL url;
                    String urlString = "http://localhost/xampp/userRegister.php"
                            + "?Username=" + name
                            + "&Phone=" + phone
                            + "&Email=" + email
                            + "&Password=" + password;
                    url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    // Check the response code
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Successfully registered
                        JOptionPane.showMessageDialog(RegisterUser.this, "http://localhost/xampp/userRegister.php");
                        JOptionPane.showMessageDialog(RegisterUser.this, "User registered successfully !");
                        Home frame1 = new Home();
    					frame1.setVisible(true);
    					dispose();
                    } else {
                        // Registration failed
                        System.out.println("Failed to register user. Response code: " + responseCode);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace(); // Print the exception to standard error
                    // You can also handle the error, e.g., by showing a message to the user
                }
            }
        });
        btnNewButton.setBounds(231, 213, 125, 36);
        contentPane.add(btnNewButton);

        passwordField = new JPasswordField();
        passwordField.setBounds(399, 148, 175, 22);
        contentPane.add(passwordField);

        textField_2 = new JTextField();
        textField_2.setBounds(81, 149, 175, 21);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        
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
    
    private boolean isValidPhone(String phone) {
        return phone != null && phone.length() >= 10;
    }
}