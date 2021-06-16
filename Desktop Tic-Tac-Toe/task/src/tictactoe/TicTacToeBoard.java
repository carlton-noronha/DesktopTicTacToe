package tictactoe;

import enums.CellStates;
import listeners.CellListener;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TicTacToeBoard extends JPanel {

    CellListener cellListener;
    int boardSize;
    private LabelStatus labelStatus;
    private final TicTacToeCell[][] board;
    private ButtonStartReset buttonStartReset;

    TicTacToeBoard(int size) {
        this.boardSize = size;
        board = new TicTacToeCell[size][size];
        setLayout(new GridLayout(size, size));
        initComponents();
    }

    /*
        Add Cells (Buttons) to the board and giving them names
        based on Chess notation
     */
    private void initComponents() {
        for (int row = boardSize; row > 0; --row) {
            for (int col = 0; col < boardSize; ++col) {
                String coordinates = (char) ('A' + col) + "" + row;
                String name = "Button" + coordinates;
                TicTacToeCell cell = new TicTacToeCell(name, CellStates.CELL_EMPTY.getCellState());
                board[boardSize - row][col] = cell;
                /*
                Board Format:
                [
                    [A3, B3, C3],
                    [A2, B2, C2],
                    [A1, B1, C1]
                ]
                 */
                cell.setEnabled(false);
                cell.addActionListener(actionEvent -> {
                    if (cellListener != null && labelStatus != null && buttonStartReset != null) {
                        cellListener.onCellClicked(actionEvent, labelStatus, buttonStartReset);
                    }
                });
                add(cell);
            }
        }
    }

    public void setCellListener(CellListener cellListener, LabelStatus labelStatus, ButtonStartReset buttonStartReset) {
        this.cellListener = cellListener;
        this.labelStatus = labelStatus;
        this.buttonStartReset = buttonStartReset;
    }

    public void resetBoard(String resetVal) {
        Arrays.stream(getComponents())
                .map(component -> (TicTacToeCell) component)
                .forEach(cell -> cell.setText(resetVal));
    }

    public TicTacToeCell[][] getBoard() {
        return board;
    }
}
