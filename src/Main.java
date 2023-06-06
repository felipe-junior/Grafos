import graph.EGraphRepresentation;
import graph.GraphManager;
import graph.representation.AdjacentList;
import graph.representation.AdjacentMatrix;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var filepath = "src/graph_with_weight.txt";
		var sc = new Scanner(System.in);

		System.out.println("Qual representação do Grafo utilizar ?");
		System.out.println("1 - Lista");
		System.out.println("2 - Matriz");

		System.out.print("Escolha uma opção: ");
		var graphRepresentationInput = Integer.parseInt(sc.nextLine());
		var graphType = EGraphRepresentation.getValue(graphRepresentationInput);
		
		GraphManager graphManager = null;

		switch (graphType){
			case LIST:
				graphManager = new GraphManager(new AdjacentList());
				break;
			case MATRIX:
				graphManager = new GraphManager(new AdjacentMatrix());
				break;
		}

		graphManager.buildGraphFromFile(filepath);
		graphManager.generateTree();
		graphManager.generateGraphOutput("src/output.txt");
	}

}
