package ch.hslu.sweng.group3;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditShowFormFrame extends JFrame {
    private JLabel lblMovieTitle;
    private JComboBox comboBoxMovie;
    private JLabel lblDate;
    private JTextField txtInputDate;
    private JLabel lblStartTime;
    private JTextField txtInputStartTime;
    private JLabel lblRoom;
    private JComboBox comboBoxRoom;
    private JButton btnSaveShow;
    private JButton btnExitForm;
    private JPanel editShowPanel;

    public EditShowFormFrame(int showID) {
        setTitle("Show Changes Form");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setContentPane(editShowPanel);
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat tf = new SimpleDateFormat("HH.mm");

        Show show = App.showDAO.getShow(showID);
        comboBoxMovie.setSelectedItem(show.getMovie().getMovieTitle() + "  [" + show.getMovie().getMovieID() + "]");
        txtInputDate.setText(df.format(show.getStart()));
        txtInputStartTime.setText(tf.format(show.getStart()));
        comboBoxRoom.setSelectedItem(show.getRoom().getRoomID());


        //executes a sql update for a show, closes the form and goes to the previous frame afterwards
        btnSaveShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Show editedShow = App.showDAO.getShow(showID);
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH.mm");
                String movieTitle = (String) comboBoxMovie.getSelectedItem();
                String movieID_asString = movieTitle.substring(movieTitle.indexOf("[") + 1, movieTitle.indexOf("]"));
                editedShow.setMovie(App.movieDAO.getMovie(Integer.parseInt(movieID_asString)));
                editedShow.setRoom(App.roomDAO.getRoom((int) comboBoxRoom.getSelectedItem()));

                String dateToInsert = txtInputDate.getText() + " " + txtInputStartTime.getText();
                if (ExceptionCheck.isValidDateFormat(txtInputDate.getText(), txtInputStartTime.getText()) == true) {
                    int year = Integer.parseInt(txtInputDate.getText().substring(0, 4)) - 1900;
                    int month = Integer.parseInt(txtInputDate.getText().substring(5, 7)) - 1;
                    int day = Integer.parseInt(txtInputDate.getText().substring(8, 10));
                    int hour = Integer.parseInt(txtInputStartTime.getText().substring(0, 2));
                    int minute = Integer.parseInt(txtInputStartTime.getText().substring(3, 5));
                    Date d1 = new Date(year, month, day, hour, minute);
                    if (ExceptionCheck.isDateInFuture(d1, df) == true) {
                        Calendar c = Calendar.getInstance();
                        c.setTime(d1);
                        c.add(Calendar.MINUTE, editedShow.getMovie().getMovieDuration());
                        Date d2 = c.getTime();
                        df.format(d1);
                        df.format(d2);
                        Show showInRoom = App.roomDAO.isOccupiedBy(d1, d2, editedShow.getRoom());
                        if (showInRoom == null || editedShow.getShowID() == showInRoom.getShowID()) {
                            editedShow.setStart(d1);
                            if (App.showDAO.editShow(editedShow) == false) {
                                InfoBox.infoBox("The Show can't be edited as there are already reservations made.", "Show can't be changed");
                            }
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
                ShowAdministrationFrame showAdministrationFrame = new ShowAdministrationFrame();

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
        editShowPanel = new JPanel();
        editShowPanel.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
        lblMovieTitle = new JLabel();
        lblMovieTitle.setText("Movie Title");
        editShowPanel.add(lblMovieTitle, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxMovie = new JComboBox();
        editShowPanel.add(comboBoxMovie, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblDate = new JLabel();
        lblDate.setText("Date");
        editShowPanel.add(lblDate, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputDate = new JTextField();
        editShowPanel.add(txtInputDate, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblStartTime = new JLabel();
        lblStartTime.setText("Start Time");
        editShowPanel.add(lblStartTime, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtInputStartTime = new JTextField();
        editShowPanel.add(txtInputStartTime, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblRoom = new JLabel();
        lblRoom.setText("Room");
        editShowPanel.add(lblRoom, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxRoom = new JComboBox();
        editShowPanel.add(comboBoxRoom, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSaveShow = new JButton();
        btnSaveShow.setText("Save");
        editShowPanel.add(btnSaveShow, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        btnExitForm = new JButton();
        btnExitForm.setText("Cancel");
        editShowPanel.add(btnExitForm, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return editShowPanel;
    }

}
