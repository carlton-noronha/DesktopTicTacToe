package listeners;

import tictactoe.ButtonPlayer1;
import tictactoe.ButtonPlayer2;
import tictactoe.LabelStatus;

import java.awt.event.ActionEvent;

public interface StartResetListener {
    void onStartReset(LabelStatus LabelStatus,
                      ActionEvent actionEvent,
                      ButtonPlayer1 player1,
                      ButtonPlayer2 player2);
}
