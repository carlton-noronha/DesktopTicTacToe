package tictactoe;

import enums.GameStates;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TicTacToeStatusBar extends JPanel {

    private LabelStatus labelStatus;

    TicTacToeStatusBar() {
        setLayout(new BorderLayout());
        setSize(400, 200);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        initComponents();
    }

    private void initComponents() {
        labelStatus = new LabelStatus(GameStates.GAME_NOT_STARTED.getState());
        add(labelStatus, BorderLayout.WEST);
    }

    public LabelStatus getLabelStatus() {
        return labelStatus;
    }

}
