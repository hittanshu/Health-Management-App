import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewPatients extends JFrame {
    
    JTable table;
    DefaultTableModel model;
    JButton addButton, deleteButton;
    
    public ViewPatients() {
        super("View Patients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        // create table
        String[] columnNames = {"ID", "Name", "Mobile", "Gender", "DOB", "Medical History", "Username", "Password"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        loadData();
        
        // create buttons
        addButton = new JButton("Add Patient");
        deleteButton = new JButton("Delete");
        
        // create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue());
        
        // create panel for table and buttons
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // add table and buttons panel to main content pane
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(tablePanel);
        
        setVisible(true);
        
        // add action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open add patient dialog
                AddPatientDialog dialog = new AddPatientDialog(ViewPatients.this);
                dialog.setVisible(true);
                
                // reload table data after dialog is closed
                if (dialog.isDialogResultOk()) {
                    loadData();
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // delete selected patient
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ViewPatients.this, "Please select a patient to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                    
                }
                
                int patientId = (int) model.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(ViewPatients.this, "Are you sure you want to delete patient with ID " + patientId + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deletePatient(patientId);
                }
            }
        });
    }
    
    private void loadData() {
        model.setRowCount(0); // clear existing rows from table model
        
        Connection con = null;
        try {
            // establish database connection
            String url = "jdbc:mysql://localhost:3306/HealthManagementSystem";
            String user = "root";
            String pass_word = "";
            con = DriverManager.getConnection(url, user, pass_word);
            // execute a query to get all patients
            String sql = "SELECT * FROM patient";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            // add patients to table model
            while (rs.next()) {
                int patientId = rs.getInt("patientID");
                String name = rs.getString("Name");
                String mobile = rs.getString("Mobile");
                String gender = rs.getString("Gender");
                Date dob = rs.getDate("DOB");
                String medicalHistory = rs.getString("Medicalhistory");
                String username= rs.getString("username");
                String password = rs.getString("password");
                Object[] row = {patientId, name, mobile, gender, dob, medicalHistory, username, password};
                model.addRow(row);
                }
                } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error retrieving patient data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                if (con != null) {
                try {
                con.close();
                } catch (SQLException e) {
                // ignore
                }
                }
                }
                }
                private void deletePatient(int patientId) {
                    Connection con = null;
                    try {
                        // establish database connection
                        String url = "jdbc:mysql://localhost:3306/HealthManagementSystem";
                        String user = "root";
                        String pass_word = "";
                        con = DriverManager.getConnection(url, user, pass_word);
                        // execute a query to delete the patient
                        String sql = "DELETE FROM patient WHERE patientID = ?";
                        PreparedStatement stmt = con.prepareStatement(sql);
                        stmt.setInt(1, patientId);
                        stmt.executeUpdate();
                        String selectpatientid = "SELECT latest_id FROM patient_id";
                        Statement statement1 = con.createStatement();
                        ResultSet resultSet = statement1.executeQuery(selectpatientid);
                        resultSet.next();
                        int latestPatientID = resultSet.getInt("latest_id");
                        int newPatientID = latestPatientID - 1;
                        String updatepatientid = "UPDATE patient_id SET latest_id = ?";
                        PreparedStatement statement2 = con.prepareStatement(updatepatientid);
                        statement2.setInt(1, newPatientID);
                        statement2.executeUpdate();
                        // reload table data
                        loadData();
                        
                        JOptionPane.showMessageDialog(this, "Patient with ID " + patientId + " has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error deleting patient: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (SQLException e) {
                                // ignore
                            }
                        }
                    }
                }
                
                public static void main(String[] args) {
                    ViewPatients viewPatients = new ViewPatients();
                }
            }                
