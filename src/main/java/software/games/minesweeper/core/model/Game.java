package software.games.minesweeper.model;

public class Game {
    private final int id; // Auto generate
    private final Board board;

    public Game(int id, Board board) {
        this.board = board;
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public int getId() {
        return id;
    }

    public boolean isGameOver(Move move) {
        return board.cellAt(move.row(), move.column()).content().equals(Content.Mine);
    }
}
