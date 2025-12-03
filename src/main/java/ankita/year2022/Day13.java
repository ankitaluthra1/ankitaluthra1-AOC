package ankita.year2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    private boolean isInstanceOfInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private int isValid(String left, String right) {
        if (isInstanceOfInteger(left) && isInstanceOfInteger(right))
            return ((Integer) (Integer.parseInt(left))).compareTo(Integer.parseInt(right));

        if (isInstanceOfInteger(left)) {
            Integer rightInt = getFirstElement(right);
            if (rightInt == null)
                return 1;
            return rightInt.compareTo(Integer.parseInt(left));
        }

        if (isInstanceOfInteger(right)) {
            Integer leftInt = getFirstElement(left);
            if (leftInt == null)
                return -1;
            return leftInt.compareTo(Integer.parseInt(right));
        }
        System.out.println(left + "         " +right);
        List<String> leftList = Arrays.asList(left.substring(1, left.length() - 1).split(","));
        List<String> rightList = Arrays.asList(right.substring(1, right.length() - 1).split(","));

        int index = 0;

        while (index < leftList.size() && index < rightList.size()) {
            int tempResult = isValid(leftList.get(index), rightList.get(index));
            if (tempResult != 0)
                return tempResult;
            index++;
        }
        if (index == leftList.size() && index == rightList.size())
            return 0;
        if (index == leftList.size())
            return -1;
        return 1;
    }

    private Integer getFirstElement(String s) {
        if (s.length() == 0)
            return null;
        if (isInstanceOfInteger(s))
            return Integer.parseInt(s);

        List<String> tempList = Arrays.asList(s.substring(1, s.length() - 1).split(","));

        if (tempList.size() == 0)
            return null;

        return getFirstElement(tempList.get(0));
    }

    public int solveFirstPuzzle(List<String> inputList) {

        List<Integer> indexList = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < inputList.size(); i += 3) {

            String leftString = inputList.get(i);
            String rightString = inputList.get(i + 1);
            System.out.println("Comparing: " + leftString + "     --     " + rightString);
            int result = isValid(leftString, rightString);
            System.out.println(result);
            if (result == -1)
                indexList.add(index);

            index++;
        }
        return indexList.stream().mapToInt(i -> i).sum();
    }

}
