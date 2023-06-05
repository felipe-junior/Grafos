import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdjacentListGraphRepresentationStrategy implements IGraphRepresentation {

	private ArrayList<LinkedList<Integer>> AdjacentList;
	private List<Edge> Edges;
	
	public AdjacentListGraphRepresentationStrategy()
	{
	}
	
	public void Initialize(int numberOfVertex, List<Edge> edges)
	{
		this.Edges = edges;
		this.AdjacentList = new ArrayList<LinkedList<Integer>>(numberOfVertex+1); 
		//Primeira linha do array ta vazio pq o grafo come�a sempre com 1
		for (int i = 0; i <= numberOfVertex; i++)
		{
			AdjacentList.add(new LinkedList<Integer>());
		}
		
		for (Edge edge : this.Edges)
		{
			this.AddEdge(edge);
		}
		
	}

	@Override
	public void AddEdge(Edge edge) {
		AdjacentList.get(edge.NodeA).add(edge.NodeB);
		AdjacentList.get(edge.NodeB).add(edge.NodeA);
	}

	@Override
	public int GetNumberOfNodes() {
		return this.AdjacentList.size() - 1;
	}

	@Override
	public int GetNodeDegree(int node) {
		return this.AdjacentList.get(node).size();
	}

	@Override
	public int GetNumberOfNodesByDegree(int degree) {
		var count = 0; 
		for (int node = 1; node <= GetNumberOfNodes(); node++)
		{
			if (this.GetNodeDegree(node) == degree)
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public int GetMaximumDegree() {
		int maxDegree = 0;
		for (int i = 1; i <= this.GetNumberOfNodes(); i++)
		{	
			var degree = this.GetNodeDegree(i);
			if(maxDegree < degree)
			{
				maxDegree = degree;
			}
		}
		return maxDegree;
	}

	@Override
	public int GetNumberOfEdges() {
		return this.Edges.size();
	}

}
