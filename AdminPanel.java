import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {

    public AdminPanel(MainFrame frame) {

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Welcome Admin (Full Access)", SwingConstants.CENTER);
        JButton logoutBtn = new JButton("Logout");

        add(label, BorderLayout.CENTER);
        add(logoutBtn, BorderLayout.SOUTH);

        logoutBtn.addActionListener(e -> {
            frame.showLogin();
        });
    }
}