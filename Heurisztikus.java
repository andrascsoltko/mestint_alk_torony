import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Heurisztikus {


    private static double h(State state) {
        double value = 0;

        if(state.getPassengers()[0] == 'L'){
            value += 78;
        }
        if(state.getPassengers()[1] == 'L'){
            value += 42;
        }
        if(state.getPassengers()[2] == 'L'){
            value += 36;
        }
        if(state.getPassengers()[3] == 'L'){
            value += 30;
        }


        return value;
    }

    public static void main(String[] args) {
        State startState = new State();
        int lepesszam = 0;
        LinkedList<Operator> solutionOperators = new LinkedList<>();
        List<Operator> operators = Arrays.asList(

                new Operator(2, 4, 3, 4),
                new Operator(2, 4, 1, 4),
                new Operator(4, 4, 3, 4),
                new Operator(0, 4, 1, 3),
                new Operator(0, 4, 1, 2),
                new Operator(1, 3, 0, 4),
                new Operator(1, 2, 0, 4),
                new Operator(3, 4, 4, 4),
                new Operator(1, 4, 2, 4),
                new Operator(3, 4, 2, 4)
        );

        State actualState = startState;

        while(true){
            if(actualState.isGoal()) {
                break;
            }

            List<Operator> availableOperators = new LinkedList<>();

            for(int i = 0; i < operators.size(); i++) {
                if(operators.get(i).isApplicable(actualState)
                        &&  h(operators.get(i).apply(actualState)) > h(actualState))
                    availableOperators.add(operators.get(i));
            }

            if(availableOperators.size() > 0) {
                Operator operator = availableOperators.get(0);

                for(Operator o: availableOperators) {
                    if(h(o.apply(actualState)) > h(operator.apply(actualState))) {
                        operator = o;
                    }
                }
                for(int clear = 0; clear < 1000; clear++)
                {
                    System.out.println("\b") ;
                }
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                System.out.println(actualState);
                System.out.println("Választott operátor:");
                System.out.println(operator);
                solutionOperators.add(operator);
                System.out.println("Heurisztika: "+h(operator.apply(actualState)));

                actualState = operator.apply(actualState);
                System.out.println("Új állapot");
                System.out.println(actualState);
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                lepesszam ++;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
                break;
            }
        }

        if(actualState.isGoal()) {
            for(int clear = 0; clear < 1000; clear++)
            {
                System.out.println("\b") ;
            }
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("Találtunk megoldást.");

            System.out.println("Kezdőállapot:");
            System.out.println(startState);
            for(Operator o: solutionOperators){
               startState = o.apply(startState);
                System.out.println("Alkalmazott operátor:");
               System.out.println(o);
                System.out.println();
                System.out.println(startState);

            }
            System.out.println("Lépés szám: "+lepesszam);
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        }
        else {
            for(int clear = 0; clear < 1000; clear++)
            {
                System.out.println("\b") ;
            }
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("Sikertelen keresés.");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        }
    }

}
