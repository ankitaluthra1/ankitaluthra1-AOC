package InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<String> readFile(String name) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> lines = new ArrayList<>();

        while(true){
            String line = bufferedReader.readLine();
            if (line == null)
                break;
            lines.add(line);
        }

        return lines;
    }

}
