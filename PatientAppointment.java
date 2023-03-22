import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class PatientAppointment extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel model;
    private JButton cancelButton;
    private JButton rescheduleButton;

    public PatientAppointment() {
        setTitle("Patient Appointments");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model with columns for PatientID and Appointments
        String[] columnNames = {"PatientID", "Appointments"};
        model = new DefaultTableModel(columnNames, 0);

        // Create table with the model
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create cancel and reschedule buttons
        cancelButton = new JButton("Cancel Appointment");
        rescheduleButton = new JButton("Reschedule Appointment");

        // Add action listeners to the buttons
        cancelButton.addActionListener(this);
        rescheduleButton.addActionListener(this);

        // Add buttons to button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(rescheduleButton);

        // Add button panel to frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Load appointments from database
        loadAppointments();

        setVisible(true);
    }

    private void loadAppointments() {
        // Connect to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthManagementSystem", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient_appointment");
            

            // Add each appointment to the table model
            while (rs.next()) {
                String patientID = rs.getString("PatientID");
                String appointment = rs.getString("Appointments");
                model.addRow(new Object[]{patientID, appointment});
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            // Get selected row and cancel appointment
            int row = table.getSelectedRow();
            if (row >= 0) {
                String patientID = (String)model.getValueAt(row, 0);
                String appointment = (String)model.getValueAt(row, 1);
                cancelAppointment(patientID, appointment);
                model.removeRow(row);
            }
            
        } else if (e.getSource() == rescheduleButton) {
            // Get selected row and reschedule appointment
            int row = table.getSelectedRow();
            if (row >= 0) {
                String patientID = (String)model.getValueAt(row, 0);
                String appointment = (String)model.getValueAt(row, 1);
                rescheduleAppointment(patientID, appointment);
            }
        }
    }

    private void cancelAppointment(String patientID, String appointment) {
        // TODO: Implement cancel appointment logic
    }

    private void rescheduleAppointment(String patientID, String appointment) {
        // TODO: Implement reschedule appointment logic
    }

    public static void main(String[] args) {
        new PatientAppointment();
    }
}
