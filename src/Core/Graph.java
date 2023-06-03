package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Graph {
	

	private List<Node> Nodes;
	
	public Graph()
	{
		Nodes = new ArrayList<Node>();
	}
	
	public void BuildGraphFromFile(String path)
	{	
		try {
			var edges = FileParser(path);
			for (Edge edge : edges) {
				
				var nodeA = Nodes.stream()
                .filter(n -> n.getId() == edge.NodeA)
                .findFirst()
                .orElse(null);
				
				var nodeB = Nodes.stream()
		                .filter(n -> n.getId() == edge.NodeB)
		                .findFirst()
		                .orElse(null);
				
				if(nodeA == null)
				{
					nodeA = new Node(edge.NodeA);
					Nodes.add(nodeA);
				}
				
				if(nodeB == null)
				{
					nodeB = new Node(edge.NodeB);
					Nodes.add(nodeB);
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
	
	
	//Private methods
	private List<Edge> FileParser(String path) throws FileNotFoundException
	{
		List<Edge> edges = new ArrayList<Edge>();
        
		System.out.println("Reading file");
		
        File myObj = new File(path);
		Scanner myReader = new Scanner(myObj);
		var nodesCount = Integer.parseInt(myReader.nextLine());
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
		    edges.add(new Edge(nodeA, nodeB, weight));
		}
        
		System.out.println("End of file reading");
        
        myReader.close();
        
        return edges;
	}
		
}
