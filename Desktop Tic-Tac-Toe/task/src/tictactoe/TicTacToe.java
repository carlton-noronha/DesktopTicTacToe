package tictactoe;

import enums.CellStates;
import enums.GameMode;
import enums.GameStates;
import enums.PlayerMode;
import listeners.CellListener;
import listeners.PlayerListener;
import listeners.StartResetListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class TicTacToe extends JFrame implements PlayerListener, CellListener, StartResetListener {

    private static final int BOARD_SIZE = 3;
    private TicTacToeBoard board;
    private Boolean isFirstPlayersTurn;
    private int moveCount;
    private boolean winnerFound;
    private TicTacToeCell[][] boardLayout;
    private String player1Type;
    private String player2Type;

    public TicTacToe() {
        isFirstPlayersTurn = true;
        moveCount = 0;
        player1Type = PlayerMode.HUMAN.getPlayerMode();
        player2Type = PlayerMode.HUMAN.getPlayerMode();
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
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
        TicTacToeToolbar toolbar = new TicTacToeToolbar();
        board.setCellListener(this, statusBar.getLabelStatus(), toolbar.getButtonStartReset());
        add(statusBar, BorderLayout.SOUTH);
        toolbar.setStartResetListener(this, statusBar.getLabelStatus());
        toolbar.setPlayerListener(this);
        add(toolbar, BorderLayout.NORTH);
        boardLayout = board.getBoard();
    }

    @Override
    public void onStartReset(LabelStatus labelStatus,
                             ActionEvent actionEvent,
                             ButtonPlayer1 player1,
                             ButtonPlayer2 player2) {
        ButtonStartReset buttonStartReset = (ButtonStartReset) actionEvent.getSource();
        if (Objects.equals(buttonStartReset.getText(),
                GameMode.START.getGameMode())) {
            labelStatus.setText(GameStates.GAME_IN_PROGRESS.getState());
            player1.setEnabled(false);
            player2.setEnabled(false);
            buttonStartReset.setText(GameMode.RESET.getGameMode());
            unFreezeBoard(true);
            startGame();
        } else {
            labelStatus.setText(GameStates.GAME_NOT_STARTED.getState());
            buttonStartReset.setText(GameMode.START.getGameMode());
            player1.setEnabled(true);
            player2.setEnabled(true);
            board.resetBoard(CellStates.CELL_EMPTY.getCellState());
            isFirstPlayersTurn = true;
            winnerFound = false;
            moveCount = 0;
        }
    }

    private void computerMove() {

        Random random = new Random();
        int x = random.nextInt(BOARD_SIZE);
        int y = random.nextInt(BOARD_SIZE);

        while (!Objects.equals(boardLayout[x][y].getText(), CellStates.CELL_EMPTY.getCellState())) {
            x = random.nextInt(BOARD_SIZE);
            y = random.nextInt(BOARD_SIZE);
        }

        boardLayout[x][y].doClick();
    }

    private void startGame() {
        if (player1Type.equals(PlayerMode.HUMAN.getPlayerMode()) &&
                player2Type.equals(PlayerMode.COMPUTER.getPlayerMode())) {
            if (!isFirstPlayersTurn) {
                computerMove();
            }
        } else if (player1Type.equals(PlayerMode.COMPUTER.getPlayerMode()) &&
                player2Type.equals(PlayerMode.HUMAN.getPlayerMode())) {
            if (isFirstPlayersTurn) {
                computerMove();
            }
        } else if (player1Type.equals(PlayerMode.COMPUTER.getPlayerMode()) &&
                player2Type.equals(PlayerMode.COMPUTER.getPlayerMode())) {
            computerMove();
        }
    }

    @Override
    public void onCellClicked(ActionEvent actionEvent, LabelStatus labelStatus, ButtonStartReset buttonStartReset) {
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
                unFreezeBoard(false);
            } else if (moveCount == BOARD_SIZE * BOARD_SIZE) { // Draw
                labelStatus.setText(GameStates.DRAW.getState());
                unFreezeBoard(false);
            } else {
                isFirstPlayersTurn = !isFirstPlayersTurn;
                startGame();
            }
        }
    }

    private void unFreezeBoard(Boolean freeze) {
        Arrays.stream(boardLayout)
                .flatMap(Arrays::stream)
                .forEach(cell -> cell.setEnabled(freeze) );
    }

    private boolean checkForWinner(TicTacToeCell cell, String playersMark) {

        int[] coordinates = getCoordinates(cell);

        int xCoordinate = coordinates[0];
        int yCoordinate = coordinates[1];

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

    private int[] getCoordinates(TicTacToeCell cell) {
        String name = cell.getName(); // Name Format is as follows: Button[LetterNumber] eg: ButtonA1, ButtonC3
        String coordinates = name.substring(name.length() - 2);
        int yCoordinate = coordinates.charAt(0) - 'A';
        int xCoordinate = BOARD_SIZE - (coordinates.charAt(1) - '0');
        return new int[]{xCoordinate, yCoordinate};
    }

    @Override
    public void onPlayerTypeSelected(ActionEvent actionEvent) {
        JButton playerButton = (JButton) actionEvent.getSource();
        // First Player Button
        if (playerButton.getClass().equals(ButtonPlayer1.class)) {
            // Change to Computer if Human
            if (Objects.equals(playerButton.getText(), PlayerMode.HUMAN.getPlayerMode())) {
                player1Type = PlayerMode.COMPUTER.getPlayerMode();
                playerButton.setText(PlayerMode.COMPUTER.getPlayerMode());
            } else { // Change to Human when Computer
                player1Type = PlayerMode.HUMAN.getPlayerMode();
                playerButton.setText(PlayerMode.HUMAN.getPlayerMode());
            }
        } else { // Second Player Button
            if (Objects.equals(playerButton.getText(), PlayerMode.HUMAN.getPlayerMode())) {
                player2Type = PlayerMode.COMPUTER.getPlayerMode();
                playerButton.setText(PlayerMode.COMPUTER.getPlayerMode());
            } else {
                player2Type = PlayerMode.HUMAN.getPlayerMode();
                playerButton.setText(PlayerMode.HUMAN.getPlayerMode());
            }
        }
    }
}