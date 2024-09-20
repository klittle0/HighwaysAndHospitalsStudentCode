
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

    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        // List tracks each city & its root (or its order, if it's its own root)
        int[] roots = new int[n + 1];
        // Case when hospital cost < highway cost
        if (hospitalCost <= highwayCost){
            return Long.valueOf(hospitalCost) * n;
        }
        // Case when highway cost < hospital cost
        // Perform union find to get the right # of clusters
        for (int[] city : cities){
            int rootOne = city[0];
            int rootOneCopy = city[0];
            int rootTwo = city[1];
            int rootTwoCopy= city[1];
            // while the city is not its own root, aka while its root is positive, identify its root
            while (roots[rootOne] > 0){
                rootOne = roots[rootOne];
            }
            // Path compression here:
            // Make the top root the root for all lower nodes that were just traversed
            while (roots[rootOneCopy] > 0){
                int tempRoot = roots[rootOneCopy];
                roots[rootOneCopy] = rootOne;
                rootOneCopy = tempRoot;
            }
            // While the city is not its own root, aka while its root is positive, identify its root
            while (roots[rootTwo] > 0){
                rootTwo = roots[rootTwo];
            }
            // Path compression here:
            // Make the top root the root for all lower nodes
            while (roots[rootTwoCopy] > 0){
                // Set the new root for each city that we traversed previously
                int tempRoot = roots[rootTwoCopy];
                roots[rootTwoCopy] = rootTwo;
                rootTwoCopy = tempRoot;
            }
            // Unite both cities/clusters if they are separate
            if (rootOne != rootTwo){
                // Weight balancing here:
                // Find the order of both roots
                int orderOne = roots[rootOne];
                int orderTwo = roots[rootTwo];
                // Set the root of the bigger tree as the root of the smaller tree
                if (orderOne < orderTwo){
                    // Updates the order of root 1 & root of root 2
                    roots[rootOne] += (orderTwo - 1);
                    roots[rootTwo] = rootOne;
                }
                else {
                    // Updates the order of root 2 & root of root 1
                    roots[rootTwo] += (orderOne - 1);
                    roots[rootOne] = rootTwo;
                }
            }
        }
        // Find # of clusters:
        int numClusters = 0;
        for (int i = 1; i < n + 1; i++){
            // Since the nodes with themselves as a root hold a negative value
            if (roots[i] <= 0){
                numClusters += 1;
            }
        }

        // Calculate cost
        return Long.valueOf(hospitalCost) * numClusters + (n - numClusters) * highwayCost;
    }

    public static void unionFind(){

    }
    public static void pathCompression(int[] roots, int rootCopy, int root){
        // Make the top root the root for all lower nodes that were just traversed
        while (roots[rootCopy] > 0){
            int tempRoot = roots[rootCopy];
            roots[rootCopy] = root;
            rootCopy = tempRoot;
        }
    }

}

