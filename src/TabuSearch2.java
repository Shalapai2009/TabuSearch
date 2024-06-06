import java.util.ArrayList;
import java.util.List;

public class TabuSearch2 {
    // Define the objective function
    public static int objectiveFunction(List<Integer> solution) {
        // TODO: Implement your objective function here
        // The objective function should evaluate
        // the quality of a given solution and
        // return a numerical value representing
        // the solution's fitness
        // Example: return solution.stream().mapToInt(Integer::intValue).sum();
        return solution.stream().mapToInt(Integer::intValue).sum();
    }

    // Define the neighborhood function
    public static List<List<Integer>> getNeighbors(List<Integer> solution) {
        List<List<Integer>> neighbors = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            for (int j = i + 1; j < solution.size(); j++) {
                List<Integer> neighbor = new ArrayList<>(solution);
                // Swap elements at indices i and j
                int temp = neighbor.get(i);
                neighbor.set(i, neighbor.get(j));
                neighbor.set(j, temp);
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    // Define the Tabu Search algorithm
    public static List<Integer> tabuSearch(List<Integer> initialSolution, int maxIterations, int tabuListSize) {
        List<Integer> bestSolution = new ArrayList<>(initialSolution);
        List<Integer> currentSolution = new ArrayList<>(initialSolution);
        List<List<Integer>> tabuList = new ArrayList<>();

        for (int iter = 0; iter < maxIterations; iter++) {
            List<List<Integer>> neighbors = getNeighbors(currentSolution);
            List<Integer> bestNeighbor = new ArrayList<>();
            int bestNeighborFitness = Integer.MAX_VALUE;

            for (List<Integer> neighbor : neighbors) {
                if (!tabuList.contains(neighbor)) {
                    int neighborFitness = objectiveFunction(neighbor);
                    if (neighborFitness < bestNeighborFitness) {
                        bestNeighbor = new ArrayList<>(neighbor);
                        bestNeighborFitness = neighborFitness;
                    }
                }
            }

            if (bestNeighbor.isEmpty()) {
                // No non-tabu neighbors found, terminate the search
                break;
            }

            currentSolution = new ArrayList<>(bestNeighbor);
            tabuList.add(bestNeighbor);

            if (tabuList.size() > tabuListSize) {
                // Remove the oldest entry from the tabu list if it exceeds the size
                tabuList.remove(0);
            }

            if (objectiveFunction(bestNeighbor) < objectiveFunction(bestSolution)) {
                // Update the best solution if the current neighbor is better
                bestSolution = new ArrayList<>(bestNeighbor);
            }
        }

        return bestSolution;
    }

    public static void main(String[] args) {
        // Example usage
        // Provide an initial solution
        List<Integer> initialSolution = List.of(1, 2, 3, 4, 5);
        int maxIterations = 100;
        int tabuListSize = 10;

        List<Integer> bestSolution = tabuSearch(initialSolution, maxIterations, tabuListSize);
        System.out.print("Best solution:");
        for (int val : bestSolution) {
            System.out.print(" " + val);
        }
        System.out.println();
        System.out.println("Best solution fitness: " + objectiveFunction(bestSolution));
    }
}