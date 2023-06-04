package Core;

import java.util.List;

public interface IGraphRepresentation {
	public void Initialize(int numberOfVertex, List<Edge> edges);
	public int AddEdge(Edge edge);
	public int GetNumberOfNodes();
	public int GetNodeDegree(int node);
	public int GetNumberOfNodesByDegree(int node);
	public int GetMaximumDegree();
}
