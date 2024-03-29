package ch.hslu.sweng.group3;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddShowFormFrame extends JFrame {
    private JComboBox comboBoxMovie;
    private JLabel lblMovieTitle;
    private JLabel lblDate;
    private JTextField txtInputDate;
    private JLabel lblStartTime;
    private JTextField txtInputStartTime;
    private JLabel lblRoom;
    private JComboBox comboBoxRoom;
    private JButton btnSaveShow;
    private JButton btnExitForm;
    private JPanel addShowPanel;

    public AddShowFormFrame() {
        setTitle("Show Form");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setContentPane(addShowPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        ArrayList<Movie> allMovies = App.movieDAO.getMovies();
        for (int i = 0; i < allMovies.size(); i++) {
            comboBoxMovie.addItem(allMovies.get(i).getMovieTitle() + "  [" + allMovies.get(i).getMovieID() + "]");
        }

        ArrayList<Room> allRooms = App.roomDAO.getRooms();
        for (int i = 0; i < allRooms.size(); i++) {
            comboBoxRoom.addItem(allRooms.get(i).getRoomID());
        }

        //closes the form, executes a sql insert for a new show and goes to the previous frame afterwards
        btnSaveShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH.mm");
                String movieTitle = (String) comboBoxMovie.getSelectedItem();
                String movieID_asString = movieTitle.substring(movieTitle.indexOf("[") + 1, movieTitle.indexOf("]"));
                int movieID = Integer.parseInt(movieID_asString);

                String dateToInsert = txtInputDate.getText() + " " + txtInputStartTime.getText();
                if (ExceptionCheck.isValidDateFormat(txtInputDate.getText(), txtInputStartTime.getText()) == true) {
                    int year = Integer.parseInt(txtInputDate.getText().substring(0, 4)) - 1900;
                    int month = Integer.parseInt(txtInputDate.getText().substring(5, 7)) - 1;
                    int day = Integer.parseInt(txtInputDate.getText().substring(8, 10));
                    int hour = Integer.parseInt(txtInputStartTime.getText().substring(0, 2));
                    int minute = Integer.parseInt(txtInputStartTime.getText().substring(3, 5));
                    Date d = new Date(year, month, day, hour, minute);
                    if (ExceptionCheck.isDateInFuture(d, df) == true) {
                        Movie movie = App.movieDAO.getMovie(movieID);
                        Room room = App.roomDAO.getRoom((Integer) comboBoxRoom.getSelectedItem());
                        Calendar c = Calendar.getInstance();
                        c.setTime(d);
                        c.add(Calendar.MINUTE, movie.getMovieDuration());
                        Date d2 = c.getTime();
                        df.format(d);
                        df.format(d2);
                        if (App.roomDAO.isOccupiedBy(d, d2, room) == null) {
                            App.showDAO.addShow(d, App.movieDAO.getMovie(movieID), App.roomDAO.getRoom((Integer) comboBoxRoom.getSelectedItem()));
                        } else {
                            InfoBox.infoBox("This Room is occupied at the requested time, plesae select other room or change time", "Room Occupied");
                        }
                    } else {
                        InfoBox.infoBox("The entered Date or Time is not in the future. You can't plan a Show in the past", "Date must be in future.");
                    }
                }
                dispose();
                ShowAdministrationFrame showAdm = new ShowAdministrationFrame();
            }
        });

        // closes the form and goes to the previous frame
        btnExitForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShowAdministrationFrame showAdm = new ShowAdministrationFrame();
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
        addShowPanel = new JPanel();
        addShowPanel.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
        lblMovieTitle = new JLabel();
        lblMovieTitle.setText("Movie Title");
        addShowPanel.add(lblMovieTitle, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxMovie = new JComboBox();
        addShowPanel.add(comboBoxMovie, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblDate = new JLabel();
        lblDate.setText("Date (yyyy.mm.dd)");
        addShowPanel.add(lblDate, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputDate = new JTextField();
        txtInputDate.setText("");
        addShowPanel.add(txtInputDate, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblStartTime = new JLabel();
        lblStartTime.setText("Start Time (hh.mm)");
        addShowPanel.add(lblStartTime, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputStartTime = new JTextField();
        addShowPanel.add(txtInputStartTime, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblRoom = new JLabel();
        lblRoom.setText("Room");
        addShowPanel.add(lblRoom, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxRoom = new JComboBox();
        addShowPanel.add(comboBoxRoom, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSaveShow = new JButton();
        btnSaveShow.setText("Save");
        addShowPanel.add(btnSaveShow, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        btnExitForm = new JButton();
        btnExitForm.setText("Cancel");
        addShowPanel.add(btnExitForm, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return addShowPanel;
    }

}
