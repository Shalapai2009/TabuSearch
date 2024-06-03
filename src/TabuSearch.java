import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TabuSearch {
    List<Cargo> cargoes;
    List<Truck> trucks = new ArrayList<>();
    public TabuSearch(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public void fullTrucks() {
        while (cargoes.size() > 0) {
            Truck currentTruck = new Truck();
            int k = 0;
                while (k < cargoes.size()) {
                    if (currentTruck.getFreePlace() - cargoes.get(k).getWeight() >= 0) {
                        currentTruck.addCargo(cargoes.get(k));
                        cargoes.remove(k);
                    }

                k+=1;
            }
            trucks.add(currentTruck);
        }
    }
}
