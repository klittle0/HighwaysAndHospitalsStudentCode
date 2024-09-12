import java.util.ArrayList;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Kate Little
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        long totalCost = 0;
        ArrayList<ArrayList<Integer>> cityClusters = new ArrayList<ArrayList<Integer>>();
        // Case when building a hospital in every city is cheaper than building any highways
        if (hospitalCost <= highwayCost){
            // What does n represent? I'm assuming it's the number of cities
            totalCost = hospitalCost * n;
        }
        // Case when highway cost < hospital cost
        else{
            // Go through every city
            for (int[] city : cities){
                //
                boolean addedToCluster = false;
                for (ArrayList<Integer> cluster : cityClusters){
                    // If it contains 1 of the 2 listed, cities, add the other
                    if (cluster.contains(city[0]) && !cluster.contains(city[1])){
                        cluster.add(city[1]);
                        addedToCluster = true;
                        break;
                    }
                    else if (cluster.contains(city[1]) && !cluster.contains(city[0])){
                        cluster.add(city[0]);
                        addedToCluster = true;
                        break;
                    }
                    // If both have already been added to the cluster
                    else if (cluster.contains(city[1]) && cluster.contains(city[0])){
                        addedToCluster = true;
                    }
                }
                // If none of the existing clusters contain either city, make a new cluster & add both cities there
                if (!addedToCluster){
                    ArrayList<Integer> newCluster = new ArrayList<Integer>();
                    newCluster.add(city[0]);
                    newCluster.add(city[1]);
                    cityClusters.add(newCluster);
                }
            }
            // TEMPORARILY prints out all clusters for test
            for (ArrayList<Integer> cluster : cityClusters){
                System.out.println(cluster);
            }

            // Calculates cost
            int numClusters = cityClusters.size();
            totalCost = hospitalCost * numClusters;
            for (ArrayList<Integer> cluster : cityClusters){
                // Adds the cost of highways per cluster;
                totalCost += (cluster.size() - 1)*highwayCost;
            }
        }
        return totalCost;
    }




}
