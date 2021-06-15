package tictactoe;

import enums.CellStates;
import enums.GameStates;
import listeners.CellListener;
import listeners.ResetListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class TicTacToe extends JFrame implements CellListener, ResetListener {

    private static final int BOARD_SIZE = 3;
    private TicTacToeBoard board;
    private Boolean isFirstPlayersTurn;
    private int moveCount;
    private boolean winnerFound;

    public TicTacToe() {
        isFirstPlayersTurn = true;
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        board = new TicTacToeBoard(BOARD_SIZE);
        add(board, BorderLayout.CENTER);
        TicTacToeStatusBar statusBar = new TicTacToeStatusBar();
        statusBar.setResetListener(this);
        board.setCellListener(this, statusBar.getLabelStatus());
        add(statusBar, BorderLayout.SOUTH);
    }

    @Override
    public void onReset(LabelStatus LabelStatus) {
        LabelStatus.setText(GameStates.GAME_NOT_STARTED.getState());
        board.resetBoard(CellStates.CELL_EMPTY.getCellState());
        isFirstPlayersTurn = true;
        winnerFound = false;
        moveCount = 0;
    }

    @Override
    public void onCellClicked(ActionEvent actionEvent, LabelStatus labelStatus) {
        String playersMark;
        playersMark = (isFirstPlayersTurn) ? CellStates.CELL_X.getCellState() : CellStates.CELL_O.getCellState();
        TicTacToeCell cell = (TicTacToeCell) actionEvent.getSource();
        boolean isEmpty = Objects.equals(cell.getText(), CellStates.CELL_EMPTY.getCellState());
        // Mark the cell only if it has not been previously marked
        if (isEmpty && !winnerFound) {
            ++moveCount;
            cell.setText(playersMark);
            winnerFound = checkForWinner(cell, playersMark);
            if (winnerFound) {
                if (Objects.equals(playersMark, CellStates.CELL_X.getCellState())) {
                    labelStatus.setText(GameStates.X_WINS.getState());
                } else {
                    labelStatus.setText(GameStates.O_WINS.getState());
                }
            } else if (moveCount == BOARD_SIZE * BOARD_SIZE) { // Draw
                labelStatus.setText(GameStates.DRAW.getState());
            } else {
                labelStatus.setText(GameStates.GAME_IN_PROGRESS.getState());
                isFirstPlayersTurn = !isFirstPlayersTurn;
            }
        }
    }

    private boolean checkForWinner(TicTacToeCell cell, String playersMark) {

        String name = cell.getName(); // Name Format is as follows: Button[LetterNumber] eg: ButtonA1, ButtonC3
        String coordinates = name.substring(name.length() - 2);
        int yCoordinate = coordinates.charAt(0) - 'A';
        int xCoordinate = BOARD_SIZE - (coordinates.charAt(1) - '0');

        TicTacToeCell[][] boardLayout = board.getBoard();

        // Check Cols
        for (int i = 0; true; i++) {
            if (!Objects.equals(boardLayout[xCoordinate][i].getText(), playersMark))
                break;
            if (i == BOARD_SIZE - 1) {
                return true;
            }
        }

        //check row
        for (int i = 0; true; i++) {
            if (!Objects.equals(boardLayout[i][yCoordinate].getText(), playersMark))
                break;
            if (i == BOARD_SIZE - 1) {
                return true;
            }
        }

        //check diagonal
        if (xCoordinate == yCoordinate) {
            //we're on a diagonal
            for (int i = 0; true; i++) {
                if (!Objects.equals(boardLayout[i][i].getText(), playersMark))
                    break;
                if (i == BOARD_SIZE - 1) {
                    return true;
                }
            }
        }

        //check anti diagonal
        if (xCoordinate + yCoordinate == BOARD_SIZE - 1) {
            for (int i = 0; true; i++) {
                if (!Objects.equals(boardLayout[i][BOARD_SIZE - i - 1].getText(), playersMark))
                    break;
                if (i == BOARD_SIZE - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}