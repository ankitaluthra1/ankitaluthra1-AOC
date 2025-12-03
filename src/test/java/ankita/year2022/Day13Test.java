package ankita.year2022;

import ankita.InputParser.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Day13Test {

    @Test
    public void shouldPassFirstTestCase() throws IOException {
        Day13 day13 = new Day13();
        FileReader fileReader = new FileReader();
        List<String> linesOfFiles = fileReader.readFile("/year2022/day13.txt");
        int output = day13.solveFirstPuzzle(linesOfFiles);

        System.out.println(output);
    }

}