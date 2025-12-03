package ankita.year2022;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

    File root;
    CommandParser commandParser;

    List<Long> fileSizeList;
    List<Long> deletableFileSizeList;
    Map<String, Long> map;

    public Day7(List<String> inputList) {
        commandParser = new CommandParser(inputList);
        root = commandParser.parse();
        fileSizeList = new ArrayList<>();
        deletableFileSizeList = new ArrayList<>();
        map = new HashMap<>();
    }

    public long solveFirstPuzzle() {
        root.getTotalSize();
        return  fileSizeList.stream().reduce(0L, (a, b) -> a + b);
    }

    public long solveSecondPuzzle() {
        root.getTotalSize();
        long minDeletionSize = 30000000 - (70000000 - map.get("/"));

       return deletableFileSizeList.stream().filter(f -> f >= minDeletionSize).mapToLong(v -> v).min().getAsLong();
    }

    class File {
        String name;
        List<File> files;
        File parent;
        long size;

        public File(String name, File parent) {
            this.name = name;
            this.parent = parent;
            this.files = new ArrayList<>();
            size = 0;
        }

        public File(String name, File parent, long size) {
            this.name = name;
            this.parent = parent;
            this.size = size;
            this.files = new ArrayList<>();
        }

        public void addFile(String name) {
            files.add(new File(name, this));
        }

        public void addFile(long size, String name) {
            files.add(new File(name, this, size));
        }

        public File get(String name) {
            return this.files.stream().filter(f -> f.name.equals(name)).findAny().get();
        }

        public boolean containsFile(String dirName) {
            return this.files.stream().map(f -> f.name).anyMatch(f -> f.equals(dirName));
        }

        public long getTotalSize(){
            long sum = 0;
            for(File file : files){
                sum = sum + file.size + file.getTotalSize();
            }
            if(sum <= 100000L){
                fileSizeList.add(sum);
            }
            if(sum <= 30000000L){
                deletableFileSizeList.add(sum);
            }
            map.put(this.name, sum);
            return sum;
        }
    }

    class CommandParser {
        List<String> inputList;

        public CommandParser(List<String> inputList) {
            this.inputList = inputList;
        }

        public File parse() {
            File root = new File("/", null);
            File currentPointer = root;
            int index = 0;
            while (index < inputList.size()) {
                String cmd = inputList.get(index);
                if (cmd.contains("$ cd")) {
                    String dirName = cmd.substring(5);
                    if (dirName.equals("/"))
                        currentPointer = root;
                    else {
                        if (dirName.equals("..")) {
                            currentPointer = currentPointer.parent;
                        } else {
                            if (!currentPointer.containsFile(dirName))
                                currentPointer.addFile(dirName);
                            currentPointer = currentPointer.get(dirName);
                        }
                    }
                    index++;
                    continue;
                }
                if (cmd.contains("$ ls")) {
                    index++;
                    while (index < inputList.size() && !inputList.get(index).contains("$")) {
                        String curr = inputList.get(index);
                        if (curr.startsWith("dir")) {
                            currentPointer.addFile(curr.split(" ")[1]);
                        } else {
                            currentPointer.addFile(Integer.parseInt(curr.split(" ")[0]), curr.split(" ")[1]);
                        }
                        index++;
                    }
                    continue;
                }
                throw new IllegalArgumentException("Unable to parse " + cmd);
            }

            return root;
        }

    }
}


