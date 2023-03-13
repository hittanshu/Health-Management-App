import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PatientHomePage extends JFrame {
    private JLabel welcomeLabel;
    private JPanel appointmentsPanel, medicationPanel, notificationsPanel;
    private JLabel upcomingAppointmentsLabel, medicationLabel, notificationsLabel;
    private JTextArea upcomingAppointmentsTextArea, medicationTextArea, notificationsTextArea;
    private JScrollPane upcomingAppointmentsScrollPane, medicationScrollPane, notificationsScrollPane;

    public PatientHomePage() {
        super("Patient Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new GridLayout(4, 2));

        // Welcome User
        welcomeLabel = new JLabel("Welcome Hittanshu ! ");
        add(welcomeLabel);

        // Upcoming Appointments
        appointmentsPanel = new JPanel();
        appointmentsPanel.setLayout(new BoxLayout(appointmentsPanel, BoxLayout.LINE_AXIS));
        upcomingAppointmentsLabel = new JLabel("Upcoming Appointments: ");
        upcomingAppointmentsTextArea = new JTextArea(2, 30);
        upcomingAppointmentsTextArea.setEditable(false);
        upcomingAppointmentsScrollPane = new JScrollPane(upcomingAppointmentsTextArea);
        upcomingAppointmentsTextArea.setText("Appointment with Dr. Smith on " + LocalDate.now().plusDays(2) +
                "\nAppointment with Dr. Johnson on " + LocalDate.now().plusDays(5));
        appointmentsPanel.add(upcomingAppointmentsLabel);
        appointmentsPanel.add(upcomingAppointmentsScrollPane);
        add(appointmentsPanel);

        // Medications
        medicationPanel = new JPanel();
        medicationPanel.setLayout(new BoxLayout(medicationPanel, BoxLayout.LINE_AXIS));
        medicationLabel = new JLabel("Medications: ");
        medicationTextArea = new JTextArea(2, 30);
        medicationTextArea.setEditable(false);
        medicationScrollPane = new JScrollPane(medicationTextArea);
        medicationTextArea.setText("Aspirin - 1 tablet every 4 hours\nIbuprofen - 1 tablet every 6 hours");
        medicationPanel.add(medicationLabel);
        medicationPanel.add(medicationScrollPane);
        add(medicationPanel);

        // Notifications
        notificationsPanel = new JPanel();
        notificationsPanel.setLayout(new BoxLayout(notificationsPanel, BoxLayout.LINE_AXIS));
        notificationsLabel = new JLabel("Notifications: ");
        notificationsTextArea = new JTextArea(2, 30);
        notificationsTextArea.setEditable(false);
        notificationsScrollPane = new JScrollPane(notificationsTextArea);
        notificationsTextArea.setText("Reminder: Your appointment with Dr. Smith is in 2 days\nImportant: " +
                "Please update your medication list with Dr. Johnson");
        notificationsPanel.add(notificationsLabel);
        notificationsPanel.add(notificationsScrollPane);
        add(notificationsPanel);

        setVisible(true);
    }

    public static void main(String args[]) {
        PatientHomePage PatientHome = new PatientHomePage();
    }
}