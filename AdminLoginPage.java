import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLoginPage extends JFrame implements ActionListener{
    private JTextField adminUsernameField;
    private JLabel adminUsernameLabel, adminPasswordLabel;
    private JPasswordField adminPasswordField;
    private JButton adminLoginButton;

public AdminLoginPage() {
    super("Admin Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 200);
    setLayout(new GridLayout(3, 2));

    adminUsernameLabel = new JLabel("Username: ");
    add(adminUsernameLabel);
    adminUsernameField = new JTextField();
    add(adminUsernameField);

    adminPasswordLabel = new JLabel("Password: ");
    add(adminPasswordLabel);
    adminPasswordField = new JPasswordField();
    add(adminPasswordField);

    adminLoginButton = new JButton("Login");
    adminLoginButton.addActionListener(this);
    add(adminLoginButton);

    setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            // Check if username and password are valid
            String username = adminUsernameField.getText();
            String password = new String(adminPasswordField.getPassword());
            if (verifyPatientLogin(username, password)) {
                // Redirect to home page
                AdminView admin = new AdminView();
                setVisible(false);
            } 
            else {
                // Show error message if username or password are invalid
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        }
    }

    public boolean verifyPatientLogin(String username, String password) {
        boolean isValidLogin = false;
        try {
            // Connect to the MySQL database using XAMPP
            String url = "jdbc:mysql://localhost:3306/HealthManagementSystem";
            String user = "root";
            String dbPassword = "";
            Connection conn = DriverManager.getConnection(url, user, dbPassword);
            System.out.println("Connected to the MySQL database");
    
            // Prepare a SELECT query to retrieve the patient record matching the entered login credentials
            String selectPatient = "SELECT * FROM admin WHERE admin_username = ? AND admin_password = ?";
            PreparedStatement statement = conn.prepareStatement(selectPatient);
            statement.setString(1, adminUsernameField.getText());
            statement.setString(2, adminPasswordField.getText());
            ResultSet result = statement.executeQuery();
    
            // If the query returns a result, then the login credentials are valid
            if (result.next()) {
                isValidLogin = true;
            }
    
            conn.close();
            System.out.println("Disconnected from the MySQL database");
        } catch (SQLException ex) {
            System.err.println("Error connecting to the MySQL database: " + ex.getMessage());
        }
        return isValidLogin;
    }   
    public static void main(String[] args) {
        AdminLoginPage AdminView= new AdminLoginPage();
    }
}
