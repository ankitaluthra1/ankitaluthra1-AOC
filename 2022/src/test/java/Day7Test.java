import InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day7Test {

    @Test
    public void shouldGivenOutputForFirstPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> inputList = fileReader.readFile("day7.txt");
        Day7 day7 = new Day7(inputList);
        long output = day7.solveFirstPuzzle();
        System.out.println(output);
    }

    @Test
    public void shouldGivenOutputForSecondPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> inputList = fileReader.readFile("day7.txt");
        Day7 day7 = new Day7(inputList);
        long output = day7.solveSecondPuzzle();
        System.out.println(output);
    }

}