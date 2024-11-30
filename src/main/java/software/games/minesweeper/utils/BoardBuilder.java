package software.games.minesweeper.model;

import java.util.Random;

public class BoardBuilder {
    private int width;
    private int height;
    private Cell[][] cells;

    public BoardBuilder newBoard(int width, int height) {
        this.cells = new Cell[width][height];
        this.width = width;
        this.height = height;
        return this;
    }

    public BoardBuilder generateMines(int numberOfMines){
        // TODO
        return this;
    }

    public Board build() {
        return new Board(width, height, cells.clone());
    }


}
