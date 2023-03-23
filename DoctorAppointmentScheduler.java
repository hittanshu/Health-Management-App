import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DoctorAppointmentScheduler extends JFrame implements ActionListener {

    private JLabel titleLabel, dateLabel, timeLabel, doctorNameLabel;
    private JTextField dateField, timeField;
    private JButton viewScheduleButton, rescheduleButton;
    private JTable scheduleTable;
    private DefaultTableModel tableModel;

    private String doctorName;

    public DoctorAppointmentScheduler(String doctorName) {
        this.doctorName = doctorName;

        setTitle("Doctor Appointment Scheduler");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titleLabel = new JLabel("Welcome, Dr. " + doctorName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create schedule panel
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new BoxLayout(schedulePanel, BoxLayout.Y_AXIS));
        schedulePanel.setBorder(BorderFactory.createTitledBorder("Schedule"));
        dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateField = new JTextField(10);
        timeLabel = new JLabel("Time (HH:MM):");
        timeField = new JTextField(10);
        viewScheduleButton = new JButton("View Schedule");
        viewScheduleButton.addActionListener(this);
        rescheduleButton = new JButton("Reschedule");
        rescheduleButton.addActionListener(this);
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(viewScheduleButton);
        buttonPanel.add(rescheduleButton);
        schedulePanel.add(inputPanel);
        schedulePanel.add(buttonPanel);

        // Create table panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Appointments"));
        String[] columns = {"Patient ID", "Date", "Time"};
        tableModel = new DefaultTableModel(columns, 0);
        scheduleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        tablePanel.add(scrollPane);

        // Add panels to frame
        add(titlePanel, BorderLayout.NORTH);
        add(schedulePanel, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewScheduleButton) {
            // Get date and time from text fields
            String date = dateField.getText();
            String time = timeField.getText();

            // Validate input
            if (!isValidDate(date) || !isValidTime(time)) {
                JOptionPane.showMessageDialog(this, "Invalid date or time format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Query database for appointments
            ArrayList<Appointment> appointments = getAppointments(date, time);
            tableModel.setRowCount(0);
            for (        Appointment appointment : appointments) {
                tableModel.addRow(new Object[]{appointment.getPatientID(), appointment.getDate(), appointment.getTime()});
            }
    
        } else if (e.getSource() == rescheduleButton) {
            // Get selected appointment
            int row = scheduleTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Please select an appointment.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String patientID = (String)tableModel.getValueAt(row, 0);
            String date = (String)tableModel.getValueAt(row, 1);
            String time = (String)tableModel.getValueAt(row, 2);
    
            // Show reschedule dialog
            String newDate = JOptionPane.showInputDialog(this, "Enter new date (YYYY-MM-DD):", date);
            String newTime = JOptionPane.showInputDialog(this, "Enter new time (HH:MM):", time);
    
            // Validate input
            if (!isValidDate(newDate) || !isValidTime(newTime)) {
                JOptionPane.showMessageDialog(this, "Invalid date or time format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Reschedule appointment in database
            if (rescheduleAppointment(patientID, date, time, newDate, newTime)) {
                // Update table
                tableModel.setValueAt(newDate, row, 1);
                tableModel.setValueAt(newTime, row, 2);
                JOptionPane.showMessageDialog(this, "Appointment rescheduled.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error rescheduling appointment.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime.parse(date, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private boolean isValidTime(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(time, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private ArrayList<Appointment> getAppointments(String date, String time) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthManagementSystem", "root", "");
            String query = "SELECT patient_id, date, time FROM appointments WHERE doctor_name=? AND date=? AND time=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, doctorName);
            pstmt.setString(2, date);
            pstmt.setString(3, time);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String patientID = rs.getString("patient_id");
                String appointmentDate = rs.getString("date");
                String appointmentTime = rs.getString("time");
                appointments.add(new Appointment(patientID, appointmentDate, appointmentTime));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    
    private boolean rescheduleAppointment(String patientID, String date, String time, String newDate, String newTime) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HealthManagementSystem", "root", "");
            String query = "UPDATE appointments SET date=?, time=? WHERE patient_id=? AND date=? AND time=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newDate);
            pstmt.setString(2, newTime);
            pstmt.setString(3, patientID);
            pstmt.setString(4, date);
            pstmt.setString(5, time);
            int rowsUpdated = pstmt.executeUpdate();
            conn.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static class Appointment {
        private String patientID;
        private String date;
        private String time;
    
        public Appointment(String patientID, String date, String time) {
            this.patientID = patientID;
            this.date = date;
            this.time = time;
        }
    
        public String getPatientID() {
            return patientID;
        }
    
        public String getDate() {
            return date;
        }
    
        public String getTime() {
            return time;
        }
    }
    
    public static void main(String[] args) {
        DoctorAppointmentScheduler frame = new DoctorAppointmentScheduler("Smith");
        frame.setVisible(true);
    }
}    
    
