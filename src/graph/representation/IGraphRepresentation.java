package graph.representation;


import graph.Edge;

import java.util.List;

public interface IGraphRepresentation {
	public void initialize(int numberOfVertex, List<Edge> edges);
	public void addEdge(Edge edge);
	public int getNumberOfEdges();
	public int getNumberOfNodes();
	public int getNodeDegree(int node);
	public int getNumberOfNodesByDegree(int degree);
	public int getMaximumDegree();
	public String getDfsTree();
	public String getBfsTree();
	public void findAndShowConnectedComponents(String outputGraphDirectory);
}
