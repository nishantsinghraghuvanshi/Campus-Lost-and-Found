import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Student{
  public Student(String current_student,int user_id){

    Connection con = DatabaseUtility.getConnection();

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(700,500);
    frame.setTitle("Campus Lost and Found as " + current_student);
    
    JTabbedPane pane = new JTabbedPane();

    JPanel feedpanel = new JPanel();
    feedpanel.setLayout(new GridLayout(4,2));

    
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
