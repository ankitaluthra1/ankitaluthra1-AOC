import InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day4Test {

    @Test
    public void shouldGivenOutputForFirstPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> input = fileReader.readFile("day4.txt");

        Day4 day4 = new Day4();
        long output = day4.solveFirstPuzzle(input);

        System.out.println(output);
    }

    @Test
    public void shouldGivenOutputForSecondPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> input = fileReader.readFile("day4.txt");

        Day4 day4 = new Day4();
        long output = day4.solveSecondPuzzle(input);

        System.out.println(output);
    }

}