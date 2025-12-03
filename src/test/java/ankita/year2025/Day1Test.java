package ankita.year2025;

import ankita.InputParser.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

class Day1Test {

  @Test
  public void shouldGivenOutputForFirstPuzzle() throws IOException {

    FileReader fileReader = new FileReader();
    List<String> input = fileReader.readFile("/year2025/day1.txt");

    ankita.year2025.Day1 day1 = new ankita.year2025.Day1(input);
    long output = day1.firstSolution();

    System.out.println(output);
  }

  @Test
  public void shouldGivenOutputForSecondPuzzle() throws IOException {

    FileReader fileReader = new FileReader();
    List<String> input = fileReader.readFile("/year2025/day1.txt");

    ankita.year2025.Day1 day1 = new ankita.year2025.Day1(input);
    long output = day1.secondSolution();

    System.out.println(output);
  }


}