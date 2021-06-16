package tictactoe;

import enums.GameMode;

import javax.swing.*;
import java.awt.*;

public class ButtonStartReset extends JButton {
    ButtonStartReset() {
        setText(GameMode.START.getGameMode());
        setName("ButtonStartReset");
        setFocusPainted(false);
        setFont(new Font("Roboto", Font.BOLD, 14));
    }
}
