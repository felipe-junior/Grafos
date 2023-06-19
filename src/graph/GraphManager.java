package graph;

import graph.enums.SearchType;
import graph.representation.IGraphRepresentation;

import java.io.*;
import java.util.ArrayList;

import static graph.enums.SearchType.DFS;


public class GraphManager {
	private final IGraphRepresentation graph;
	private final String inputGraphFilePath;
	private final String outputGraphDirectory;

	public GraphManager(IGraphRepresentation graph, String inputGraphFilePath, String outputGraphDirectory) {
		this.graph = graph;
		this.inputGraphFilePath = inputGraphFilePath;
		this.outputGraphDirectory = outputGraphDirectory;
		this.buildGraphFromFile();
	}

	public void buildGraphFromFile() {
		try {
			this.convertFileToGraph();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private void convertFileToGraph() throws IOException {
		System.out.println("Reading file");

		try(var reader = new BufferedReader(new FileReader(this.inputGraphFilePath))) {
			String line = reader.readLine();
			var numberOfVertex = Integer.parseInt(line);
			var edges = new ArrayList<Edge>();

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				int nodeA = Integer.parseInt(values[0]);
				int nodeB = Integer.parseInt(values[1]);

				var weight = values.length == 3 ? Float.parseFloat(values[2]) : null;

				edges.add(new Edge(nodeA, nodeB, weight));
//				System.out.println(String.format("%d %d %s", nodeA, nodeB, (weight == null ? "" : weight.toString())));
			}
			this.graph.initialize(numberOfVertex, edges);
			System.out.println("End of file reading");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void generateTree(SearchType searchMethod) {
		String tree;
		if(searchMethod.getType() == DFS.getType()) {
			tree = graph.getDfsTree();
		} else {
			tree = graph.getBfsTree();
		}

		var  filename = graph.getClass().getSimpleName() + (searchMethod.getType() == DFS.getType() ? "-dfs-":"-bfs-")  + "node-levels.txt";
		try {
			FileWriter myWriter = new FileWriter(this.outputGraphDirectory + filename);
			myWriter.write(tree);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void showConnectComponents()
	{
		graph.findAndShowConnectedComponents(this.outputGraphDirectory);
	}

	public void generateGraphOutput() {
		var n = graph.getNumberOfNodes();
		var m = graph.getNumberOfEdges();
		var d = this.calculateAverageDegree();

		try(var writer = new BufferedWriter(new FileWriter(this.outputGraphDirectory + graph.getClass().getSimpleName() + "-output.txt"))){
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
}
