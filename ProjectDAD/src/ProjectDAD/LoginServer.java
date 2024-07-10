package ProjectDAD;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginServer {

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            System.out.println("Server started");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    String email = in.readUTF();
                    String password = in.readUTF();

                    System.out.println("Received email: " + email);
                    System.out.println("Received password: " + password);

                    // Always respond with "Login Successful"
                    out.writeUTF("Login Successful");
                    out.writeUTF(email);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginServer().startServer();
    }
}
