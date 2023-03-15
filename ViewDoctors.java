import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewDoctors extends JFrame {
    
    JTable table;
    DefaultTableModel model;
    JButton addButton, deleteButton;
    
    public ViewDoctors() {
        super("View Dcotors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        // create table
        String[] columnNames = {"ID", "Name", "Mobile", "Address", "Type"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable(model);
        loadData();
        
        // create buttons
        addButton = new JButton("Add Doctor");
        deleteButton = new JButton("Delete Dcotor");
        
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
                dispose();

            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // delete selected patient
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(ViewDoctors.this, "Please select a doctor to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                    
                }
                
                int patientId = (int) model.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(ViewDoctors.this, "Are you sure you want to delete doctor with ID " + DcotorID + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteDoctor(DoctorId);
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
            String sql = "SELECT * FROM doctor";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            // add patients to table model
            while (rs.next()) {
                int DoctorId = rs.getInt("DoctorID");
                String name = rs.getString("DocName");
                String mobile = rs.getString("DocMobile");
                String DocAddress = rs.getString("DocAddress");
                String Type = rs.getString("DocType");
                Object[] row = {DoctorId, name, mobile,DocAddress,Type};
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
                private void deleteDoctor(int DoctorId){
                    Connection con = null;
                    try {
                        // establish database connection
                        String url = "jdbc:mysql://localhost:3306/HealthManagementSystem";
                        String user = "root";
                        String pass_word = "";
                        con = DriverManager.getConnection(url, user, pass_word);
                        // execute a query to delete the patient
                        String sql = "DELETE FROM doctor WHERE DoctorId = ?";
                        PreparedStatement stmt = con.prepareStatement(sql);
                        stmt.setInt(1, DoctorId);
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
                        
                        JOptionPane.showMessageDialog(this, "Doctor with ID " + DoctorId + " has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error deleting doctor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                    ViewDoctors ViewDoctors = new ViewDoctors();
                }
            }                
