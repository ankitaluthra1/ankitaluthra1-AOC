package gogl.foo.bar.chess_board_knight_moves;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Solution {

    static class Coordinate {
        int i;
        int j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null) return false;
            Coordinate other = (Coordinate) object;
            return i == other.i && j == other.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }

        public List<Coordinate> validMoves() {
            List<Coordinate> coordinates = new ArrayList<>();
            {
             //   addValidCoordinates(coordinates, new Coordinate(i - 1, j));
                addValidCoordinates(coordinates, new Coordinate(i - 1, j - 2));
                addValidCoordinates(coordinates, new Coordinate(i - 1, j + 2));
            }

            {
                addValidCoordinates(coordinates, new Coordinate(i - 2, j - 1));
                addValidCoordinates(coordinates, new Coordinate(i - 2, j + 1));
            }

            {
            //    addValidCoordinates(coordinates, new Coordinate(i + 1, j));
                addValidCoordinates(coordinates, new Coordinate(i + 1, j - 2));
                addValidCoordinates(coordinates, new Coordinate(i + 1, j + 2));
            }

            {
                addValidCoordinates(coordinates, new Coordinate(i + 2, j - 1));
                addValidCoordinates(coordinates, new Coordinate(i + 2, j + 1));
            }


            return coordinates;
        }
    }

    private static Map<Coordinate, Integer> cache;

    public static int solution(int src, int dest) {
        cache = new HashMap<>();
        Coordinate source = getCoordinates(src);
        Coordinate destination = getCoordinates(dest);

        return findMinMoves(source, destination, new ArrayList<>());

    }

    private static void addValidCoordinates(List<Coordinate> coordinates, Coordinate coordinate) {
        if (isValidCoordinate(coordinate))
            coordinates.add(coordinate);
    }

    private static boolean isValidCoordinate(Coordinate coordinate) {
        if (coordinate.i < 0 || coordinate.i > 7 || coordinate.j < 0 || coordinate.j > 7)
            return false;
        return true;
    }

    private static Coordinate getCoordinates(int num) {
        return new Coordinate((num / 8), (num % 8));
    }

    private static int findMinMoves(Coordinate source, Coordinate destination, List<Coordinate> toBeIgnored) {
        if (toBeIgnored.contains(source))
            return Integer.MAX_VALUE;
        if (source.equals(destination))
            return 0;
        if (cache.containsKey(source))
            return cache.get(source);

        List<Coordinate> neighbors = source.validMoves();

        int minMoves = Integer.MAX_VALUE;

        List<Coordinate> toBeIgnoredNew = new ArrayList<>(toBeIgnored);
        toBeIgnoredNew.add(source);

        for (Coordinate neighbor : neighbors) {
            int minMovesTemp = findMinMoves(neighbor, destination, toBeIgnoredNew);
            if (minMovesTemp == Integer.MAX_VALUE)
                continue;
            minMoves = Math.min(minMoves, 1 + minMovesTemp);
        }

        cache.put(source, minMoves);

        return minMoves;
    }

    public static void main(String[] args) {
        int solution = solution( 19, 36);
        System.out.println(solution);
    }
}
