package enums;

public enum CellStates {
    CELL_EMPTY(" "),
    CELL_X("X"),
    CELL_O("O");

    private final String cellState;

    CellStates(String cellState) {
        this.cellState = cellState;
    }

    public String getCellState() {
        return cellState;
    }
}
