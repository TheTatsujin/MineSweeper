package software.games.minesweeper;

import software.games.minesweeper.core.model.*;
import software.games.minesweeper.utils.BoardBuilder;
import software.games.minesweeper.utils.MineRandomizer;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private int currentTurn;
    private final List<GameState> gameStates;
    private final List<BoardState> boardStates;
    private Game game;
    
    public GameController(){
        this.currentTurn = 0;
        this.gameStates = new ArrayList<>();
        this.boardStates = new ArrayList<>();
    }
    
    public GameController newGame(int boardWidth, int boardHeight, int numberOfMines){
        Board board = new BoardBuilder()
                .newBoard(boardWidth, boardHeight)
                .addMinesAt(new MineRandomizer(boardWidth, boardHeight).generateMineCoordinates(numberOfMines))
                .build();
        
        this.game = new Game(1, board);
        return this;
    }
    
    
    public Game getGame() {
        return game;
    }

    public GameState getCurrentGameState(){
        return gameStates.getLast();
    }

    public BoardState getCurrentBoardState(){
        return boardStates.getLast();
    }

    public List<Move> getMoveHistory(){
        return gameStates.stream()
                .map(GameState::move)
                .toList();
    }

    public List<List<Cell>> getBoardHistory(){
        return boardStates.stream()
                .map(BoardState::getOpenedCells)
                .toList();
    }

    public Move getLastMove(){
        return getCurrentGameState().move();
    }

    public Move getMoveAtTurn(int turn){
        if (currentTurn < turn || turn == 0) return null;
        return gameStates.get(turn + 1).move();
    }
    
    public GameController nextTurn(Move move){
        // TODO: Win / Lose / Ongoing game logic
        currentTurn++;
        if (game.isGameOver(move)) gameStates.add(gameStates.get(currentTurn).nextTurn(move, GameState.State.lost));
        return this;
    }
}
