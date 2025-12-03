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

  public int secondSolution() {
    int result = 0;
    int counter = 50;

    for (String i : inputs) {
      boolean isRight;
      isRight = i.charAt(0) == 'R';
      int moves = Integer.parseInt(i.substring(1));
      int additionalMoves = 0;
      if (moves > 100) {
        additionalMoves = additionalMoves + (moves / 100);
      }
      moves = moves % 100;
      if (isRight) {
        counter = counter + moves;
        if (counter > 99) {
          if (counter != 100) {
            additionalMoves++;
          }
          counter = counter - 100;
        }
      } else {
        boolean startsFrom0 = counter == 0;
        counter = counter - moves;
        if (counter < 0) {
          if (!startsFrom0) {
            additionalMoves++;
          }
          counter = 100 - Math.abs(counter);
        }
      }
      if (counter == 0) {
        additionalMoves++;
      }
      result = result + additionalMoves;
    }
    return result;
  }

  public int firstSolution() {
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
}
