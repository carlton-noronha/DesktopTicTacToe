package enums;

public enum PlayerMode {
    HUMAN("Human"),
    COMPUTER("Robot");

    String playerMode;

    PlayerMode(String playerMode) {
        this.playerMode = playerMode;
    }

    public String getPlayerMode() {
        return playerMode;
    }
}
