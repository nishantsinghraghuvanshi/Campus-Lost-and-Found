import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(MainFrame frame) {

        setLayout(new GridBagLayout()); // centers content

        JPanel box = new JPanel();
        box.setLayout(new GridLayout(3,2,8,8));
        box.setPreferredSize(new Dimension(250,120)); // fixed small size

        JTextField userField = new JTextField(10);
        JPasswordField passField = new JPasswordField(10);
        JButton loginBtn = new JButton("Login");

        box.add(new JLabel("Username:"));
        box.add(userField);

        box.add(new JLabel("Password:"));
        box.add(passField);

        box.add(new JLabel(""));
        box.add(loginBtn);

        add(box);

        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            if(username.equals("student") && password.equals("123")) {
                frame.showStudent();
            }
            else if(username.equals("admin") && password.equals("123")) {
                frame.showAdmin();
            }
            else {
                JOptionPane.showMessageDialog(null,"Invalid Login");
            }
        });
    }
}