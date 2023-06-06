package graph.representation;

import graph.Edge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacentList implements IGraphRepresentation {

	private ArrayList<LinkedList<Integer>> adjacentList;
	private List<Edge> edges;
	
	public AdjacentList() { }
	
	public void initialize(int numberOfVertex, List<Edge> edges) {
		this.edges = edges;
		this.adjacentList = new ArrayList<LinkedList<Integer>>(numberOfVertex+1);
		//Primeira linha do array ta vazio pq o grafo come�a sempre com 1
		for (int i = 0; i < numberOfVertex; i++) {
			adjacentList.add(new LinkedList<Integer>());
		}
		
		for (Edge edge : this.edges) {
			this.addEdge(edge);
		}
		
	}

	@Override
	public String getTree(){
		throw new RuntimeException("ALDALWDLAWd");
	}

	@Override
	public void addEdge(Edge edge) {
		var nodeA = edge.getNodeA() - 1;
		var nodeB = edge.getNodeB() - 1;

		adjacentList.get(nodeA).add(nodeB);
		adjacentList.get(nodeB).add(nodeA);
	}

	@Override
	public int getNumberOfNodes() {
		return this.adjacentList.size();
	}

	@Override
	public int getNodeDegree(int node) {
		return this.adjacentList.get(node).size();
	}

	@Override
	public int getNumberOfNodesByDegree(int degree) {
		var count = 0; 
		for (int node = 0; node < getNumberOfNodes(); node++) {
			if (this.getNodeDegree(node) == degree) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getMaximumDegree() {
		int maxDegree = 0;
		for (int i = 0; i < this.getNumberOfNodes(); i++) {
			var degree = this.getNodeDegree(i);
			if(maxDegree < degree) {
				maxDegree = degree;
			}
		}
		return maxDegree;
	}

	@Override
	public int getNumberOfEdges() {
		return this.edges.size();
	}

}
