import InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day3Test {

    @Test
    public void shouldGivenOutputForFirstPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> input = fileReader.readFile("day3.txt");

        Day3 day3 = new Day3();
        long output = day3.solveFirstPuzzle(input);

        System.out.println(output);
    }

    @Test
    public void shouldGivenOutputForSecondPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> input = fileReader.readFile("day3.txt");

        Day3 day3 = new Day3();
        long output = day3.solveSecondPuzzle(input);

        System.out.println(output);
    }

}