package Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class GraphManager {
	private IGraphRepresentation Graph;
	
	public GraphManager(IGraphRepresentation graph)
	{
		Graph = graph;
	}
	
	public void BuildGraphFromFile(String path)
	{	
		try {
			this.FileParser(path);
		}		
		 catch (FileNotFoundException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
	}
	
	
	public void DisplayGraphInfo()
	{
		System.out.println("# n = "+ Graph.GetNumberOfNodes()); //vertex number
		System.out.println("# m = "+ Graph.GetNumberOfEdges()); //Edges number
		System.out.println("# d = "+ this.CalculateAverageDegree());//Grau
		this.CalculateAndDisplayEmpiricDistribution();
	}
	
	
	//Private methods
	
	private void CalculateAndDisplayEmpiricDistribution()
	{
		int maxDegree = this.Graph.GetMaximumDegree();	
		for (int i = 1; i <= maxDegree; i++) {
			Float frequency = (float) this.Graph.GetNumberOfNodesByDegree(i) / this.Graph.GetNumberOfNodes();
			System.out.println(i + " " + String.format(frequency.toString(),"%.2f"));
		}
	}
	
	private float CalculateAverageDegree()
	{
		int degreeCount = 0;
		for (int node = 1 ; node <= this.Graph.GetNumberOfNodes(); node++) {
			degreeCount += this.Graph.GetNodeDegree(node);
		}
		return (float) degreeCount / this.Graph.GetNumberOfNodes();
	}
	
	private void FileParser(String path) throws FileNotFoundException
	{   
		System.out.println("Reading file");
		
        File myObj = new File(path);
		Scanner myReader = new Scanner(myObj);
		var numberOfVertex = Integer.parseInt(myReader.nextLine());
		var edges = new ArrayList<Edge>();
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
		this.Graph.Initialize(numberOfVertex, edges);
		
		System.out.println("End of file reading");
        
        myReader.close();
        
	}
		
}
