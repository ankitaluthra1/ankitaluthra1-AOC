import InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day8Test {

    @Test
    public void shouldGivenOutputForFirstPuzzle() throws IOException {
       FileReader fileReader = new FileReader();
       List<String> inputList = fileReader.readFile("day8.txt");
        Day8 day8 = new Day8(inputList);
       int output = day8.solveFirstPuzzle();
        System.out.println(output);
    }

    @Test
    public void shouldGivenOutputForSecondPuzzle() throws IOException {
        FileReader fileReader = new FileReader();
        List<String> inputList = fileReader.readFile("day8.txt");
        Day8 day8 = new Day8(inputList);

        int output2 = day8.solveSecondPuzzle();
        System.out.println(output2);
    }

}