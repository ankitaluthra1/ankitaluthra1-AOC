import java.util.List;
import java.util.Random;

public class Day4 {

    class Pair{
        public final int start;
        public final int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int diff(){
            return (end - start);
        }
    }

    class Assignment{

        Pair large;
        Pair small;

        public Assignment(Pair first, Pair second) {

            int firstPairDiff = first.diff();
            int secondPairDiff = second.diff();

            if (firstPairDiff >= secondPairDiff){
                large = first;
                small = second;
            }
            else {
                large = second;
                small = first;
            }
        }

        public boolean isCompletelyOverlapping(){
            return small.start >= large.start && small.end <= large.end;
        }

        public boolean isPartiallyOverlapping(){
            return (small.start >= large.start && small.start <= large.end) || (small.end <= large.end && small.end >= large.start);
        }
    }
    public int solveFirstPuzzle(List<String> input) {
            int count = 0;
        for(String s : input){
            String split1 = s.split(",")[0];
            String split2 = s.split(",")[1];
            Pair first = from(split1);
            Pair second = from(split2);
            Assignment assignment = new Assignment(first, second);
            if(assignment.isCompletelyOverlapping())
                count++;
        }
        return count;
    }

    public int solveSecondPuzzle(List<String> input) {
        int count = 0;
        for(String s : input){
            String split1 = s.split(",")[0];
            String split2 = s.split(",")[1];
            Pair first = from(split1);
            Pair second = from(split2);
            Assignment assignment = new Assignment(first, second);
            if(assignment.isPartiallyOverlapping())
                count++;
        }
        return count;
    }

    private Pair from(String input){
        String split1 = input.split("-")[0];
        String split2 = input.split("-")[1];
        return new Pair(Integer.parseInt(split1) , Integer.parseInt(split2));
    }
}
