package Core;

public class main {

	public static void main(String[] args) {
		var filepath = "src/Core/graph.txt";
		var graph = new GraphManager(new AdjacentListGraphRepresentationStrategy());
		graph.BuildGraphFromFile(filepath);
		graph.DisplayGraphInfo();
	}

}
