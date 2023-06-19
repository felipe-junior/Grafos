package graph.representation;

import graph.Edge;

import java.util.*;

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
	public String getDfsTree() {
		StringBuilder tree = new StringBuilder();
		var visited = new boolean[adjacentList.size()];
		int root = 0;
		dfs(root, tree, visited, 0);
		return tree.toString();
	}

	@Override
	public String getBfsTree() {
		StringBuilder tree = new StringBuilder();
		var visited = new boolean[adjacentList.size()];
		int root = 0;
		bfs(root, tree, visited);
		return tree.toString();
	}

	public void bfs(int startNode, StringBuilder tree, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		visited[startNode] = true;
		queue.offer(startNode);

		while (!queue.isEmpty()) {
			int currentNode = queue.poll();
			int level = getLevel(currentNode, startNode);
			String log = String.format("NODE: %d - LEVEL: %d\n", currentNode + 1, level);
			tree.append(log);

			LinkedList<Integer> neighbors = adjacentList.get(currentNode);
			for (int neighbor : neighbors) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.offer(neighbor);
				}
			}
		}
	}

	private void dfs(int startNode, StringBuilder tree, boolean[] visited, int level) {
		visited[startNode] = true;
		String log = String.format("NODE: %d - LEVEL: %d\n", startNode + 1, level);
		tree.append(log);

		LinkedList<Integer> neighbors = adjacentList.get(startNode);
		for (int neighbor : neighbors) {
			if (!visited[neighbor]) {
				dfs(neighbor, tree, visited, level + 1);
			}
		}
	}
	@Override
	public List<List<Integer>> generateConnectedComponents() {
		List<List<Integer>> connectedComponents = new ArrayList<>();
		boolean[] visited = new boolean[adjacentList.size()];

		for (int node = 0; node < adjacentList.size(); node++) {
			if (!visited[node]) {
				List<Integer> connectedComponent = new ArrayList<>();
				dfs(node, visited, 0, connectedComponent);
				connectedComponents.add(connectedComponent);
			}
		}

		return connectedComponents;
	}

	private void dfs(int startNode, boolean[] visited, int level, List<Integer> connectedComponent) {
		Stack<Integer> stack = new Stack<>();
		stack.push(startNode);

		while (!stack.isEmpty()) {
			int currentNode = stack.pop();
			visited[currentNode] = true;
			connectedComponent.add(currentNode + 1); // Adiciona o nó atual à componente conexa

			LinkedList<Integer> neighbors = adjacentList.get(currentNode);
			for (int neighbor : neighbors) {
				if (!visited[neighbor]) {
					stack.push(neighbor);
				}
			}
		}
	}

	private int getLevel(int node, int startNode) {
		int level = 0;
		int currentNode = node;
		while (currentNode != startNode) {
			currentNode = getParent(currentNode);
			level++;
		}
		return level;
	}

	private int getParent(int node) {
		for (int i = 0; i < adjacentList.size(); i++) {
			if (adjacentList.get(i).contains(node)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getNumberOfEdges() {
		return this.edges.size();
	}

}
