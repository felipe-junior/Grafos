package Core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdjacentListGraphRepresentationStrategy implements IGraphRepresentation {

	private ArrayList<ArrayList<Integer>> AdjacentList;
	private List<Edge> Edges;
	
	public AdjacentListGraphRepresentationStrategy()
	{
		AdjacentList = new ArrayList<ArrayList<Integer>>();
	}
	
	public void Initialize(int numberOfVertex, List<Edge> edges)
	{
		this.Edges = edges;
		this.AdjacentList = new ArrayList<ArrayList<Integer>>(numberOfVertex+1); 
		//Primeira linha do array ta vazio pq o grafo começa sempre com 1
		for (int i = 1; i <= numberOfVertex; i++)
		{
			AdjacentList.add(new ArrayList<Integer>());
		}
		
		for (Edge edge : this.Edges)
		{
			AdjacentList.get(edge.NodeA).add(edge.NodeB);
			AdjacentList.get(edge.NodeB).add(edge.NodeA);
		}
		
	}

	@Override
	public int AddEdge(Edge edge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetNumberOfNodes() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetNodeDegree(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetNumberOfNodesByDegree(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int GetMaximumDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

}
