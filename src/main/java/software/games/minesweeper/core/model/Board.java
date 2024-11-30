package software.games.minesweeper.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board implements Iterable<Cell> {
    private final int width;
    private final int height;

    private final Cell[][] cells;

    public Board(int width, int height, Cell[][] cells) {
        this.cells = cells;
        this.width = width;
        this.height = height;
    }

    public Board withCells(Cell[][] cells) {
        return new Board(width, height, cells.clone());
    }

    public Board withUpdatedNeighbors(){
        Cell[][] newCells = IntStream.range(0, height)
                .mapToObj(row -> IntStream.range(0, width)
                        .mapToObj(column -> cells[row][column].withNeighbors(findNeighbors(row, column)))
                        .toArray(Cell[]::new))
                .toArray(Cell[][]::new);
        return new Board(width, height, newCells);
    }



    public Cell cellAt(int row, int column) {
        return isOutOfBounds(row, column) ? null : cells[row][column];
    }

    public boolean isOutOfBounds(int row, int column) {
        return row < 0 || column >= width || column < 0 || row >= height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private List<Cell> findNeighbors(int row, int column) {
        int[] directions = {-1, 0, 1};
        List<Cell> neighbors = new ArrayList<>();
        for (int rowOffset : directions) {
            for (int columnOffset : directions) {
                if (rowOffset != 0 || columnOffset != 0) neighbors.add(this.cellAt(row + rowOffset, column + columnOffset));
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        return IntStream.range(0, height)
                .mapToObj(row -> IntStream.range(0, width)
                        .mapToObj(col -> cellAt(row, col).toString())
                        .collect(Collectors.joining(", "))
                )
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellIterator();
    }

    private class CellIterator implements Iterator<Cell> {
        private int currentRow;
        private int currentCol;

        public CellIterator() {
            currentRow = 0;
            currentCol = 0;
        }

        @Override
        public boolean hasNext() {
            return currentRow < height && currentCol < width;
        }

        public Cell next() {
            currentRow += ++currentCol / width;
            return cells[currentRow % Board.this.height][currentCol % Board.this.width];
        }
    }
}
