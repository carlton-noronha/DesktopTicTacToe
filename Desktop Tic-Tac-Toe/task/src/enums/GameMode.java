package enums;

public enum GameMode {
    START("Start"),
    RESET("Reset");

    String gameMode;

    GameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameMode() {
        return gameMode;
    }
}
