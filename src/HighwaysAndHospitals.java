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
        ArrayList<ArrayList<Integer>> numClusters = new ArrayList<ArrayList<Integer>>();
        // When building a hospital in every city is cheaper than building any highways
        if (hospitalCost <= highwayCost){
            // What does n represent?
            totalCost = hospitalCost * n;
        }
        // When highway cost < hospital cost
        else{
            for (int[] city : cities){
                for (ArrayList<Integer> cluster : numClusters){

                }

                // Check to see if either value 0 or 1 is already in any of numCluster's arraylists
                // If no, make a new arraylist in numClusters & add both city[0] and city[1] there
                // If yes, add the other value (not already in list) to that list
                System.out.println(city[0] + " and " + city[1]);
            }
            // Need to separate all nodes into # of clusters
            // Once I have # of clusters, cost of each cluster = cost of 1 hospital + cost of minimum # of highways
        }
        return 0;
    }




}
