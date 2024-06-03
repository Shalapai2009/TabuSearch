import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //int RandomRib = (int) (Math.random() * (10));

        List<Cargo> cargos = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            cargos.add(new Cargo(1 + (int) (Math.random() * (10))));
        }

        TabuSearch tabuSearch = new TabuSearch(cargos);
        tabuSearch.fullTrucks();
       // cargos[0] = new Cargo((int) (Math.random() * (10)));
        System.out.println("byaka");
    }
}