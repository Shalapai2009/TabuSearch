import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //int RandomRib = (int) (Math.random() * (10));

        List<Cargo> cargos = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            cargos.add(new Cargo(1 + (int) (Math.random() * (10))));
        }

        TabuSearch tabuSearch1 = new TabuSearch(cargos);
        //tabuSearch1.fullTrucks();
        tabuSearch1.createListTrucks(1000);
        //TabuSearch tabuSearch2 = new TabuSearch(cargos);
        //tabuSearch2.fullTrucks();
        //tabuSearch2.listTrucks(10);
        //TabuSearch tabuSearch3 = new TabuSearch(cargos);
        //tabuSearch3.fullTrucks();
       // tabuSearch3.listTrucks(10);
       // cargos[0] = new Cargo((int) (Math.random() * (10)));
        System.out.println("byaka");
    }
}