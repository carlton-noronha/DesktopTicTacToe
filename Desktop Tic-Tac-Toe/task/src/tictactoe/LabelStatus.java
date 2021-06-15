package tictactoe;

import java.awt.*;

public class LabelStatus extends javax.swing.JLabel {
    LabelStatus(String status) {
        setText(status);
        setName("LabelStatus");
        setFont(new Font("Roboto", Font.BOLD, 14));
    }
}
