import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
public class Login{
    public static void main(String[] args){

        Connection con = DatabaseUtility.getConnection();
        if(con != null){
          System.out.println("Connection Successful");
        }
        JFrame frame = new JFrame();
        frame.setTitle("Campus Lost and Found");
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,2));
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

        panel.add(username);
        panel.add(username_input);
        panel.add(password);
        panel.add(password_input);
        panel.add(blank);
        panel.add(login_button);
        frame.add(panel);
        login_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                   String query = "select * from Users where username = ? and password = ?";
                  PreparedStatement statement = con.prepareStatement(query);
                  String user = username_input.getText();
                  String pass = new String(password_input.getPassword());
                  statement.setString(1,user);
                  statement.setString(2,pass);
                  ResultSet rs = statement.executeQuery();
                  if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Logged In");
                  }
                  else{
                    JOptionPane.showMessageDialog(null,"Loggin Failed ");
                  }
                }
                catch(Exception exception){
                  JOptionPane.showMessageDialog(null,"Connection Failed ");
                }
            }
        });
        frame.setVisible(true);
    }
}
