import java.util.ArrayList;
import java.util.List;

public class TabuSearch {
    List<Cargo> cargos;
    int maxIterations;
    int tabuListSize;
    public TabuSearch(List<Cargo> cargos, int maxIterations, int tabuListSize) {
        this.cargos = new ArrayList<>(cargos);
        this.maxIterations = maxIterations;
        this.tabuListSize = tabuListSize;
    }
    public List<Cargo> tabuSearch() {
        List<Cargo> bestSolution = new ArrayList<>(cargos);
        List<Cargo> currentSolution = new ArrayList<>(cargos);
        List<List<Cargo>> tabuList = new ArrayList<>();

        for (int i = 0; i < maxIterations; i++) {
            List<List<Cargo>> neighbors = getNeighbors(currentSolution);
            List<Cargo> bestNeighbor = new ArrayList<>();
            int bestNeighborFitness = Integer.MAX_VALUE;

            for (List<Cargo> neighbor : neighbors) {
                if (!tabuList.contains(neighbor)) {
                    int neighborFitness = fullTrucksInOrderOfAdmission(neighbor);
                    if (neighborFitness < bestNeighborFitness) {
                        bestNeighbor = new ArrayList<>(neighbor);
                        bestNeighborFitness = neighborFitness;
                    }
                }
            }

            if (bestNeighbor.isEmpty()) {
                // все в табу, закрываем ворота
                break;
            }

            currentSolution = new ArrayList<>(bestNeighbor);
            tabuList.add(bestNeighbor);

            if (tabuList.size() > tabuListSize) {
                // Слишком много табу, убираем самый первый попавшийся
                tabuList.remove(0);
            }

            if (fullTrucksInOrderOfAdmission(bestNeighbor) < fullTrucksInOrderOfAdmission(bestSolution)) {
                // обновим лучшего, лучший тот, кто меньше всего занял грузовиков
                bestSolution = new ArrayList<>(bestNeighbor);
            }

        }
        return bestSolution;
    }

    public static List<List<Cargo>> getNeighbors(List<Cargo> solution) {
        List<List<Cargo>> neighbors = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            for (int j = i + 1; j < solution.size(); j++) {
                List<Cargo> neighbor = new ArrayList<>(solution);
                // Swap elements at indices i and j
                Cargo temp = neighbor.get(i);
                neighbor.set(i, neighbor.get(j));
                neighbor.set(j, temp);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }
    private int fullTrucks(List<Cargo> listCargo) {
        List<Cargo> currentCargoes = listCargo;
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
        return trucks.size();
    }
    private int fullTrucksInOrderOfAdmission(List<Cargo> listCargo) {
        List<Cargo> currentCargoes = listCargo;
        List<Truck> trucks = new ArrayList<>();
        Truck currentTruck = new Truck();
        boolean switcher = false;
            for (Cargo cargo: currentCargoes) {
                switcher = false;
                if (currentTruck.getFreePlace() - cargo.getWeight() >= 0) {
                    currentTruck.addCargo(cargo);
                }
                else {
                    switcher = true;
                     trucks.add(currentTruck);
                     currentTruck = new Truck();
                     currentTruck.addCargo(cargo);
                }
            }
            if (switcher) {
                trucks.add(currentTruck);
            }
        return trucks.size();
    }


}
