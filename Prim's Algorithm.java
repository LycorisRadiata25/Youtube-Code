package prim;

import java.util.ArrayList;

public class Main {

	public static double mst(double[][] connections) {
		ArrayList<Integer> visitedNodesArray = new ArrayList<Integer>();
		ArrayList<Integer> unvisitedNodesArray = new ArrayList<Integer>();
		double totalDistance = 0;

		//Key is used to make the output more human friendly
		String[] key = {"A","B","C","D","E","F"};

		//Add the node you wish to begin with
		visitedNodesArray.add(0);

		//Loops through all of the connections except the starting one and adds them to the unvisited array
		for (int i = 1; i < connections.length; i++) {
			unvisitedNodesArray.add(i);
		}

		//Loops until all the nodes have been visited
		while (unvisitedNodesArray.size() != 0) {
			double currentMinConnection = Double.MAX_VALUE;
			int currentMinNodeUnvisited = 0;
			int currentMinNodeVisited = 0;

			//Loops through all the visited nodes and checks all of their non-zero connections with the unvisited nodes until it finds the minimum
			for (int c = 0; c < visitedNodesArray.size(); c++) {
				int visitedNode = visitedNodesArray.get(c);
				for (Integer unvisitedNode : unvisitedNodesArray) {
					double connection = connections[visitedNode][unvisitedNode];
					if (connection < currentMinConnection && connection != 0) {
						currentMinConnection = connection;
						currentMinNodeUnvisited = unvisitedNode;
						currentMinNodeVisited = visitedNode;
					}
				}
			}

			//Adds the node with the minimum connection to the visited nodes and removes it from the unvisited nodes
			visitedNodesArray.add(currentMinNodeUnvisited);
			unvisitedNodesArray.remove(unvisitedNodesArray.indexOf(currentMinNodeUnvisited));
			totalDistance += currentMinConnection;

			//Prints the connection just made
			System.out.println("Connection made between " + key[currentMinNodeVisited] + " and " + key[currentMinNodeUnvisited] +
					" with a value of " + Double.toString(currentMinConnection));
		}
		return totalDistance;
	}

	public static void main(String[] args) {
		//0 is used to indicate no connection and is ignored by the above function
		double[][] connections = {
			{0,6.5,11,0,7.5,0},
			{6.5,0,7.5,0,0,13},
			{11,7.5,0,5.5,0,5},
			{0,0,5.5,0,0,10},
			{7.5,0,0,0,0,5},
			{0,13,5,10,5,0}
		};
		System.out.println("Total Distance of: " + mst(connections));
	}
}
