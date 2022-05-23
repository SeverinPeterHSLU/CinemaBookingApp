package ch.hslu.sweng.group3;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieAdministrationFrame extends JFrame {
    private JTable activeMoviesTable;
    private JLabel lblActiveMoviesTabel;
    private JButton btnAddMovie;
    private JButton btnBackToMain;
    private JPanel movieAdministrationPanel;
    private JPanel movieTablePanel;


    public MovieAdministrationFrame() {
        setLayout(null);
        setTitle("Movie Administration");
        setSize(1500, 800);
        setContentPane(movieAdministrationPanel);
        movieTablePanel.setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultTableModel model = new DefaultTableModel();
        activeMoviesTable = new JTable(model);
        movieTablePanel.add(activeMoviesTable, BorderLayout.CENTER);
        movieTablePanel.add(new JScrollPane(activeMoviesTable));
        activeMoviesTable.setModel(model);


        Object[] headers = {"Movie ID", "Movie Title", "Duration (min)", "Edit", "Delete"};
        model.setColumnIdentifiers(headers);


        ArrayList<Movie> allActiveMovies = Movie.getMovies();


        for (int r = 0; r < allActiveMovies.size(); r++) {
            String movieID = Integer.toString(allActiveMovies.get(r).getMovieID());
            String movieTitle = allActiveMovies.get(r).getMovieTitle();
            String duration = Integer.toString(allActiveMovies.get(r).getMovieDuration());

            Object[] row = {movieID, movieTitle, duration, "Edit", "Delete"};
            model.addRow(row);
        }
        Action edit = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Object obj = activeMoviesTable.getValueAt(activeMoviesTable.getSelectedRow(), 0);
                String movieID_string = obj.toString();
                int movieID = Integer.parseInt(movieID_string);
                dispose();
                EditMovieFormFrame editMovieFormFrame = new EditMovieFormFrame(movieID);
            }
        };
        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Object obj = activeMoviesTable.getValueAt(activeMoviesTable.getSelectedRow(), 0);
                String movieID_string = obj.toString();
                int movieID = Integer.parseInt(movieID_string);
                Movie m = Movie.getMovie(movieID);
                if (Movie.removeMovie(m) == false) {
                    InfoBox.infoBox("There are shows booked already for this movie. Therefore it cannot be removed.", "Movie cannot be removed");
                }
                dispose();
                MovieAdministrationFrame newMovieAdministrationFrame = new MovieAdministrationFrame();
            }
        };

        ButtonColumn editButtons = new ButtonColumn(activeMoviesTable, edit, 3);
        ButtonColumn deleteButtons = new ButtonColumn(activeMoviesTable, delete, 4);


        //opens form to create new movie
        btnAddMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AddMovieFormFrame addMovieForm = new AddMovieFormFrame();
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        movieAdministrationPanel = new JPanel();
        movieAdministrationPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(movieAdministrationPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblActiveMoviesTabel = new JLabel();
        lblActiveMoviesTabel.setText("Currently Screened Movies");
        movieAdministrationPanel.add(lblActiveMoviesTabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnAddMovie = new JButton();
        btnAddMovie.setText("Add Movie");
        movieAdministrationPanel.add(btnAddMovie, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        btnBackToMain = new JButton();
        btnBackToMain.setText("Go Back");
        movieAdministrationPanel.add(btnBackToMain, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, 40), null, 0, false));
        movieTablePanel = new JPanel();
        movieTablePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        movieAdministrationPanel.add(movieTablePanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        activeMoviesTable = new JTable();
        movieTablePanel.add(activeMoviesTable, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 50), null, 0, false));
    }
}
