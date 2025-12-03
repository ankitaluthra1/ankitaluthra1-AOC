package ankita.year2022;

import java.util.*;

public class Day3 {

    class Rucksack {

        String first;
        String last;

        public Rucksack(String input) {
            int mid = input.length() / 2;
            first = input.substring(0, mid);
            last = input.substring(mid);
        }

        public Character commonItem() {
            Map<Character, Integer> firstHalfCount = new HashMap<>();
            for (Character c : first.toCharArray()) {
                int count = firstHalfCount.getOrDefault(c, 0);
                firstHalfCount.put(c, count + 1);
            }

            Map<Character, Integer> lastHalfCount = new HashMap<>();
            for (Character c : last.toCharArray()) {
                int count = lastHalfCount.getOrDefault(c, 0);
                lastHalfCount.put(c, count + 1);
            }

            for (Character c : firstHalfCount.keySet()) {
                if (lastHalfCount.containsKey(c))
                    return c;
            }
            throw new IllegalStateException("No same characters found in both halves");
        }

        public Set<Character> uniqueChars() {
            Set<Character> set = new HashSet<>();
            for (Character c : first.toCharArray()) {
                set.add(c);
            }
            for (Character c : last.toCharArray()) {
                set.add(c);
            }
            return set;
        }
    }

    public long solveFirstPuzzle(List<String> input) {
        long totalPriority = 0;
        for (String s : input) {
            Rucksack rucksack = new Rucksack(s);
            Character commonItem = rucksack.commonItem();
            int currentPriority = getPriority(commonItem);
            totalPriority = totalPriority + currentPriority;
        }
        return totalPriority;
    }

    private int getPriority(Character commonItem) {
        int currentPriority = commonItem - 'a' + 1;
        if (commonItem >= 65 && commonItem <= 90) {
            currentPriority = commonItem - 'A' + 1;
            currentPriority = currentPriority + 26;
        }
        return currentPriority;
    }

    public long solveSecondPuzzle(List<String> input) {
        int index = 0;
        int totalPriority = 0;
        while (true) {
            int index2 = index + 1;
            int index3 = index + 2;
            Rucksack rucksack1 = new Rucksack(input.get(index));
            Set<Character> set1 = rucksack1.uniqueChars();

            Rucksack rucksack2 = new Rucksack(input.get(index2));
            Set<Character> set2 = rucksack2.uniqueChars();

            Rucksack rucksack3 = new Rucksack(input.get(index3));
            Set<Character> set3 = rucksack3.uniqueChars();

            Character commonCharacter = commonCharacter(set1, set2, set3);

            totalPriority = totalPriority + getPriority(commonCharacter);

            index = index3 + 1;
            if (index == input.size())
                break;
        }
        return totalPriority;
    }

    private Character commonCharacter(Set<Character> set1, Set<Character> set2, Set<Character> set3) {

        for (Character c : set1) {
            if (set2.contains(c)) {
                if (set3.contains(c))
                    return c;
            }
        }
        throw new IllegalStateException("No same characters found in three lines");
    }
}
