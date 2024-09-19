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
        // List to track each city & its root
        int[] roots = new int[n];
        // Case when building a hospital in every city is cheaper than building any highways
        if (hospitalCost <= highwayCost){
            // What does n represent? I'm assuming it's the number of cities
            totalCost = hospitalCost * n;
        }
        // Case when highway cost < hospital cost
        else{
            // Perform union find to get the right # of clusters

            // If I set them to 0, will this cause issues??
            int rootOne = 0;
            int rootTwo = 0;
            for (int[] city : cities){
                rootOne = city[0];
                // while the city is not its own root
                while (roots[city[0]] != city[0]){
                    // Identify root for city
                    rootOne = roots[city[0]];
                }
                // While the city is not its own root
                while (roots[city[1]] != city[1]){
                    rootTwo = roots[city[1]];
                    // Path Compression here:
                    int temp = rootTwo;
                    roots[city[1]] = temp;



                }
                if (rootOne != rootTwo){
                    // Weight balancing here:
                    // Find the order of both roots
                    int orderOne = roots[rootOne];
                    int orderTwo = roots[rootTwo];
                    // Set the root of the bigger tree as the root of the smaller tree
                    if (orderOne > orderTwo){
                        // Instead of roots[rootOne], could I just say orderOne??
                        roots[rootTwo] = roots[rootOne];
                    }

                }
                // After weight balancing, then add the proper values to the roots[] array

            }



            // Go through every city
            for (int i = 0; i < n; i++){
                // Get the root of each city in pair
                int root1 = cityClusters[cities[i][0]];
                int root2 = cityClusters[cities[i][1]];
                // if the roots are equal, do nothing. If they're not equal, set the topmost root = root1
                if (root1 != root2){

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
