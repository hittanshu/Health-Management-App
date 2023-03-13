import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PatientHomePage extends JFrame {
    private JLabel welcomeLabel, medicalHistoryLabel, upcomingAppointmentsLabel, previousAppointmentsLabel,
            medicationLabel, notificationsLabel;
    private JTextArea medicalHistoryTextArea, upcomingAppointmentsTextArea, previousAppointmentsTextArea,
            medicationTextArea, notificationsTextArea;

    public PatientHomePage() {
        // Set up the frame
        super("Patient Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(6, 2));

        // Create the welcome label
        welcomeLabel = new JLabel("Welcome, John Doe!");
        add(welcomeLabel);

        // Create the medical history label and text area
        medicalHistoryLabel = new JLabel("Medical History");
        add(medicalHistoryLabel);
        medicalHistoryTextArea = new JTextArea(5, 30);
        medicalHistoryTextArea.setEditable(false);
        JScrollPane medicalHistoryScrollPane = new JScrollPane(medicalHistoryTextArea);
        add(medicalHistoryScrollPane);

        // Create the upcoming appointments label and text area
        upcomingAppointmentsLabel = new JLabel("Upcoming Appointments");
        add(upcomingAppointmentsLabel);
        upcomingAppointmentsTextArea = new JTextArea(5, 30);
        upcomingAppointmentsTextArea.setEditable(false);
        JScrollPane upcomingAppointmentsScrollPane = new JScrollPane(upcomingAppointmentsTextArea);
        add(upcomingAppointmentsScrollPane);

        // Create the previous appointments label and text area
        previousAppointmentsLabel = new JLabel("Previous Appointments");
        add(previousAppointmentsLabel);
        previousAppointmentsTextArea = new JTextArea(5, 30);
        previousAppointmentsTextArea.setEditable(false);
        JScrollPane previousAppointmentsScrollPane = new JScrollPane(previousAppointmentsTextArea);
        add(previousAppointmentsScrollPane);

        // Create the medication label and text area
        medicationLabel = new JLabel("Medication");
        add(medicationLabel);
        medicationTextArea = new JTextArea(5, 30);
        medicationTextArea.setEditable(false);
        JScrollPane medicationScrollPane = new JScrollPane(medicationTextArea);
        add(medicationScrollPane);

        // Create the notifications label and text area
        notificationsLabel = new JLabel("Notifications");
        add(notificationsLabel);
        notificationsTextArea = new JTextArea(5, 30);
        notificationsTextArea.setEditable(false);
        JScrollPane notificationsScrollPane = new JScrollPane(notificationsTextArea);
        add(notificationsScrollPane);

        // Populate the text areas with dummy data
        medicalHistoryTextArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse " +
                "porta mi quis augue varius, ut convallis diam interdum. Nam luctus dictum ex, quis maximus " +
                "justo vulputate ut.");
        upcomingAppointmentsTextArea.setText("Appointment with Dr. Smith on " + LocalDate.now().plusDays(2) +
                "\nAppointment with Dr. Johnson on " + LocalDate.now().plusDays(5));
        previousAppointmentsTextArea.setText("Appointment with Dr. Lee on " + LocalDate.now().minusDays(10) +
                "\nAppointment with Dr. Patel on " + LocalDate.now().minusDays(30));
        medicationTextArea.setText("Aspirin - 1 tablet every 4 hours\nIbuprofen - 1 tablet every 6 hours");
        notificationsTextArea.setText("Reminder: Your appointment with Dr. Smith is in 2 days\nImportant: " +
                "Please update your medication list with Dr. Johnson");
        // Show the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        PatientHomePage patientHomePage = new PatientHomePage();
    }
}
