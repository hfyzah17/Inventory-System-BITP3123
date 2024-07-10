package ProjectDAD;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReceiverLogin implements Runnable {

    private Socket socket;
    private JFrame loginFrame;

    public ReceiverLogin(Socket socket, JFrame loginFrame) {
        this.socket = socket;
        this.loginFrame = loginFrame;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
            String response = in.readUTF();
            String email = in.readUTF();

            if ("Login Successful".equals(response)) {
                // Show email entered and success message
                JOptionPane.showMessageDialog(loginFrame, "User login email: " + email);
                JOptionPane.showMessageDialog(loginFrame, "Login Successful!");

                // Redirect to Home page
                InventoryView homeFrame = new InventoryView();
                homeFrame.setVisible(true);

                // Dispose login frame
                loginFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Login Failed. Please try again.");
            }
        } catch (IOException ex) {
            System.err.println("Error reading from server: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
