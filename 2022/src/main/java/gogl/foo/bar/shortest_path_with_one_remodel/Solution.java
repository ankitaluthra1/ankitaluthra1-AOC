package gogl.foo.bar.shortest_path_with_one_remodel;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Solution {
    static class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public List<Pair> getNeighbors() {
            List<Pair> neighbors = new ArrayList<>();
            if (i > 0)
                neighbors.add(new Pair(i - 1, j));
            if (i < (map.length - 1))
                neighbors.add(new Pair(i + 1, j));
            if (j > 0)
                neighbors.add(new Pair(i, j - 1));
            if (j < (map[0].length - 1))
                neighbors.add(new Pair(i, j + 1));
            return neighbors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i && j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    private static Pair destination;
    private static int[][] map;

    private static Map<String, Integer> cache;

    public static int solution(int[][] map2) {
        map = map2;

        int rowSize = map.length;
        int colSize = map[0].length;

        destination = new Pair(rowSize - 1, colSize -1);

        cache = new HashMap<>();

        int shortest = findShortest(new Pair(0, 0), new ArrayList<>(), true);
        System.out.println(shortest);

        return shortest;
    }

    private static int findShortest(Pair src, List<Pair> previousPass, boolean canRemodel) {

        String key = src.i + " - " + src.j + " - " + canRemodel;

        if (src.equals(destination))
            return 1;

        if (previousPass.contains(src))
            return Integer.MAX_VALUE;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int minPath = Integer.MAX_VALUE;

        List<Pair> previousPassNew = new ArrayList<>(previousPass);
        previousPassNew.add(src);

        if (canRemodel) {
            List<Pair> neighbors = src.getNeighbors().stream().filter(p -> !previousPassNew.contains(p)).collect(Collectors.toList());
            for (Pair neighbor : neighbors) {
                if (map[neighbor.i][neighbor.j] == 0)
                    minPath = Math.min(findShortest(neighbor, previousPassNew, true), minPath);
                else {
                    minPath = Math.min(findShortest(neighbor, previousPassNew, false), minPath);
                }
            }
        } else {
            List<Pair> nextDirections = getNextDirection(src, previousPass);
            for (Pair direction : nextDirections) {
                minPath = Math.min(findShortest(direction, previousPassNew, false), minPath);
            }
        }

        if (minPath == Integer.MAX_VALUE) {
            cache.put(key, minPath);
            return minPath;
        }

        cache.put(key, 1 + minPath);
        return 1 + minPath;
    }

    private static List<Pair> getNextDirection(Pair src, List<Pair> previousPass) {

        return src.getNeighbors().stream().filter(p -> !previousPass.contains(p))
                .filter(p -> map[p.i][p.j] == 0).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[][] map2 = {{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}};
        solution(map2);
    }
}