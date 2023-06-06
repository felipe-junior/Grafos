package graph.representation;

import graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdjacentMatrix implements IGraphRepresentation {

	private Float[][] adjacentMatrix;
	private List<Edge> edges;
	private boolean hasWeight;

	public AdjacentMatrix() { }
	
	public void initialize(int numberOfVertex, List<Edge> edges) {
		this.edges = edges;
		this.adjacentMatrix = new Float[numberOfVertex][numberOfVertex];
		for (Float[] matrix : adjacentMatrix) {
			Arrays.fill(matrix, 0f);
		}
		
		for (var edge : this.edges) {
			this.addEdge(edge);
		}

		this.hasWeight = edges.get(0).getWheight() != null;
	}

	@Override
	public String getTree(){
		return breadthFirstSearch(adjacentMatrix, 0, 0);
	}

	private static String breadthFirstSearch(Float[][] matriz, int vertice, int nivel) {
		StringBuilder arvore = new StringBuilder();
		arvore.append(getIndentation(nivel));
		arvore.append(vertice);
		arvore.append("\n");

		int numVertices = matriz.length;

		for (int i = 0; i < numVertices; i++) {
			if (matriz[vertice][i] != 0) {
				arvore.append(getIndentation(nivel + 1));
				arvore.append("├── ");
				arvore.append(breadthFirstSearch(matriz, i, nivel + 1));
			}
		}

		return arvore.toString();
	}

	private static String getIndentation(int level) {
		StringBuilder indentation = new StringBuilder();
		for (int i = 0; i < level; i++) {
			indentation.append("│   ");
		}
		if (level > 0) {
			indentation.append("└── ");
		}
		return indentation.toString();
	}

	@Override
	public void addEdge(Edge edge) {
		var nodeA = edge.getNodeA() - 1;
		var nodeB = edge.getNodeB() - 1;

		adjacentMatrix[nodeA][nodeB] = edge.getWheight() != null ? edge.getWheight() : 1f;
		adjacentMatrix[nodeB][nodeA] =  edge.getWheight() != null ? edge.getWheight() : 1f;
	}

	@Override
	public int getNumberOfNodes() {
		return this.adjacentMatrix.length;
	}

	@Override
	public int getNodeDegree(int node) {
		var count = 0;
		for (var i = 0; i < adjacentMatrix[node].length; i++){
			if(!hasWeight && adjacentMatrix[node][i] == 1) count++;
			else if(hasWeight && adjacentMatrix[node][i] != 0) count++;
		}
		return count;
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
