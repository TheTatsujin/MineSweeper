package software.games.minesweeper.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MineRandomizer {
    private final int width;
    private final int height;
    private List<int[]> availablePositions;

    public MineRandomizer(int width, int height) {
        this.width = width;
        this.height = height;
        this.availablePositions = initializePositions();
    }

    public List<int[]> generateMineCoordinates(int numberOfMines) {
        if (availablePositions.size() < numberOfMines || availablePositions.isEmpty()) return new ArrayList<>();

        availablePositions = shufflePositions();
        return availablePositions.subList(0, numberOfMines);

    }

    private List<int[]> initializePositions() {
        return IntStream.range(0, height * width)
                .mapToObj(column -> new int[]{column / width, column % width})
                .collect(Collectors.toList());

    }

    private List<int[]> shufflePositions(){
        for (int tail = availablePositions.size() - 1; tail > 0; tail--) swapPositions(tail, new Random().nextInt(tail));
        return availablePositions;
    }

    private void swapPositions(int i, int j){
        int[] swapValue = availablePositions.get(i);
        availablePositions.set(i, availablePositions.get(j));
        availablePositions.set(j, swapValue);
    }

}
