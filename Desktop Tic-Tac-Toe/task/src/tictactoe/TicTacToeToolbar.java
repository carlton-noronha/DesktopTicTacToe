package tictactoe;

import listeners.PlayerListener;
import listeners.StartResetListener;

import javax.swing.*;
import java.awt.*;

public class TicTacToeToolbar extends JPanel {

    private StartResetListener startResetListener;
    private LabelStatus labelStatus;
    private PlayerListener playerListener;
    private ButtonStartReset buttonStartReset;
    private ButtonPlayer1 buttonPlayer1;
    private ButtonPlayer2 buttonPlayer2;
    TicTacToeToolbar() {
        setLayout(new BorderLayout());
        initComponentsToolBar();
    }

    private void initComponentsToolBar() {
        buttonPlayer1 = new ButtonPlayer1();
        add(buttonPlayer1, BorderLayout.WEST);
        buttonStartReset = new ButtonStartReset();
        add(buttonStartReset, BorderLayout.CENTER);
        buttonPlayer2 = new ButtonPlayer2();
        add(buttonPlayer2, BorderLayout.EAST);

        Dimension preferred = buttonStartReset.getPreferredSize();
        preferred.width = 195;
        buttonPlayer1.setPreferredSize(preferred);
        buttonStartReset.setPreferredSize(preferred);
        buttonPlayer2.setPreferredSize(preferred);

        buttonPlayer1.setMaximumSize(preferred);
        buttonStartReset.setMaximumSize(preferred);
        buttonPlayer2.setMaximumSize(preferred);

        buttonPlayer1.setMinimumSize(preferred);
        buttonStartReset.setMinimumSize(preferred);
        buttonPlayer2.setMinimumSize(preferred);

        buttonPlayer1.addActionListener(actionEvent -> {
            if (playerListener != null) {
                playerListener.onPlayerTypeSelected(actionEvent);
            }
        });

        buttonPlayer2.addActionListener(actionEvent -> {
            if (playerListener != null) {
                playerListener.onPlayerTypeSelected(actionEvent);
            }
        });

        buttonStartReset.addActionListener(actionEvent -> {
            if (startResetListener != null && labelStatus != null) {
                startResetListener.onStartReset(labelStatus, actionEvent,
                        buttonPlayer1, buttonPlayer2);
            }
        });
    }

    public void setStartResetListener(StartResetListener startResetListener,
                                      LabelStatus labelStatus) {
        this.startResetListener = startResetListener;
        this.labelStatus = labelStatus;
    }

    public void setPlayerListener(PlayerListener playerListener) {
        this.playerListener = playerListener;
    }

    public ButtonStartReset getButtonStartReset() {
        return buttonStartReset;
    }

    public ButtonPlayer1 getButtonPlayer1() {
        return buttonPlayer1;
    }

    public ButtonPlayer2 getButtonPlayer2() {
        return buttonPlayer2;
    }
}
