package ch.hslu.sweng.group3;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowAdministrationFrame extends JFrame {
    private JLabel lblCurrentShows;
    private JTable currentShowsTable;
    private JButton btnAddShow;
    private JButton btnBackToMain;
    private JPanel showAdministrationPanel;
    private JPanel showTablePanel;

    public ShowAdministrationFrame() {
        setTitle("Show Administration");
        setSize(1500, 800);
        setContentPane(showAdministrationPanel);
        showTablePanel.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultTableModel model = new DefaultTableModel();
        currentShowsTable = new JTable(model);
        showTablePanel.add(currentShowsTable, BorderLayout.CENTER);
        showTablePanel.add(new JScrollPane(currentShowsTable));
        currentShowsTable.setModel(model);


        Object[] headers = {"Show ID", "Start", "MovieID", "Movie Title", "Room", "Edit", "Delete"};
        model.setColumnIdentifiers(headers);


        ArrayList<Show> allShows = App.showDAO.getShows();


        for (int s = 0; s < allShows.size(); s++) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH.mm");
            String showID = Integer.toString(allShows.get(s).getShowID());
            Date d = allShows.get(s).getStart();
            String startOfShow = df.format(d);
            String movieID = String.valueOf(allShows.get(s).getMovie().getMovieID());
            String movieTitle = String.valueOf(allShows.get(s).getMovie().getMovieTitle());
            Room r = allShows.get(s).getRoom();
            String roomID = String.valueOf(r.getRoomID());

            Object[] row = {showID, startOfShow, movieID, movieTitle, roomID, "Edit", "Delete"};
            if (ExceptionCheck.isDateInFuture(d, df) == true) {
                model.addRow(row);
            }

        }

        Action edit = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Object obj = currentShowsTable.getValueAt(currentShowsTable.getSelectedRow(), 0);
                String showID_String = obj.toString();
                int showID = Integer.parseInt(showID_String);
                dispose();
                EditShowFormFrame editShowFormFrame = new EditShowFormFrame(showID);
            }
        };
        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Object obj = currentShowsTable.getValueAt(currentShowsTable.getSelectedRow(), 0);
                String showID_string = obj.toString();
                int showID = Integer.parseInt(showID_string);
                Show s = App.showDAO.getShow(showID);
                if (App.showDAO.removeShow(s) == false) {
                    InfoBox.infoBox("There are reservations booked already for this show. Therefore it cannot be removed.", "Information");
                }
                dispose();
                ShowAdministrationFrame newShowAdministrationFrame = new ShowAdministrationFrame();
            }
        };

        ButtonColumn editButtons = new ButtonColumn(currentShowsTable, edit, 5);
        ButtonColumn deleteButtons = new ButtonColumn(currentShowsTable, delete, 6);

        //opens form to create new show entry
        btnAddShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddShowFormFrame addMovieForm = new AddShowFormFrame();
            }
        });

        //navigates back to mainPanel
        btnBackToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainFrame mainframe = new MainFrame();
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
        showAdministrationPanel = new JPanel();
        showAdministrationPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        lblCurrentShows = new JLabel();
        lblCurrentShows.setText("Upcoming Shows");
        showAdministrationPanel.add(lblCurrentShows, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnAddShow = new JButton();
        btnAddShow.setText("Add Show");
        showAdministrationPanel.add(btnAddShow, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        btnBackToMain = new JButton();
        btnBackToMain.setText("Go Back");
        showAdministrationPanel.add(btnBackToMain, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        showTablePanel = new JPanel();
        showTablePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        showAdministrationPanel.add(showTablePanel, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        currentShowsTable = new JTable();
        showTablePanel.add(currentShowsTable, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return showAdministrationPanel;
    }

}
