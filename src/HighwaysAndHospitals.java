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
        int[] roots = new int[n + 1];
        // Case when building a hospital in every city is cheaper than building any highways
        if (hospitalCost <= highwayCost){
            return hospitalCost * n;
        }
        // Case when highway cost < hospital cost

        // Perform union find to get the right # of clusters
        for (int[] city : cities){
            int cityOne = city[0];
            int cityOneCopy = city[0];
            int cityTwo = city[1];
            int cityTwoCopy= city[1];
            // while the city is not its own root, aka while its root is positive
            while (roots[cityOne] > 0){
                // Identify root for city
                cityOne = roots[cityOne];
            }
            System.out.println("copy: " + cityOneCopy);
            System.out.print("current" + cityOne);
            // Path compression here:
            // Make the top root the root for all lower nodes
            while (roots[cityOneCopy] > 0){
                // Set the new root for each city that we traversed previously
                int tempRoot = roots[cityOneCopy];
                roots[cityOneCopy] = cityOne;
                cityOneCopy = tempRoot;
            }
            // While the city is not its own root, aka while its root is positive
            while (roots[cityTwo] > 0){
                // Identify root
                cityTwo = roots[cityTwo];
            }
            // Path compression here:
            // Make the top root the root for all lower nodes
            while (roots[cityTwoCopy] > 0){
                // Set the new root for each city that we traversed previously
                int tempRoot = roots[cityTwoCopy];
                roots[cityTwoCopy] = cityTwo;
                cityTwoCopy = tempRoot;
            }
            if (cityOne != cityTwo){
                // Weight balancing here:
                // Find the order of both roots
                int orderOne = roots[cityOne];
                int orderTwo = roots[cityTwo];
                // Set the root of the bigger tree as the root of the smaller tree
                if (orderOne < orderTwo){
                    // Updates the order of root 1 & root of root 2
                    roots[cityOne] += (orderTwo - 1);
                    roots[cityTwo] = cityOne;
                }
                else if (orderOne > orderTwo){
                    // Updates the order of root 2 & root of root 1
                    roots[cityTwo] += (orderOne - 1);
                    roots[cityOne] = cityTwo;
                }
            }
        }


            // find # of clusters:
            int numClusters = 0;

            for (int root : roots){
                // Since the roots with themselves as a root hold a negative value
                if (root < 0){
                    numClusters += 1;
                }
            }
            // Calculate cost
            totalCost += hospitalCost * numClusters + (n - numClusters)*highwayCost;

        return totalCost;
    }

}
