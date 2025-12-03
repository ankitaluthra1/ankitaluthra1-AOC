package ankita.year2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1 {

    class CaloriePerElf implements Comparable<CaloriePerElf>{
        int elf;
        Long calories;

        public CaloriePerElf(int elf, long calories) {
            this.elf = elf;
            this.calories = calories;
        }

        @Override
        public int compareTo(CaloriePerElf o) {
            return o.calories.compareTo(this.calories);
        }
    }

    public long solveFirstPuzzle(List<String> input) {
        List<CaloriePerElf> caloriePerElfMap = parseInput(input);
        List<CaloriePerElf> sortedList = caloriePerElfMap.stream().sorted().collect(Collectors.toList());
        return sortedList.get(0).calories;
    }

    public long solveSecondPuzzle(List<String> input) {
        List<CaloriePerElf> caloriePerElfMap = parseInput(input);
        List<CaloriePerElf> sortedList = caloriePerElfMap.stream().sorted().collect(Collectors.toList());
        long calories = 0;
        calories = calories + sortedList.get(0).calories +
                sortedList.get(1).calories+
                sortedList.get(2).calories;

        return calories;
    }

    private List<CaloriePerElf> parseInput(List<String> input) {
        int counter = 1;
        long calCount = 0;
        List<CaloriePerElf> caloriePerElfList = new ArrayList<>();
        for(String line : input){
            if(line.trim().equals("")) {
                caloriePerElfList.add(new CaloriePerElf(counter, calCount));
                counter++;
                calCount = 0;
            }
            else {
                int calories = Integer.parseInt(line);
                calCount = calCount + calories;
            }
        }
        caloriePerElfList.add(new CaloriePerElf(counter, calCount));
        return caloriePerElfList;
    }
}
