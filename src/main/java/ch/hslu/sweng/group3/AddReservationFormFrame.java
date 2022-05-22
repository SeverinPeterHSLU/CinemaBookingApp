package ch.hslu.sweng.group3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddReservationFormFrame extends JFrame {
    private JLabel lblShow;
    private JTextField txtInputShowID;
    private JLabel lblEmail;
    private JTextField txtInputEmail;
    private JLabel lblNumberOfSeats;
    private JTextField txtInputNumberOfSeats;
    private JButton btnSaveReservation;
    private JButton btnExitForm;
    private JPanel addReservationPanel;


    public AddReservationFormFrame(int showID) {
        setTitle("Reservation Form");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setContentPane(addReservationPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        txtInputShowID.setText(String.valueOf(showID));


        //closes the form, executes a sql insert for a new reservation and goes to the previous frame afterwards
        btnSaveReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Show s = Show.getShow(Integer.parseInt(txtInputShowID.getText()));
                Customer c = Customer.getCustomerByEmail(txtInputEmail.getText());
                if (c == null) {
                    Customer.addCustomer(txtInputEmail.getText());
                }
                Customer newCustomer = Customer.getCustomerByEmail(txtInputEmail.getText());
                int numberOfSeats = Integer.parseInt(txtInputNumberOfSeats.getText());
                int reservationNumber = Reservation.addReservation(numberOfSeats, newCustomer, s);
                InfoBox.infoBox("The reservation number for the customer is the following: " + reservationNumber, "Reservation Number");

                dispose();
                MainFrame mainFrame = new MainFrame();
            }
        });

        // closes the form and goes to the previous frame
        btnExitForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainFrame mainFrame = new MainFrame();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        addReservationPanel = new JPanel();
        addReservationPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        lblShow = new JLabel();
        lblShow.setText("Show");
        addReservationPanel.add(lblShow, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputShowID = new JTextField();
        txtInputShowID.setEditable(false);
        addReservationPanel.add(txtInputShowID, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblEmail = new JLabel();
        lblEmail.setText("Email");
        addReservationPanel.add(lblEmail, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputEmail = new JTextField();
        addReservationPanel.add(txtInputEmail, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblNumberOfSeats = new JLabel();
        lblNumberOfSeats.setText("Number of Seats");
        addReservationPanel.add(lblNumberOfSeats, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputNumberOfSeats = new JTextField();
        addReservationPanel.add(txtInputNumberOfSeats, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btnSaveReservation = new JButton();
        btnSaveReservation.setText("Save");
        addReservationPanel.add(btnSaveReservation, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 1, false));
        btnExitForm = new JButton();
        btnExitForm.setText("Cancel");
        addReservationPanel.add(btnExitForm, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 1, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return addReservationPanel;
    }

}
