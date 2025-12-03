package ankita.year2025;

import static java.nio.file.Files.readString;

import ankita.InputParser.FileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day1 {

  List<String> inputs;

  Day1(List<String> inputs) {
    this.inputs = inputs;
  }

  private int firstSolution() {
    int result = 0;
    int counter = 50;

    for (String i : inputs) {
      boolean isRight = false;
      isRight = i.charAt(0) == 'R';
      int moves = Integer.parseInt(i.substring(1));
      moves = moves % 100;
      if (isRight) {
        counter = counter + moves;
        if (counter > 99) {
          counter = counter - 100;
        }
      } else {
        counter = counter - moves;
        if (counter < 0) {
          counter = 100 - Math.abs(counter);
        }
      }
      if (counter == 0) {
        result++;
      }
    }
    return result;
  }

  public static void main(String[] args) throws IOException {


    List<String> inputs = new FileReader().readFile("/year2025/day1.txt");
    Day1 day1 = new Day1(inputs);
    System.out.println(day1.firstSolution());

  }
}
