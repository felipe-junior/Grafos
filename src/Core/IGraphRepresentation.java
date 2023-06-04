package Core;

import java.util.List;

public interface IGraphRepresentation {
	public void Initialize(int numberOfVertex, List<Edge> edges);
	public void AddEdge(Edge edge);
	public int GetNumberOfEdges();
	public int GetNumberOfNodes();
	public int GetNodeDegree(int node);
	public int GetNumberOfNodesByDegree(int degree);
	public int GetMaximumDegree();
}
