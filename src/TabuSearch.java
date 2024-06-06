import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class TabuSearch {
    List<Cargo> cargoes;

    List<List<Truck>> list = new ArrayList<>();
    public TabuSearch(List<Cargo> cargoes) {
        this.cargoes = new ArrayList<>(cargoes);

    }
    public void tabuTabu() {

    }
    public void createListTrucks(int i) {
        for (int j = 0; j < i; j++) {
            list.add(fullTrucks());
        }
    }
    public List<Truck> fullTrucks() {
        List<Cargo> currentCargoes = new ArrayList<>(cargoes);
        Collections.shuffle(currentCargoes);
        List<Truck> trucks = new ArrayList<>();
        while (currentCargoes.size() > 0) {
            Truck currentTruck = new Truck();
            int k = 0;
                while (k < currentCargoes.size()) {
                    if (currentTruck.getFreePlace() - currentCargoes.get(k).getWeight() >= 0) {
                        currentTruck.addCargo(currentCargoes.get(k));
                        currentCargoes.remove(k);
                    }

                k += 1;
            }
            trucks.add(currentTruck);
        }
    return trucks;
    }
}
