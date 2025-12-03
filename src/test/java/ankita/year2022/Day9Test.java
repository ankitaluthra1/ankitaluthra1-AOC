package ankita.year2022;

import ankita.InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Day9Test {

    @Test
    public void shouldGivenOutputForFirstPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> inputList = fileReader.readFile("/year2022/day9.txt");
        Day9 day9 = new Day9(inputList);
        int output = day9.solveFirstPuzzle();
        System.out.println(output);
    }

    @Test
    public void shouldGivenOutputForSecondPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> inputList = fileReader.readFile("ankita/year2022/day9.txt");
        Day9 day9 = new Day9(inputList);
        int output = day9.solveSecondPuzzle();
        System.out.println(output);
    }

    @Test
    public void test() throws IOException {
        List<Integer> previous = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        List<List<Integer>> newPrevious = new ArrayList<>();
        newPrevious.add(previous);

        System.out.println(newPrevious.contains(list));
    }

    public int compareTo(double a, double b){
        int i = (int) (a - b);
        System.out.println(i);
        return i;
    }
}