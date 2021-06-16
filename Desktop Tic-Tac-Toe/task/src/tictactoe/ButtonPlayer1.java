package tictactoe;

import enums.PlayerMode;

import javax.swing.*;
import java.awt.*;

public class ButtonPlayer1 extends JButton {
    ButtonPlayer1() {
        setText(PlayerMode.HUMAN.getPlayerMode());
        setName("ButtonPlayer1");
        setFocusPainted(false);
        setFont(new Font("Roboto", Font.BOLD, 14));
    }
}
