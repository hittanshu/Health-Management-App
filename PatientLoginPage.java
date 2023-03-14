import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PatientLoginPage extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, forgotPasswordButton;

    public PatientLoginPage() {
        super("Patient Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        // Email label and text field
        JLabel emailLabel = new JLabel("Email: ");
        add(emailLabel);
        emailField = new JTextField();
        add(emailField);

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
            // Check if email and password are valid
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (isValid(email, password)) {
                // Redirect to home page
                PatientHomePage patientHomePage = new PatientHomePage();
                setVisible(false);
            } else {
                // Show error message if email or password are invalid
                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
            }
        } else if (e.getSource() == forgotPasswordButton) {
            // Show forgot password dialog
            JOptionPane.showMessageDialog(this, "Please contact customer support to reset your password.");
        }
    }

    private boolean isValid(String email, String password) {

        return !email.isEmpty() && !password.isEmpty();
    }

    public static void main(String[] args) {
        PatientLoginPage patientLoginPage = new PatientLoginPage();
    }
}
