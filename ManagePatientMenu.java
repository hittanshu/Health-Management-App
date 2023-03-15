import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagePatientMenu extends JFrame implements ActionListener {
    private JButton addPatientBtn, deletePatientBtn, viewPatientBtn;

    public ManagePatientMenu() {
        setTitle("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout());

        addPatientBtn = new JButton("Add Patient");
        deletePatientBtn = new JButton("Delete Patient");
        viewPatientBtn = new JButton("View Patient");

        addPatientBtn.addActionListener(this);
        deletePatientBtn.addActionListener(this);
        viewPatientBtn.addActionListener(this);

        panel.add(addPatientBtn);
        panel.add(deletePatientBtn);
        panel.add(viewPatientBtn);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPatientBtn) {
            AddPatientFrame addPatientFrame = new AddPatientFrame();
            addPatientFrame.setVisible(true);
        } else if (e.getSource() == deletePatientBtn) {
            DeletePatientFrame deletePatientFrame = new DeletePatientFrame();
            deletePatientFrame.setVisible(true);
        } else if (e.getSource() == viewPatientBtn) {
            dispose();
            new PatientManage();
            ViewPatientFrame viewPatientFrame = new ViewPatientFrame();
            viewPatientFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        ManagePatientMenu ManagePatientMenu = new ManagePatientMenu();
    }
}

class AddPatientFrame extends JFrame {
    public AddPatientFrame() {
        setTitle("Add Patient Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Add Patient Form");
        panel.add(label);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
    }
}

class DeletePatientFrame extends JFrame {
    public DeletePatientFrame() {
        setTitle("Delete Patient Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Delete Patient Form");
        panel.add(label);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
    }
}

class ViewPatientFrame extends JFrame {
    public ViewPatientFrame() {
        setTitle("View Patient Frame");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("View Patient Form");
        panel.add(label);

        getContentPane().add(panel);
        setLocationRelativeTo(null);
    }
}
