import javax.swing.*;
import java.awt.*;
public class Login{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Campus Lost and Found");
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));

        JLabel role_selector = new JLabel();
        role_selector.setText("Select Your Role : ");
        String roles[] = {"Admin","Student"};
        JComboBox role = new JComboBox(roles);

        JLabel username = new JLabel();
        username.setText("Username : ");
        JTextField username_input = new JTextField();

        JLabel password = new JLabel();
        password.setText("Password : ");
        JPasswordField password_input = new JPasswordField();

        JLabel blank = new JLabel();
        blank.setText("       ");
        JButton login_button = new JButton();
        login_button.setText("Login");


        panel.add(role_selector);
        panel.add(role);
        panel.add(username);
        panel.add(username_input);
        panel.add(password);
        panel.add(password_input);
        panel.add(blank);
        panel.add(login_button);
        frame.add(panel);


        frame.setVisible(true);
    }
}