package Core;

import java.util.HashSet;
import java.util.Set;

public class Node {

		private int Id;
		private Set<Integer> Neighbors;
		
		public Node(int id) {
			Id = id;
			Neighbors = new HashSet<Integer>();
		}
		
		public void AddNeighbor(int node)
		{
			Neighbors.add(node);
		}

		public int getId() {
			return Id;
		}
}
