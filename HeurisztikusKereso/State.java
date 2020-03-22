import java.util.Arrays;

public class State {



    char[] passengers = new char[5];
    static int[] Weights= new int[] {78, 42, 36, 30, 0};



    public State() {
        this.passengers[0] = 'F';
        this.passengers[1] = 'F';
        this.passengers[2] = 'F';
        this.passengers[3] = 'L';
        this.passengers[4] = 'O';
    }

    public boolean isGoal() {
        return  this.passengers[0] == 'L' && this.passengers[1] == 'L' && this.passengers[2] == 'L';

    }

    public char[] getPassengers() {
        return passengers;
    }

    public void setPassengers(char position, int index) {
        passengers[index] = position;
    }

    public static int[] getWeights() {
        return Weights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return Arrays.equals(getPassengers(), state.getPassengers());
    }

    @Override
    public String toString() {
        return "State{" +
                "Passengers=" + Arrays.toString(passengers) +
                '}';
    }

    public String toString2() {
        return Arrays.toString(passengers);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(passengers);
    }
}
