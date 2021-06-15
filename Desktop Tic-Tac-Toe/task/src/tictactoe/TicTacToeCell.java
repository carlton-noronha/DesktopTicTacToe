package tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToeCell extends JButton {

    TicTacToeCell(String name, String initVal) {
        setName(name);
        setText(initVal);
        setFocusPainted(false);
        setFont(new Font("Roboto", Font.BOLD, 48));
    }
}
