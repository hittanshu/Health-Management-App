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
        setSize(500, 500);

        // Create the buttons
        patientButton = new JButton("Manage Patients");
        patientButton.addActionListener(this);
        doctorButton = new JButton("Manage Doctors");
        doctorButton.addActionListener(this);
        appointmentButton = new JButton("Manage Appointments");
        appointmentButton.addActionListener(this);
        prescriptionButton = new JButton("Manage Prescriptions");
        prescriptionButton.addActionListener(this);
        billingButton = new JButton("Manage Billing");
        billingButton.addActionListener(this);

        // Add the buttons to the content pane
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.add(patientButton);
        buttonPanel.add(doctorButton);
        buttonPanel.add(appointmentButton);
        buttonPanel.add(prescriptionButton);
        buttonPanel.add(billingButton);
        getContentPane().add(buttonPanel);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == patientButton) {
            // Open the patient management screen
            // PatientManagementScreen patientManagementScreen = new PatientManagementScreen();
        } else if (e.getSource() == doctorButton) {
            // Open the doctor management screen
            // DoctorManagementScreen doctorManagementScreen = new DoctorManagementScreen();
        } else if (e.getSource() == appointmentButton) {
            // Open the appointment management screen
            // AppointmentManagementScreen appointmentManagementScreen = new AppointmentManagementScreen();
        } else if (e.getSource() == prescriptionButton) {
            // Open the prescription management screen
            // PrescriptionManagementScreen prescriptionManagementScreen = new PrescriptionManagementScreen();
        } else if (e.getSource() == billingButton) {
            // Open the billing management screen
            // BillingManagementScreen billingManagementScreen = new BillingManagementScreen();
        }
    }

    public static void main(String[] args) {
        AdminView Admin = new AdminView();
    }
}
