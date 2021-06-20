package listeners;

import tictactoe.ButtonStartReset;
import tictactoe.LabelStatus;

import java.awt.event.ActionEvent;

public interface CellListener {
    void onCellClicked(ActionEvent actionEvent, LabelStatus LabelStatus, ButtonStartReset buttonStartReset);
}
