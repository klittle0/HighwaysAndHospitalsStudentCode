
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
        // List maps a root to each city (or its order, if the city is its own root)
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
            pathCompression(roots, rootOne, rootOneCopy);
            // While the city is not its own root, aka while its root is positive, identify its root
            while (roots[rootTwo] > 0){
                rootTwo = roots[rootTwo];
            }
            pathCompression(roots, rootTwo, rootTwoCopy);
            // If both cities/clusters are separate (aka don't share a root), unite them
            if (rootOne != rootTwo){
                // Implement weight balancing:
                union(roots, rootOne, rootTwo);
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

        // Return total cost
        return Long.valueOf(hospitalCost) * numClusters + (n - numClusters) * highwayCost;
    }

    // Turn the top root into the root for all lower nodes that were just traversed
    public static void pathCompression(int[] roots, int root, int rootCopy){
        // While the topmost root hasn't been reached, continue updating the root
        while (roots[rootCopy] > 0){
            int tempRoot = roots[rootCopy];
            roots[rootCopy] = root;
            rootCopy = tempRoot;
        }
    }

    // Set the root of the bigger tree as the root of the smaller tree
    public static void union(int[] roots, int rootOne, int rootTwo){
        // Find the order of both roots
        int orderOne = roots[rootOne];
        int orderTwo = roots[rootTwo];
        if (orderOne < orderTwo){
            // Update the order of root 1 & root of root 2
            roots[rootOne] += (orderTwo - 1);
            roots[rootTwo] = rootOne;
        }
        else {
            // Update the order of root 2 & root of root 1
            roots[rootTwo] += (orderOne - 1);
            roots[rootOne] = rootTwo;
        }
    }
}