//Gil Nevo 310021654 Shachar Bartal 305262016

package Assig3;

import java.util.Vector;

public class Vertex {
	public char name;
	
	// Vector of All vertexes that current Vertex connects to.
	public Vector<Vertex> next;
	
	// Vector of All Edges that current Vertex starts at.
	public Vector<Edge> EdgeVector;
	
	/*fields for DFS*/
	public String color;
	public Vertex p ;
	public int Dtime;
	public int Ftime;

	//constructor.
	Vertex(char ch) {
		name = ch;
		next = new Vector();
		EdgeVector = new Vector();
		color = "white";
		p = null;
		Dtime = 0;
		Ftime = 0;
	}
}
