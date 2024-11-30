package software.games.minesweeper.model;

public record GameState(int gameID, int turn, Move move) {
    public GameState nextTurn(Move move){
        return new GameState(gameID, turn + 1, move);
    }
}
