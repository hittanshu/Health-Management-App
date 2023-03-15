import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class PatientLoginPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, forgotPasswordButton;

    public PatientLoginPage() {
        super("Patient Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username: ");
        add(usernameLabel);
        usernameField = new JTextField();
        add(usernameField);

        // Password label and password field
        JLabel passwordLabel = new JLabel("Password: ");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        // Forgot password button
        forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.addActionListener(this);
        add(forgotPasswordButton);

        setVisible(true);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Check if username and password are valid
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (verifyPatientLogin(username, password)) {
                // Redirect to home page
                PatientHomePage patientHomePage = new PatientHomePage();
                setVisible(false);
            } else {
                // Show error message if username or password are invalid
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
            }
        } else if (e.getSource() == forgotPasswordButton) {
            // Show forgot password dialog
            JOptionPane.showMessageDialog(this, "Please contact customer support to reset your password.");
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
            String selectPatient = "SELECT * FROM patient WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(selectPatient);
            statement.setString(1, usernameField.getText());
            statement.setString(2, passwordField.getText());
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
        PatientLoginPage patientLoginPage = new PatientLoginPage();
    }
}
