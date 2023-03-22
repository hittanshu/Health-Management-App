import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class PatientHomePage extends JFrame {
    private JLabel welcomeLabel;
    private JPanel appointmentsPanel, medicationPanel, notificationsPanel, diseasPanel;
    private JLabel upcomingAppointmentsLabel, medicationLabel, notificationsLabel, diseasLabel;
    private JTextArea upcomingAppointmentsTextArea, medicationTextArea, notificationsTextArea, diseasTextArea;
    private JScrollPane upcomingAppointmentsScrollPane, medicationScrollPane, notificationsScrollPane, diseaseScrollPane;

    public PatientHomePage() {
        super("Patient Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new GridLayout(5, 1));

        // Create top panel
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        topPanel.setBackground(new Color(218, 227, 243));
        welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(welcomeLabel);
        add(topPanel);

        // Create appointments panel
        appointmentsPanel = new JPanel();
        appointmentsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        appointmentsPanel.setBackground(Color.WHITE);
        upcomingAppointmentsLabel = new JLabel("Upcoming Appointments:");
        upcomingAppointmentsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        upcomingAppointmentsTextArea = new JTextArea(2, 30);
        upcomingAppointmentsTextArea.setEditable(false);
        upcomingAppointmentsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        upcomingAppointmentsTextArea.setText("Appointment with Dr. Smith on " + LocalDate.now().plusDays(2) +
                "\nAppointment with Dr. Johnson on " + LocalDate.now().plusDays(5));
        upcomingAppointmentsScrollPane = new JScrollPane(upcomingAppointmentsTextArea);
        appointmentsPanel.add(upcomingAppointmentsLabel);
        appointmentsPanel.add(upcomingAppointmentsScrollPane);
        add(appointmentsPanel);

        // Create medication panel
        medicationPanel = new JPanel();
        medicationPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        medicationPanel.setBackground(new Color(218, 227, 243));
        medicationLabel = new JLabel("Medications:");
        medicationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        medicationTextArea = new JTextArea(2, 30);
        medicationTextArea.setEditable(false);
        medicationTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        medicationTextArea.setText("Aspirin - 1 tablet every 4 hours\nIbuprofen - 1 tablet every 6 hours");
        medicationScrollPane = new JScrollPane(medicationTextArea);
        medicationPanel.add(medicationLabel);
        medicationPanel.add(medicationScrollPane);
        add(medicationPanel);

        // Create notifications panel
        notificationsPanel = new JPanel();
        notificationsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        notificationsPanel.setBackground(Color.WHITE);
        notificationsLabel = new JLabel("Notifications:");
        notificationsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        notificationsTextArea = new JTextArea(2, 30);
        notificationsTextArea.setEditable(false);
        notificationsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        notificationsTextArea.setText("Reminder: Your appointment with Dr. Smith is in 2 days\nImportant: " +
                "Please update your medication list with Dr. Johnson");
        notificationsScrollPane = new JScrollPane(notificationsTextArea);
        notificationsPanel.add(notificationsLabel);
        notificationsPanel.add(notificationsScrollPane);
        add(notificationsPanel);

        // Create disease panel
        diseasPanel = new JPanel();
        diseasPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        diseasPanel.setBackground(Color.WHITE);
        diseasLabel = new JLabel("Diseases around you: ");
        diseasLabel.setFont(new Font("Arial", Font.BOLD, 16));
        diseasTextArea = new JTextArea(2, 30);
        diseasTextArea.setEditable(false);
        diseasTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        diseasTextArea.setText("There are 12 reported covid cases in your area.");
        diseaseScrollPane = new JScrollPane(diseasTextArea);
        diseasPanel.add(diseasLabel);
        diseasPanel.add(diseaseScrollPane);
        add(diseasPanel);

        setVisible(true);
    }

    public static void main(String args[]) {
        PatientHomePage PatientHome = new PatientHomePage();
    }
}