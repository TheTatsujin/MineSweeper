package software.games.minesweeper.model;

import java.util.ArrayList;
import java.util.List;

public class BoardState {
    public final List<Cell> openedCells;

    public BoardState() {
        openedCells = new ArrayList<>();
    }
    public BoardState(List<Cell> cells) {
        openedCells = cells;
    }

    public boolean isOpen(Cell cell) {
        for (Cell c : openedCells) if (c == cell) return true;
        return false;
    }

    public BoardState nextState(List<Cell> openedCells) {
        return new BoardState();
    }
}
