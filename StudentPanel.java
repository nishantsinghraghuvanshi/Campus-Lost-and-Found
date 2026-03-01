import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {

    public StudentPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome Student", SwingConstants.CENTER);
        JButton logoutBtn = new JButton("Logout");
        add(label, BorderLayout.CENTER);
        add(logoutBtn, BorderLayout.SOUTH);
        logoutBtn.addActionListener(e -> {
            frame.showLogin();
        });
    }
}