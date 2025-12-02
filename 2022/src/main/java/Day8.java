import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day8 {

    class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    int[][] grid;
    Map<Pair, Pair> leftCache;
    Map<Pair, Pair> rightCache;
    Map<Pair, Pair> topCache;
    Map<Pair, Pair> bottomCache;

    public Day8(List<String> inputList) {
        grid = new int[inputList.size()][inputList.get(0).length()];
        int index = 0;
        for (String input : inputList) {
            for (int i = 0; i < input.length(); i++) {
                grid[index][i] = Integer.parseInt(input.charAt(i) + "");
            }
            index++;
        }
        leftCache = new HashMap<>();
        rightCache = new HashMap<>();
        topCache = new HashMap<>();
        bottomCache = new HashMap<>();
    }

    public int solveFirstPuzzle() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean isVisible = isVisible(i, j);
                if(isVisible)
                    count++;
            }
        }
        return count;
    }

    public int solveSecondPuzzle() {
        int maxScore = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                updateCache(i,j);
                int score = findScenicScore(i, j);
                maxScore = Math.max(maxScore, score);
            }
        }
        return maxScore;
    }

    private int findScenicScore(int i, int j) {
        Pair key = new Pair(i,j);

        int leftScore = j - leftCache.getOrDefault(key, new Pair(0,0)).y;
        int rightScore = rightCache.getOrDefault(key, new Pair(grid[0].length-1, grid[0].length-1)).y - j;
        int topScore = i - topCache.getOrDefault(key, new Pair(0,0)).x;
        int bottomScore = bottomCache.getOrDefault(key, new Pair(grid.length-1, grid.length-1)).x - i;

        return (leftScore * rightScore * topScore * bottomScore);
    }

    private void updateCache(int i, int j){
        isVisibleFromLeft(i, j-1, i, j);
        isVisibleFromRight(i, j+1, i, j);
        isVisibleFromTop(i-1, j, i, j);
        isVisibleFromBottom(i+1, j, i, j);
    }

    private boolean isVisible(int i, int j){
        if(i <= 0 || j <= 0 || i >= grid.length - 1 || j >= grid[0].length-1)
            return true;
        return isVisibleFromLeft(i, j-1, i, j) || isVisibleFromRight(i, j+1, i, j) || isVisibleFromTop(i-1, j, i, j) || isVisibleFromBottom(i+1, j, i, j);
    }

    private boolean isVisibleFromLeft(int i, int j, int originalI, int originalJ) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return true;
        Pair key = new Pair(i, j);
        if(grid[i][j] >= grid[originalI][originalJ]) {
            leftCache.put(new Pair(originalI,originalJ), key);
            return false;
        }

        if(leftCache.containsKey(key)){
            return isVisibleFromLeft(leftCache.get(key).x, leftCache.get(key).y, originalI, originalJ);
        }
        return isVisibleFromLeft(i, j-1, originalI, originalJ);
    }

    private boolean isVisibleFromRight(int i, int j, int originalI, int originalJ) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return true;
        Pair key = new Pair(i, j);
        if(grid[i][j] >= grid[originalI][originalJ]) {
            rightCache.put(new Pair(originalI,originalJ), key);
            return false;
        }

        if(rightCache.containsKey(key)){
            return isVisibleFromRight(rightCache.get(key).x, rightCache.get(key).y, originalI, originalJ);
        }
        return isVisibleFromRight(i, j+1, originalI, originalJ);
    }

    private boolean isVisibleFromTop(int i, int j, int originalI, int originalJ) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return true;
        Pair key = new Pair(i, j);
        if(grid[i][j] >= grid[originalI][originalJ]) {
            topCache.put(new Pair(originalI,originalJ), key);
            return false;
        }

        if(topCache.containsKey(key)){
            return isVisibleFromTop(topCache.get(key).x, topCache.get(key).y, originalI, originalJ);
        }
        return isVisibleFromTop(i-1, j, originalI, originalJ);
    }

    private boolean isVisibleFromBottom(int i, int j, int originalI, int originalJ) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return true;
        Pair key = new Pair(i, j);
        if(grid[i][j] >= grid[originalI][originalJ]) {
            bottomCache.put(new Pair(originalI,originalJ), key);
            return false;
        }

        if(bottomCache.containsKey(key)){
            return isVisibleFromBottom(bottomCache.get(key).x, bottomCache.get(key).y, originalI, originalJ);
        }
        return isVisibleFromBottom(i+1, j, originalI, originalJ);
    }


}
