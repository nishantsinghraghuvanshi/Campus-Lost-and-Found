import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class Login {
    public static void main(String[] args) {

        Connection con = DatabaseUtility.getConnection();
        if (con != null) {
            System.out.println("Connection Successful");
        }

        JFrame frame = new JFrame("Campus Lost and Found  —  Login");
        frame.setSize(350, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username :");
        JTextField usernameInput = new JTextField();

        JLabel passwordLabel = new JLabel("Password :");
        JPasswordField passwordInput = new JPasswordField();

        JLabel blank = new JLabel();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);  panel.add(usernameInput);
        panel.add(passwordLabel);  panel.add(passwordInput);
        panel.add(blank);          panel.add(loginButton);

        frame.add(panel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
                    PreparedStatement statement = con.prepareStatement(query);
                    String user = usernameInput.getText();
                    String pass = new String(passwordInput.getPassword());
                    statement.setString(1, user);
                    statement.setString(2, pass);
                    ResultSet rs = statement.executeQuery();

                    if (rs.next()) {
                        String role = rs.getString("role");
                        frame.dispose(); // close the login window

                        if (role.equals("student")) {
                            int userId = rs.getInt("id");
                            new Student(user, userId);
                        } else if (role.equals("admin")) {
                            new Admin();  // open the Admin panel
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed. Check your credentials.");
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Connection error: " + exception.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}