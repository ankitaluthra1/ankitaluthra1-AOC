package ankita.year2022;

import java.util.*;

public class Day9 {

    public int solveFirstPuzzle() {

        Coordinate head = new Coordinate(0, 0);
        Tail tail = new Tail(new Coordinate(0,0));

        for (Command c : commands) {
            for(int i= 0 ; i < c.steps; i++) {
                Coordinate newHead =  c.applyForOneStep(head);
                Coordinate newPos = moveTowards(tail.currentPosition, newHead);
                tail.moveTo(newPos);
                head = newHead;
            }
        }
        return tail.countMoves();
    }

    public int solveSecondPuzzle() {
        Coordinate head = new Coordinate(0, 0);
        List<Tail> tails = new ArrayList<>(9);

        for (int i = 0; i<9;i++){
            tails.add(new Tail(new Coordinate(0,0)));
        }

        for (Command c : commands) {
            for(int i= 0 ; i < c.steps; i++) {
                Coordinate newHead =  c.applyForOneStep(head);
                moveAllTails(newHead, tails);
                head = newHead;
            }
        }
        return tails.get(8).countMoves();

    }

    private void moveAllTails(Coordinate headPos, List<Tail> tails){

        Coordinate newPos = headPos;

        for (Tail tail : tails){
            Coordinate temp = moveTowards(tail.currentPosition, newPos);
            if(!tail.moveTo(temp))
                break;
            tail.moveTo(temp);
            newPos = temp;
        }

    }

    enum Direction {
        L, R, U, D
    }

    class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    class Tail {
        Coordinate currentPosition;
        Set<String> moves;

        public Tail(Coordinate currentPosition) {
            this.currentPosition = currentPosition;
            moves = new HashSet<>();
            moves.add(currentPosition.toString());
        }

        public boolean moveTo(Coordinate newPosition) {

            if(currentPosition.x == newPosition.x && currentPosition.y == newPosition.y)
                return false;
            currentPosition = newPosition;
            moves.add(newPosition.toString());
            return true;
        }

        public int countMoves() {
            return moves.size();
        }
    }

    class Command {
        Direction direction;
        int steps;

        public Command(String s) {
            direction = Direction.valueOf(s.split(" ")[0]);
            steps = Integer.parseInt(s.split(" ")[1]);
        }

        public Coordinate applyForOneStep(Coordinate pos) {
            Coordinate newPos;
            switch (this.direction){
                case U: newPos = new Coordinate(pos.x, pos.y + 1);break;
                case D: newPos = new Coordinate(pos.x, pos.y - 1);break;
                case L: newPos = new Coordinate(pos.x - 1, pos.y);break;
                case R: newPos = new Coordinate(pos.x + 1, pos.y);break;
                default: throw new IllegalArgumentException("incorrect direction");
            }
            return newPos;
        }
    }

    List<Command> commands;

    public Day9(List<String> inputList) {
        commands = new ArrayList<>();
        for (String s : inputList) {
            commands.add(new Command(s));
        }

    }

    private boolean isUpwards(Coordinate source, Coordinate destination) {
        return source.x == destination.x && source.y < destination.y;
    }

    private boolean isDownwards(Coordinate source, Coordinate destination) {
        return source.x == destination.x && source.y > destination.y;
    }

    private boolean isLeft(Coordinate source, Coordinate destination) {
        return source.y == destination.y && source.x > destination.x;
    }

    private boolean isRight(Coordinate source, Coordinate destination) {
        return source.y == destination.y && source.x < destination.x;
    }

    private Coordinate moveTowards(Coordinate source, Coordinate destination) {

        if (isAdjoining(source, destination))
            return source;

        if (isUpwards(source, destination)) {
            return moveUpward(source);
        }

        if (isDownwards(source, destination)) {
            return moveDownward(source);
        }

        if (isLeft(source, destination)) {
            return moveLeft(source);
        }

        if (isRight(source, destination)) {
            return moveRight(source);
        }

        return moveDiagonal(source, destination);

    }

    private Coordinate moveUpward(Coordinate source) {
        return new Coordinate(source.x, source.y + 1);
    }

    private Coordinate moveDownward(Coordinate source) {
        return new Coordinate(source.x, source.y - 1);
    }

    private Coordinate moveLeft(Coordinate source) {
        return new Coordinate(source.x - 1, source.y);
    }

    private Coordinate moveRight(Coordinate source) {
        return new Coordinate(source.x + 1, source.y);
    }

    private Coordinate moveDiagonal(Coordinate source, Coordinate destination) {
        int xSteps = destination.x - source.x > 0 ? -1 : 1;
        int ySteps = destination.y - source.y > 0 ? -1 : 1;
        return new Coordinate(source.x - xSteps, source.y - ySteps);
    }

    private boolean isAdjoining(Coordinate source, Coordinate destination) {
        return !(Math.abs(source.x - destination.x) > 1 || Math.abs(source.y - destination.y) > 1);
    }

}
