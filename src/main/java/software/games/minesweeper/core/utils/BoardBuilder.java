package software.games.minesweeper.utils;

import software.games.minesweeper.core.model.Board;
import software.games.minesweeper.core.model.Cell;
import software.games.minesweeper.core.model.Content;

import java.util.Arrays;
import java.util.List;

public class BoardBuilder {
    private Board board;
    private Cell[][] cells;

    public BoardBuilder newBoard(int width, int height) {
        this.board = initializeBoard(width, height);
        return this;
    }

    public BoardBuilder addMinesAt(List<int[]> coordinates) {
        coordinates.forEach(coordinate -> addMineAt(coordinate[0], coordinate[1]));
        board = board.withCells(cells);
        return this;
    }

    public Board build() {
        return board.withCells(cells).withUpdatedNeighbors();
    }

    private Board initializeBoard(int width, int height) {
        cells = initializeCells(width, height);
        return new Board(width, height, cells.clone());
    }

    private Cell[][] initializeCells(int width, int height) {
        return Arrays.stream(new Cell[height][width])
                .map(cellRow -> Arrays.stream(cellRow)
                        .map(cell -> new Cell(Content.Hint, List.of()))
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);
    }


    private void addMineAt(int row, int column){
        cells[row][column] = board.cellAt(row, column).asMine();
    }

}
