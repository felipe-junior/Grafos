package graph.representation;


import graph.Edge;

import java.util.List;

public interface IGraphRepresentation {
	void initialize(int numberOfVertex, List<Edge> edges);
	void addEdge(Edge edge);
	int getNumberOfEdges();
	int getNumberOfNodes();
	int getNodeDegree(int node);
	int getNumberOfNodesByDegree(int degree);
	int getMaximumDegree();
	String getDfsTree();
	String getBfsTree();
	void findAndShowConnectedComponents(String outputGraphDirectory);
	List<Integer> calculateShortestPath(int startNode, int endNode);
	float calculateEdgeWeight(int nodeA, int nodeB);
	List<Edge> findMinimumSpanningTree();
	double calculateAverageDistance();
}
