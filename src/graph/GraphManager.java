package graph;

import graph.representation.IGraphRepresentation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static graph.enums.SearchType.BFS;
import static graph.enums.SearchType.DFS;


public class GraphManager {
	private final IGraphRepresentation graph;

	public GraphManager(IGraphRepresentation graph) {
		this.graph = graph;
	}

	public void buildGraphFromFile(String path) {
		try {
			this.convertFileToGraph(path);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private void convertFileToGraph(String path) throws IOException {
		System.out.println("Reading file");

		try(var reader = new BufferedReader(new FileReader(path))) {
			String line = reader.readLine();
			var numberOfVertex = Integer.parseInt(line);
			var edges = new ArrayList<Edge>();

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				int nodeA = Integer.parseInt(values[0]);
				int nodeB = Integer.parseInt(values[1]);

				var weight = values.length == 3 ? Float.parseFloat(values[2]) : null;

				edges.add(new Edge(nodeA, nodeB, weight));
				System.out.println(String.format("%d %d %s", nodeA, nodeB, (weight == null ? "" : weight.toString())));
			}
			this.graph.initialize(numberOfVertex, edges);
			System.out.println("End of file reading");
		}
	}

	public void generateTree() {
		var searchMethod = BFS.getType();
		String tree;
		if(searchMethod == DFS.getType()) {
			tree = graph.getDfsTree();
		} else {
			tree = graph.getBfsTree();
		}

		var  filename = graph.getClass().getName()+ (searchMethod == DFS.getType() ? "-dfs-":"-bfs-")  + "node-levels.txt";
		try {
			FileWriter myWriter = new FileWriter("src/files/output/graph/" + filename);
			myWriter.write(tree);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void generateGraphOutput(String filePath) {
		var n = graph.getNumberOfNodes();
		var m = graph.getNumberOfEdges();
		var d = this.calculateAverageDegree();

		try(var writer = new BufferedWriter(new FileWriter(filePath))){
			writer.write(String.format("# n = %s\n", n));
			writer.write(String.format("# m = %s\n", m));
			writer.write(String.format("# d = %s\n", d));

			int maxDegree = this.graph.getMaximumDegree();
			for (int i = 1; i <= maxDegree; i++) {
				Float frequency = (float) this.graph.getNumberOfNodesByDegree(i) / this.graph.getNumberOfNodes();
				writer.write(String.format("%d %s", i, frequency));
				if(i != maxDegree) writer.newLine();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private float calculateAverageDegree() {
		int degreeCount = 0;
		for (int node = 0; node < this.graph.getNumberOfNodes(); node++) {
			degreeCount += this.graph.getNodeDegree(node);
		}
		return (float) degreeCount / this.graph.getNumberOfNodes();
	}

	public void generateConnectedComponents(){
		List<List<Integer>> connectedComponents = graph.generateConnectedComponents();

		// Ordenar as componentes conexos em ordem decrescente de tamanho
		connectedComponents.sort((a, b) -> b.size() - a.size());

		// Imprimir o número de componentes conexos
		System.out.println("Número de componentes conexos: " + connectedComponents.size());

		// Imprimir informações de cada componente conexo
		for (int i = 0; i < connectedComponents.size(); i++) {
			List<Integer> component = connectedComponents.get(i);

			// Imprimir o tamanho do componente
			System.out.println("Tamanho do componente " + (i + 1) + ": " + component.size());

			// Imprimir os vértices pertencentes ao componente
			System.out.println("Vértices do componente " + (i + 1) + ": " + component);
			System.out.println();
		}
	}
}
