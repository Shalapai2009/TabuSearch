import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Cargo> cargos = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            cargos.add(new Cargo(1 + (int) (Math.random() * (10))));
        }
        int maxIterations = 100;
        int tabuListSize = 10;

        List<Cargo> bestSolution = new TabuSearch(cargos,maxIterations,tabuListSize).tabuSearch();
        for (Cargo cargo: bestSolution) {
            System.out.println(cargo.getWeight());
        }
        System.out.println("byaka");
    }
}