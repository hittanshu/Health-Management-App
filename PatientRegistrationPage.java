import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class PatientRegistrationPage extends JFrame implements ActionListener{

    JLabel label1,label2,label3,label4,label5,usernamelabel, passwordlabel;
    JTextField t1,t2, usernametf;
    JPasswordField passwordfield;
    JRadioButton male,female;
    JComboBox day,month,year;
    JTextArea ta1;
    JButton submit;

	PatientRegistrationPage(){
		setTitle("Registration Form");
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        label1 = new JLabel("Name");
        label1.setBounds(20,10,100,20);
        c.add(label1);

        t1 = new JTextField();
        t1.setBounds(80 , 10, 150, 20);
        c.add(t1);

        label2 = new JLabel("Mobile");
        label2.setBounds(20,40,100,20);
        c.add(label2);

        t2 = new JTextField();
        t2.setBounds(80 , 40, 150, 20);
        c.add(t2);

        label3 = new JLabel("Gender");;
        label3.setBounds(20 , 70 , 100 , 20);
        c.add(label3);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        male.setBounds(80, 70, 80, 20);
        female.setBounds(160,70,80,20);
        c.add(male);
        c.add(female);
        ButtonGroup gen= new ButtonGroup();
        gen.add(male);
        gen.add(female); 

        label4 = new JLabel("DOB");
        label4.setBounds(20, 100, 80, 20);
        c.add(label4);

        String days[] ={"1", "2", "3", "4", "5","6", "7", "8", "9", "10","11", "12", "13", "14", "15","16", "17", "18", "19", "20","21", "22", "23", "24", "25","26", "27", "28", "29", "30","31"};
        String months[] = {"Jan", "feb", "Mar", "Apr","May", "Jun", "July", "Aug","Sep", "Oct", "Nov", "Dec"};
        String years[] = {"2003", "2004", "2005", "2006","2007", "2008", "2009", "2010","2011", "2012", "2013", "2014","2015", "2016", "2017", "2018","2019", "2020", "2021", "2022", "2023"};
        year = new JComboBox(years);
        month = new JComboBox(months);
        day = new JComboBox(days); 
        day.setBounds(85,100,40,20);
        month.setBounds(130 , 100, 45, 20);
        year.setBounds(180 , 100, 55,20);
        c.add(day);
        c.add(month);
        c.add(year);

        usernamelabel = new JLabel("Enter Username: ");
        usernamelabel.setBounds(20 , 130 , 100 , 20);
        c.add(usernamelabel);
        usernametf = new JTextField();
        usernametf.setBounds(120 , 130, 100, 20);
        c.add(t1);
        c.add(usernametf);

        passwordlabel = new JLabel("Enter Password: ");
        passwordlabel.setBounds(230 , 130 , 100 , 20);
        passwordfield = new JPasswordField();
        passwordfield.setBounds(330, 130, 100,20);
        c.add(passwordlabel);
        c.add(passwordfield);

        label5 = new JLabel("Medical History");
        label5.setBounds(20,160,100,20);
        c.add(label5);

        ta1 = new JTextArea();
        ta1.setBounds(20,190,220,80);
        c.add(ta1);

        submit = new JButton("Submit");
        submit.setBounds(20,250,80,20);
        submit.addActionListener(this);
        c.add(submit);  

		setVisible(true);
        
    }

    public static void main(String args[]){
        PatientRegistrationPage Register = new PatientRegistrationPage();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submit) {
                    try {
                        // Connect to the MySQL database using XAMPP
                        String url = "jdbc:mysql://localhost:3306/HealthManagementSystem";
                        String user = "root";
                        String password = "";
                        Connection conn = DriverManager.getConnection(url, user, password);
                        System.out.println("Connected to the MySQL database");
                
                        //for continously incrementing patient id
                        String selectpatientid = "SELECT latest_id FROM patient_id";
                        Statement statement1 = conn.createStatement();
                        ResultSet resultSet = statement1.executeQuery(selectpatientid);
                        resultSet.next();
                        int latestPatientID = resultSet.getInt("latest_id");
                        int newPatientID = latestPatientID + 1;
                        String updatepatientid = "UPDATE patient_id SET latest_id = ?";
                        PreparedStatement statement2 = conn.prepareStatement(updatepatientid);
                        statement2.setInt(1, newPatientID);
                        statement2.executeUpdate();

                        String insertpatientdata = "INSERT INTO patient(PatientID, Name, Mobile, Gender, DOB, Medicalhistory, username, password) VALUES (?,?,?,?,?,?,?,?)";
                        PreparedStatement statement = conn.prepareStatement(insertpatientdata);
                        statement.setInt(1, newPatientID);
                        statement.setString(2, t1.getText());
                        statement.setString(3, t2.getText());
                        statement.setString(4, male.isSelected() ? "Male" : "Female");
                        String dob = year.getSelectedItem() + "-" + String.format("%02d", month.getSelectedIndex()+1) + "-" + String.format("%02d", Integer.parseInt((String) day.getSelectedItem()));
                        statement.setString(5, dob);
                        statement.setString(6, usernametf.getText());
                        statement.setString(7, passwordfield.getText());
                        statement.setString(8, ta1.getText());
                        int rowsInserted = statement.executeUpdate();
                        // JOptionPane.showMessageDialog(this, "Patient registration successful!");
                        System.out.println(rowsInserted + " row(s) inserted");
                        conn.close();
                        System.out.println("Disconnected from the MySQL database");
                    }
                    catch (SQLException ex){
                        System.err.println("Error connecting to the MySQL database: " + ex.getMessage());
                    }
                }
                    PatientLoginRegisterPage patientHomePageafterreg = new PatientLoginRegisterPage();
                    setVisible(false);
                }
            }