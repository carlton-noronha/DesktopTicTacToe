package tictactoe;

import enums.GameStates;
import listeners.ResetListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TicTacToeStatusBar extends JPanel {

    private LabelStatus labelStatus;
    private ResetListener resetListener;

    TicTacToeStatusBar() {
        setLayout(new BorderLayout());
        setSize(400, 200);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        initComponents();
    }

    private void initComponents() {
        labelStatus = new LabelStatus(GameStates.GAME_NOT_STARTED.getState());
        add(labelStatus, BorderLayout.WEST);
        JButton buttonReset = new ButtonReset();
        buttonReset.addActionListener(actionEvent -> {
            if (resetListener != null) {
                resetListener.onReset(labelStatus);
            }
        });
        add(buttonReset, BorderLayout.EAST);
    }

    public LabelStatus getLabelStatus() {
        return labelStatus;
    }

    public void setResetListener(ResetListener resetListener) {
        this.resetListener = resetListener;
    }

}
