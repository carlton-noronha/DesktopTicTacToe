package tictactoe;

import enums.PlayerMode;

import javax.swing.*;
import java.awt.*;

public class ButtonPlayer2 extends JButton {
    ButtonPlayer2() {
        setText(PlayerMode.HUMAN.getPlayerMode());
        setName("ButtonPlayer2");
        setFocusPainted(false);
        setFont(new Font("Roboto", Font.BOLD, 14));
    }
}
