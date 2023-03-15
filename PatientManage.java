import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PatientManage extends JPanel {
    private JTable table;

    public PatientManage() {
        setLayout(new BorderLayout());

        // Create the table model and add the data
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Patient ID");
        model.addColumn("Name");
        model.addColumn("Gender");
        model.addColumn("DOB");
        model.addColumn("Medical History");
        model.addColumn("Username");
        model.addColumn("Password");
        getData(model);

        // Create the table and add it to a scroll pane
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    private void getData(DefaultTableModel model) {
        String dbURL = "jdbc:mysql://localhost:3306/HealthManagementSystem";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT PATIENTID, NAME, GENDER, DOB, MEDICALHISTORY, USERNAME, PASSWORD FROM PATIENT");

            while (result.next()) {
                Object[] row = { result.getInt("PATIENTID"), result.getString("NAME"), result.getString("GENDER"), result.getString("DOB"), result.getString("MEDICALHISTORY"), result.getString("USERNAME"), result.getString("PASSWORD") };
                model.addRow(row);
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Patient Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new PatientManage();
        frame.add(panel);

        frame.setVisible(true);
    }
}
