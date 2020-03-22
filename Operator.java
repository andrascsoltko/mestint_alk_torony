import java.util.Arrays;

public class Operator {


    int[] downBasket; // levisz only
    int[] upBasket; //felvisz only

    int le1, le2, fel1, fel2;

    public Operator(int le1, int le2, int fel1, int fel2){
        this.le1=le1;
        this.le2 = le2;
        this.fel1 = fel1;
        this.fel2 = fel2;

        this.downBasket = new int[]{le1, le2};
        this.upBasket = new int[]{fel1, fel2};
    }



    public boolean isApplicable(State state) {

        int downBasketsum= State.getWeights()[downBasket[0]]+ State.getWeights()[downBasket[1]];
        int upBasketsum= State.getWeights()[upBasket[0]]+ State.getWeights()[upBasket[1]];

        boolean b = Math.abs(downBasketsum - upBasketsum) <= 6 && Math.abs(downBasketsum - upBasketsum) > 0;

        if(state.getPassengers()[downBasket[0]] == 'F' && state.getPassengers()[downBasket[1]] == 'F'
                && state.getPassengers()[upBasket[0]] == 'L' && state.getPassengers()[upBasket[1]] == 'L' )
        {
            return b;
        }

        if( (state.getPassengers()[downBasket[0]] == 'O' && state.getPassengers()[downBasket[1]] == 'F'
                || state.getPassengers()[downBasket[0]] == 'F' && state.getPassengers()[downBasket[1]] == 'O')
                && (state.getPassengers()[upBasket[0]] == 'O' && state.getPassengers()[upBasket[1]] == 'L'
                || state.getPassengers()[upBasket[0]] == 'L' && state.getPassengers()[upBasket[1]] == 'O') )
        {
            return b;
        }

        if(state.getPassengers()[downBasket[0]] == 'O' && state.getPassengers()[downBasket[1]] == 'F'
                && state.getPassengers()[upBasket[0]] == 'O' && state.getPassengers()[upBasket[1]] == 'O')
        {
            return Math.abs(downBasketsum - upBasketsum) == 30;
        }
        if(state.getPassengers()[downBasket[0]] == 'F' && state.getPassengers()[downBasket[1]] == 'O'
                && state.getPassengers()[upBasket[0]] == 'O' && state.getPassengers()[upBasket[1]] == 'O')
        {
            return Math.abs(downBasketsum - upBasketsum) == 30;
        }
        if(state.getPassengers()[downBasket[0]] == 'O' && state.getPassengers()[downBasket[1]] == 'O'
                && state.getPassengers()[upBasket[0]] == 'L' && state.getPassengers()[upBasket[1]] == 'O')
        {
            return Math.abs(downBasketsum - upBasketsum) == 30;
        }
        if(state.getPassengers()[downBasket[0]] == 'O' && state.getPassengers()[downBasket[1]] == 'O'
                && state.getPassengers()[upBasket[0]] == 'O' && state.getPassengers()[upBasket[1]] == 'L')
        {
            return Math.abs(downBasketsum - upBasketsum) == 30;
        }

        if((state.getPassengers()[downBasket[0]] == 'O' && state.getPassengers()[downBasket[1]] == 'F'
                || state.getPassengers()[downBasket[0]] == 'F' && state.getPassengers()[downBasket[1]] == 'O')
                && (state.getPassengers()[upBasket[0]] == 'L' && state.getPassengers()[upBasket[0]] == 'L')){

            return b;
        }

        if((state.getPassengers()[upBasket[0]] == 'O' && state.getPassengers()[upBasket[1]] == 'L'
                || state.getPassengers()[upBasket[0]] == 'L' && state.getPassengers()[upBasket[1]] == 'O')
                && (state.getPassengers()[downBasket[0]] == 'F' && state.getPassengers()[downBasket[0]] == 'F')){

            return b;
        }


        else return false;
    }

    public State apply(State state) {

        State uj = new State();

        uj.setPassengers(state.getPassengers()[0], 0);
        uj.setPassengers(state.getPassengers()[1], 1);
        uj.setPassengers(state.getPassengers()[2], 2);
        uj.setPassengers(state.getPassengers()[3], 3);
        uj.setPassengers(state.getPassengers()[4], 4);

        for(int member: this.downBasket){
            if(state.getPassengers()[member] == 'F')
            {
                uj.setPassengers('L',member);
                continue;
            }
            if(state.getPassengers()[member] == 'O'){
                uj.setPassengers('O',member);
                continue;
            }
        }

        for(int member: this.upBasket){
            if(state.getPassengers()[member] == 'L')
            {
                uj.setPassengers('F',member);
                continue;
            }
            if(state.getPassengers()[member] == 'O'){
                uj.setPassengers('O',member);
                continue;
            }
        }


        return uj;
    }

    @Override
    public String toString() {
        return "[downBasket=" + Arrays.toString(downBasket) +
                ", upBasket=" + Arrays.toString(upBasket) + "] ";
    }

}
