package software.games.minesweeper.model;

import java.util.List;
import java.util.Objects;

public record Cell(Content content, List<Cell> neighbors) {

    public Cell asMine(){
        return new Cell(Content.Mine, neighbors);
    }

    public Cell withNeighbors(List<Cell> neighbors){
        return new Cell(content, neighbors);
    }

    public int surroundingMines(){
        return neighbors().stream()
                .filter(Objects::nonNull)
                .mapToInt(neighbor -> neighbor.content.equals(Content.Mine) ? 1 : 0)
                .sum();
    }

    @Override
    public String toString() {
        return (content.equals(Content.Mine) ? "X" : String.valueOf(surroundingMines()));
    }

    @Override
    public int hashCode() {
        return surroundingMines();
    }
}
