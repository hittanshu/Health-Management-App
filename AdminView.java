import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminView extends JFrame implements ActionListener {
    private JButton patientButton, doctorButton, appointmentButton, prescriptionButton, billingButton;

    public AdminView() {
        // Set up the frame
        super("Admin // Health Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 200);

        // Create the buttons
        patientButton = new JButton("Manage Patients");
        patientButton.addActionListener(this);
        doctorButton = new JButton("Manage Doctors");
        doctorButton.addActionListener(this);

        // Add the buttons to the content pane
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(patientButton);
        buttonPanel.add(doctorButton);
        getContentPane().add(buttonPanel);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == patientButton) {
            ManagePatientMenu patientManagementScreen = new ManagePatientMenu();
        } else if (e.getSource() == doctorButton) {
            ManageDoctorMenu doctorManagementScreen = new ManageDoctorMenu();
        }
    }
    public static void main(String[] args) {
        AdminView Admin = new AdminView();
    }
}
