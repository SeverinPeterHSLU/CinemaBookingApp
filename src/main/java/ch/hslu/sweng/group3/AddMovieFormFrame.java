package ch.hslu.sweng.group3;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddMovieFormFrame extends JFrame {
    private JLabel lblMovieTitle;
    private JTextField txtInputMovieTitle;
    private JLabel lblDuration;
    private JTextField txtInputDuration;
    private JButton btnSaveMovie;
    private JButton btnExitForm;
    private JPanel addMoviePanel;

    public AddMovieFormFrame() {
        setTitle("Movie Form");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setContentPane(addMoviePanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //closes the from, executes a sql insert for a new movie and goes to the previous frame afterwards
        btnSaveMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieTitle = txtInputMovieTitle.getText();
                String duration = txtInputDuration.getText();
                if (ExceptionCheck.isValuePositiveNumber(duration) == true) {
                    App.movieDAO.addMovie(movieTitle, Integer.parseInt(duration), true);
                }

                dispose();
                MovieAdministrationFrame movieAdm = new MovieAdministrationFrame();

            }
        });
        // closes the form and goes to the previous frame
        btnExitForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MovieAdministrationFrame movieAdm = new MovieAdministrationFrame();
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
        addMoviePanel = new JPanel();
        addMoviePanel.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        lblMovieTitle = new JLabel();
        lblMovieTitle.setText("Movie Title");
        addMoviePanel.add(lblMovieTitle, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputMovieTitle = new JTextField();
        addMoviePanel.add(txtInputMovieTitle, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblDuration = new JLabel();
        lblDuration.setText("Duration (in min)");
        addMoviePanel.add(lblDuration, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputDuration = new JTextField();
        addMoviePanel.add(txtInputDuration, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        btnSaveMovie = new JButton();
        btnSaveMovie.setText("Save");
        addMoviePanel.add(btnSaveMovie, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        btnExitForm = new JButton();
        btnExitForm.setText("Cancel");
        addMoviePanel.add(btnExitForm, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return addMoviePanel;
    }

}
