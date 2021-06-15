package tictactoe;

import enums.CellStates;
import listeners.CellListener;

import javax.swing.*;
import java.awt.*;

public class TicTacToeBoard extends JPanel {

    CellListener cellListener;
    int boardSize;
    private LabelStatus labelStatus;
    private final TicTacToeCell[][] board;

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
                cell.addActionListener(actionEvent -> {
                    if (cellListener != null && labelStatus != null) {
                        cellListener.onCellClicked(actionEvent, labelStatus);
                    }
                });
                add(cell);
            }
        }
    }

    public void setCellListener(CellListener cellListener, LabelStatus labelStatus) {
        this.cellListener = cellListener;
        this.labelStatus = labelStatus;
    }

    public void resetBoard(String resetVal) {
        for (Component component : getComponents()) {
            ((TicTacToeCell) component).setText(resetVal);
        }
    }

    public TicTacToeCell[][] getBoard() {
        return board;
    }
}
