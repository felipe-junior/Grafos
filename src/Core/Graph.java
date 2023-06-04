package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Graph {
	

	private List<Node> Nodes;
	private List<Edge> Edges;
	private int NodesCount;
	
	public Graph()
	{
		Nodes = new ArrayList<Node>();
		Edges = new ArrayList<Edge>();
	}
	
	public void BuildGraphFromFile(String path)
	{	
		try {
			this.FileParser(path);
			for (Edge edge : this.Edges) {
				
				var nodeA = this.Nodes.stream()
                .filter(n -> n.getId() == edge.NodeA)
                .findFirst()
                .orElse(null);
				
				var nodeB = this.Nodes.stream()
		                .filter(n -> n.getId() == edge.NodeB)
		                .findFirst()
		                .orElse(null);
				
				if(nodeA == null)
				{
					nodeA = new Node(edge.NodeA);
					this.Nodes.add(nodeA);
				}
				
				if(nodeB == null)
				{
					nodeB = new Node(edge.NodeB);
					this.Nodes.add(nodeB);
				}
				
				nodeA.AddNeighbor(nodeB.getId());
				nodeB.AddNeighbor(nodeA.getId());
			}
			System.out.println("Node count: " + Nodes.size());
		}		
		 catch (FileNotFoundException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
	}
	
	
	public void DisplayGraphInfo()
	{
		System.out.println("# n = "+ this.NodesCount); //vertex number
		System.out.println("# m = "+ this.Edges.size()); //Edges number
		System.out.println("# d = "+ this.CalculateAverageDegree());//Grau
		this.CalculateAndDisplayEmpiricDistribution();
	}
	
	
	//Private methods
	
	private void CalculateAndDisplayEmpiricDistribution()
	{
		int maxDegree = 0;
		for (Node node : this.Nodes)
		{
			if(maxDegree < node.GetDegree())
			{
				maxDegree = node.GetDegree();
			}
			
			node.GetDegree();
		}
		
		for (int i = 1; i <= maxDegree; i++) {
			int degree = i;
			List<Node> nodes = this.Nodes.stream()
	            .filter(n -> n.GetDegree() == degree)
	            .collect(Collectors.toList());
			Float frequency = (float) nodes.size() / this.Nodes.size();
			System.out.println(i + " " + String.format(frequency.toString(),"%.2f"));
		}
	}
	
	private float CalculateAverageDegree()
	{
		int degreeCount = 0;
		for (var node : this.Nodes) {
			degreeCount += node.GetDegree();
		}
		return (float) degreeCount / this.NodesCount;
	}
	
	private void FileParser(String path) throws FileNotFoundException
	{   
		System.out.println("Reading file");
		
        File myObj = new File(path);
		Scanner myReader = new Scanner(myObj);
		this.NodesCount = Integer.parseInt(myReader.nextLine());
		while (myReader.hasNextLine()) {
		    String data = myReader.nextLine();
		    String[] values = data.split(" ");
		    int nodeA = Integer.parseInt(values[0]);
		    int nodeB = Integer.parseInt(values[1]);
		    Float weight = null;
		    
		    if(values.length == 3)
		    {
		        weight = Float.parseFloat(values[2]);                	
		    }
		    
		    System.out.println(nodeA + " " + nodeB + (weight == null ? "" : weight));
		    this.Edges.add(new Edge(nodeA, nodeB, weight));
		}
        
		System.out.println("End of file reading");
        
        myReader.close();
        
	}
		
}
