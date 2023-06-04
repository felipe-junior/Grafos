package Core;

class Edge
{
	int NodeA;
	int NodeB;
	Float Wheight;
	
	public Edge(int nodeA, int nodeB, Float wheight) {
		NodeA = nodeA;
		NodeB = nodeB;
		Wheight = wheight;
	}
	
	public Edge(int nodeA, int nodeB) {
		NodeA = nodeA;
		NodeB = nodeB;
		Wheight = null;
	}
}
