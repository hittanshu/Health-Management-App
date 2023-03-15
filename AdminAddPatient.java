import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AdminAddPatient extends JFrame implements ActionListener {
    
    JLabel titleLabel, nameLabel, mobileLabel, genderLabel, dobLabel, historyLabel, usernameLabel, passwordLabel;
    JTextField nameTextField, mobileTextField, dobTextField, historyTextField, usernameTextField;
    JComboBox<String> genderComboBox;
    JPasswordField passwordField;
    JButton addButton;
    
    public AdminAddPatient() {
        super("Add Patient");
        setLayout(new GridLayout(9, 2));
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        titleLabel = new JLabel("Add New Patient", JLabel.CENTER);
        nameLabel = new JLabel("Name:", JLabel.LEFT);
        mobileLabel = new JLabel("Mobile:", JLabel.LEFT);
        genderLabel = new JLabel("Gender:", JLabel.LEFT);
        dobLabel = new JLabel("Date of Birth (dd/mm/yyyy):", JLabel.LEFT);
        historyLabel = new JLabel("Medical History:", JLabel.LEFT);
        usernameLabel = new JLabel("Username:", JLabel.LEFT);
        passwordLabel = new JLabel("Password:", JLabel.LEFT);
        
        nameTextField = new JTextField();
        mobileTextField = new JTextField();
        dobTextField = new JTextField();
        historyTextField = new JTextField();
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        
        genderComboBox = new JComboBox<String>(new String[]{"Male", "Female", "Other"});
        
        addButton = new JButton("Add Patient");
        addButton.addActionListener(this);
        
        add(titleLabel);
        add(new JLabel(""));
        add(nameLabel);
        add(nameTextField);
        add(mobileLabel);
        add(mobileTextField);
        add(genderLabel);
        add(genderComboBox);
        add(dobLabel);
        add(dobTextField);
        add(historyLabel);
        add(historyTextField);
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel(""));
        add(addButton);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthManagementSystem");
                
                String query = "INSERT INTO patient (Name, Mobile, Gender, DOB, Medicalhistory, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, nameTextField.getText());
                ps.setString(2, mobileTextField.getText());
                ps.setString(3, (String) genderComboBox.getSelectedItem());
                ps.setString(4, dobTextField.getText());
                ps.setString(5, historyTextField.getText());
                ps.setString(6, usernameTextField.getText());
                ps.setString(7, new String(passwordField.getPassword()));
                
                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Patient added successfully.");
                    nameTextField.setText("");
                    mobileTextField.setText("");
                    dobTextField.setText("");
                    historyTextField.setText("");
                    usernameTextField.setText("");
                    passwordField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Unable to add patient.");
                }
                
                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new AdminAddPatient();
    }
}
