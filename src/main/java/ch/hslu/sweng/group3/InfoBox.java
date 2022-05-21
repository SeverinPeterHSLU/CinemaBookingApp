package ch.hslu.sweng.group3;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class InfoBox {

    public static void infoBox(String infoMessage, String titleBar){
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
        }

    }

