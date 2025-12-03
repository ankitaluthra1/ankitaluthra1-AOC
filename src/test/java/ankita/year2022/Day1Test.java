package ankita.year2022;

import ankita.InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day1Test {

    @Test
    public void shouldGivenOutputForFirstPuzzle() throws IOException {

        FileReader fileReader = new FileReader();
        List<String> input = fileReader.readFile("/year2022/day1.txt");

        Day1 day1 = new Day1();
        long output = day1.solveFirstPuzzle(input);

        System.out.println(output);
    }

    @Test
    public void shouldGivenOutputForSecondPuzzle() throws IOException {

        FileReader fileReader = new FileReader();
        List<String> input = fileReader.readFile("ankita/year2022/day1.txt");

        Day1 day1 = new Day1();
        long output = day1.solveSecondPuzzle(input);

        System.out.println(output);
    }


}