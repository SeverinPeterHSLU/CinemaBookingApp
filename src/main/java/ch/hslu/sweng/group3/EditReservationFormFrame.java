package ch.hslu.sweng.group3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditReservationFormFrame extends JFrame {
    private JLabel lblShow;
    private JTextField txtInputShowID;
    private JLabel lblEmail;
    private JTextField txtInputEmail;
    private JLabel lblNumberOfSeats;
    private JTextField txtInputNumberOfSeats;
    private JButton btnSaveReservation;
    private JButton btnExitForm;
    private JLabel lblStatus;
    private JCheckBox checkBoxCollectedRes;
    private JPanel editReservationPanel;

    public EditReservationFormFrame(Reservation res) {
        setTitle("Reservation Changes Form");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setContentPane(editReservationPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        txtInputShowID.setText(String.valueOf(res.getShow().getShowID()));
        txtInputEmail.setText(res.getCustomer().getEmail());
        txtInputNumberOfSeats.setText(String.valueOf(res.getNumberOfSeats()));
        checkBoxCollectedRes.setSelected(res.isCollected());




        //executes a sql update for a reservation, closes the form and goes to the previous frame afterwards
        btnSaveReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer newCustomer = res.getCustomer();
                newCustomer.setEmail(txtInputEmail.getText());
                res.setCustomer(newCustomer);
                res.setNumberOfSeats(Integer.parseInt(txtInputNumberOfSeats.getText()));
                res.setCollected(checkBoxCollectedRes.isSelected());

                Reservation.editReservation(res);
                dispose();
                ReservationAdministrationFrame reservationAdministrationFrame = new ReservationAdministrationFrame();
            }
        });

        // closes the form and goes to the previous frame
        btnExitForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                ReservationAdministrationFrame reservationAdministrationFrame = new ReservationAdministrationFrame();

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
        editReservationPanel = new JPanel();
        editReservationPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        lblShow = new JLabel();
        lblShow.setText("Show");
        editReservationPanel.add(lblShow, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputShowID = new JTextField();
        txtInputShowID.setEditable(false);
        editReservationPanel.add(txtInputShowID, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblEmail = new JLabel();
        lblEmail.setText("Email");
        editReservationPanel.add(lblEmail, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputEmail = new JTextField();
        editReservationPanel.add(txtInputEmail, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblNumberOfSeats = new JLabel();
        lblNumberOfSeats.setText("Number of Seats");
        editReservationPanel.add(lblNumberOfSeats, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputNumberOfSeats = new JTextField();
        editReservationPanel.add(txtInputNumberOfSeats, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblStatus = new JLabel();
        lblStatus.setText("Status of Reservation");
        editReservationPanel.add(lblStatus, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxCollectedRes = new JCheckBox();
        checkBoxCollectedRes.setText("Collected");
        editReservationPanel.add(checkBoxCollectedRes, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSaveReservation = new JButton();
        btnSaveReservation.setText("Save");
        editReservationPanel.add(btnSaveReservation, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 1, false));
        btnExitForm = new JButton();
        btnExitForm.setText("Cancel");
        editReservationPanel.add(btnExitForm, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 1, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return editReservationPanel;
    }
}
