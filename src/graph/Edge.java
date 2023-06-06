package graph;

public class Edge
{
	private int NodeA;
	private int NodeB;
	private Float Wheight;

	public Edge(int nodeA, int nodeB) {
		NodeA = nodeA;
		NodeB = nodeB;
		Wheight = null;
	}

	public Edge(int nodeA, int nodeB, Float wheight) {
		NodeA = nodeA;
		NodeB = nodeB;
		Wheight = wheight;
	}

	public int getNodeA() {
		return NodeA;
	}

	public void setNodeA(int nodeA) {
		NodeA = nodeA;
	}

	public int getNodeB() {
		return NodeB;
	}

	public void setNodeB(int nodeB) {
		NodeB = nodeB;
	}

	public Float getWheight() {
		return Wheight;
	}

	public void setWheight(Float wheight) {
		Wheight = wheight;
	}
}
