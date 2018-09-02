//Gil Nevo 310021654 Shachar Bartal 305262016

package Assig3;

import java.util.Vector;

public class Dag {

	// Vector that holds all Vertexes.
	public Vector<Vertex> Adj;

	// constructor.
	public Dag() {

		Adj = new Vector();
	}

	public void AddVertex(Vertex v) {
		if (!Adj.contains(v)) {
			Adj.addElement(v);

		}
	}

	public void AddEdge(Vertex v, Vertex u, int Weight) {
		if (Adj.contains(v) || Adj.contains(u)) {

			// adds vertex u to the vector of vertexes of v.
			v.next.addElement(u);

			// Creates and adds Edge to Vector of Edges of v.
			Edge vu = new Edge(v, u, Weight);
			v.EdgeVector.addElement(vu);
		}
	}

	// Prints the Graph.
	public void print() {
		for (int i = 0; i < Adj.size(); i++) {
			if (Adj.elementAt(i).EdgeVector.size() > 0) {
				System.out.print(Adj.elementAt(i).name + "-------("
						+ Adj.elementAt(i).EdgeVector.elementAt(0).Weight
						+ ")------->"
						+ Adj.elementAt(i).EdgeVector.elementAt(0).End.name);
				for (int j = 1; j < Adj.elementAt(i).EdgeVector.size(); j++) {
					System.out
							.print("   "
									+ Adj.elementAt(i).name
									+ "-------("
									+ Adj.elementAt(i).EdgeVector.elementAt(j).Weight
									+ ")------->"
									+ Adj.elementAt(i).EdgeVector.elementAt(j).End.name);
				}
			} else
				System.out.print(Adj.elementAt(i).name + "-------");
			System.out.println();
		}
	}
}
