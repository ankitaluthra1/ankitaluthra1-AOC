package ankita.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

  public List<String> readFile(String name) throws IOException {
    String srcDir = "src/main/resources/ankita";
    List<String> lines = Files.readAllLines(Path.of(srcDir +  name));

    return lines;
  }

}
