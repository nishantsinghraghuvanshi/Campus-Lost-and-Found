import javax.swing.*;

public class MainFrame extends JFrame {

    LoginPanel loginPanel;
    StudentPanel studentPanel;
    AdminPanel adminPanel;

    public MainFrame() {

        setTitle("Lost & Found");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginPanel = new LoginPanel(this);
        studentPanel = new StudentPanel(this);
        adminPanel = new AdminPanel(this);

        add(loginPanel);

        setVisible(true);
    }

    public void showStudent() {
        getContentPane().removeAll();
        add(studentPanel);
        revalidate();
        repaint();
    }
    public void showAdmin() {
        getContentPane().removeAll();
        add(adminPanel);
        revalidate();
        repaint();
    }

    public void showLogin() {
        getContentPane().removeAll();
        add(loginPanel);
        revalidate();
        repaint();
    }
}