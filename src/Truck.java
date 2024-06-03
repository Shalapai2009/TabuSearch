import java.util.ArrayList;
import java.util.List;

public class Truck {
    private int maxWeight = 10;
    private List<Cargo> cargoes = new ArrayList<>();
    private int freePlace = maxWeight;

    public void addCargo(Cargo cargo) {
        cargoes.add(cargo);
        freePlace-=cargo.getWeight();
    }

    public void setCargoes(List<Cargo> cargoes) {
        this.cargoes = cargoes;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getFreePlace() {
        return freePlace;
    }

    public List<Cargo> getCargoes() {
        return cargoes;
    }
}
