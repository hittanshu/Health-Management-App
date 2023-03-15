import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientLoginRegisterPage extends JFrame {
    private JButton loginButton, registerButton, adminLoginButton;
    
    public PatientLoginRegisterPage() {
        super("Patient Login/Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(3, 1));
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login page
                dispose();
                new PatientLoginPage();
            }
        });
        panel.add(loginButton);
        
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the registration page
                dispose();
                new PatientRegistrationPage();
            }
        });
        panel.add(registerButton);
        
        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the registration page
                dispose();
                new AdminLoginPage();
            }
        });
        panel.add(adminLoginButton);
        add(panel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        PatientLoginRegisterPage page = new PatientLoginRegisterPage();
    }
}
