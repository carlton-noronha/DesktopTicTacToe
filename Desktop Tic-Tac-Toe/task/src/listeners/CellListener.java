package listeners;

import tictactoe.LabelStatus;

import java.awt.event.ActionEvent;

public interface CellListener {
    void onCellClicked(ActionEvent actionEvent, LabelStatus LabelStatus);
}
