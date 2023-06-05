public class Main {

	public static void main(String[] args) {
		var filepath = "src/graph.txt";
		var graph = new GraphManager(new AdjacentListGraphRepresentationStrategy());
		graph.BuildGraphFromFile(filepath);
		graph.DisplayGraphInfo();
	}

}
