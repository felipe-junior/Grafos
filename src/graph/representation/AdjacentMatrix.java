package graph.representation;

import graph.Edge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacentMatrix implements IGraphRepresentation {

	private Float[][] adjacentMatrix;
	private List<Edge> edges;
	private boolean[] visited;
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
	public String getDfsTree(){
		StringBuilder tree = new StringBuilder();
		visited = new boolean[adjacentMatrix.length];
		int root = 0;
		dfs(root, 0, tree);
		return tree.toString();
	}

	@Override
	public String getBfsTree(){
		StringBuilder tree = new StringBuilder();
		visited = new boolean[adjacentMatrix.length];
		int root = 0;
		bfs(root, tree);
		return tree.toString();
	}

	private void dfs(int node, int level, StringBuilder tree) {

		this.visited[node] = true;
		int numVertices = adjacentMatrix.length;

		String log = String.format("NODE: %d - LEVEL: %d\n", node+1, level);
		tree.append(log);
		level += 1;
		for (int i = 0; i < numVertices; i++) {
			if (adjacentMatrix[node][i] != 0 && !this.visited[i])
			{
				this.dfs(i, level, tree);
			}
		}
	}

	private void bfs(int root, StringBuilder tree)
	{
		Queue<Integer> queue = new LinkedList<>();
		queue.add(root);
		String logRoot = String.format("NODE: %d - LEVEL: %d\n", root+1, 0);
		var levels = new Integer[adjacentMatrix.length];
		Arrays.fill(levels, -1);
		levels[root] = 0;
		tree.append(logRoot);
		while (!queue.isEmpty()){
			var node = queue.poll();
			visited[node] = true;
			for (int i = 0; i < adjacentMatrix.length; i++) {
				if (adjacentMatrix[node][i] != 0 && !visited[i])
				{
					queue.add(i);
					if(levels[i] == -1)
					{
						levels[i] = levels[node] + 1;
						tree.append(String.format("NODE: %d - LEVEL: %d\n", i + 1, levels[i]));
					}
				}
			}
		}
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
