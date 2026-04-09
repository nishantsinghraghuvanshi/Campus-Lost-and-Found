import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class Student{
  public Student(String current_student,int user_id){

    Connection con = DatabaseUtility.getConnection();

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700,500);
    frame.setTitle("Campus Lost and Found as " + current_student);
    
    JTabbedPane pane = new JTabbedPane();

    JPanel reportpanel = new JPanel();
    reportpanel.setLayout(new GridLayout(4,2));

    JLabel items_print = new JLabel();
    items_print.setText("Enter the Item Name : ");
    JTextField items_input = new JTextField();

    JLabel category_print = new JLabel();
    category_print.setText("Please select a category : ");
    String category[] = {"Electronic","Academic","Umbrella","Skincare","Other"};
    JComboBox category_input = new JComboBox(category);

    JLabel status_print = new JLabel();
    status_print.setText("Status of the Item ");
    String status[] = {"Lost","Found"};
    JComboBox status_input = new JComboBox(status);

    JLabel blank = new JLabel();
    JButton submit_button = new JButton();
    submit_button.setText("Submit");

    reportpanel.add(items_print);
    reportpanel.add(items_input);
    reportpanel.add(category_print);
    reportpanel.add(category_input);
    reportpanel.add(status_print);
    reportpanel.add(status_input);
    reportpanel.add(blank);
    reportpanel.add(submit_button);


    JPanel feedpanel = new JPanel();
    feedpanel.setLayout(new BorderLayout());
    String columns[] = {"Name","Category","Status"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    feedpanel.add(scrollPane, BorderLayout.CENTER);
    try {
        String query = "SELECT item_name, category, status FROM Items";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("item_name"),
                rs.getString("category"),
                rs.getString("status")
            });
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error loading data");
    }
      
    pane.add("Feed",feedpanel);
    pane.add("Reported",reportpanel);


    submit_button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        try{
          String item_result = items_input.getText();
          String category_result = (String)category_input.getSelectedItem();
          String status_result = (String)status_input.getSelectedItem();
          String query = "insert into Items(item_name,category,status,reporter_id)values(?,?,?,?)";
          PreparedStatement statement = con.prepareStatement(query);
          statement.setString(1,item_result);
          statement.setString(2,category_result);
          statement.setString(3,status_result);
          statement.setInt(4,user_id);
          statement.executeUpdate();
          JOptionPane.showMessageDialog(null,"Item added Successfully !");
          model.setRowCount(0);

          String query2 = "SELECT item_name, category, status FROM Items";
          PreparedStatement ps2 = con.prepareStatement(query2);
          ResultSet rs2 = ps2.executeQuery();

          while (rs2.next()) {
              model.addRow(new Object[]{
                  rs2.getString("item_name"),
                  rs2.getString("category"),
                  rs2.getString("status")
              });
          }

        }
        catch(Exception ex){
          JOptionPane.showMessageDialog(null,"Invalid Input !");
        }
      }
    });

    frame.add(pane);
    frame.setVisible(true);
  }
}
