//Gil Nevo 310021654 Shachar Bartal 305262016

package Assig3;

import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		Dag dag = new Dag();

		/* this part is creating the Dag */

		String str = "abcde";
		char[] ch = str.toCharArray();
		Vertex[] vert = new Vertex[ch.length];
		for (int i = 0; i < ch.length; i++) {
			vert[i] = new Vertex(ch[i]);
			dag.AddVertex(vert[i]);
		}
		dag.AddEdge(vert[0], vert[1], 1);
		dag.AddEdge(vert[0], vert[2], 30);
		dag.AddEdge(vert[2], vert[3], -1);
		dag.AddEdge(vert[3], vert[4], 10);
		dag.AddEdge(vert[1], vert[3], 0);
		/* printing the Dag */
		System.out.println("The graph is: ");
		dag.print();
		Vector<Vertex> TopologicList = new Vector();
		DFSMaxPath(dag, TopologicList);

	}

	/*
	 * this function is very Similar to DFS except it Creates Topologic list for
	 * us to work on
	 */
	public static void DFSMaxPath(Dag dag, Vector<Vertex> TopologicList) {
		int time = 0;
		for (int i = 0; i < dag.Adj.size(); i++) {
			if (dag.Adj.elementAt(i).color == "white")
				time = DFSVisit(dag.Adj.elementAt(i), time, TopologicList);

		}
		DynamicPath(TopologicList);

	}

	public static int DFSVisit(Vertex u, int time, Vector<Vertex> TopologicList) {

		u.color = "grey";
		time++;
		u.Dtime = time;
		for (int i = 0; i < u.next.size(); i++) {
			if (u.next.elementAt(i).color == "white") {
				u.next.elementAt(i).p = u;
				time = DFSVisit(u.next.elementAt(i), time, TopologicList);
			}
		}

		u.color = "black";
		time = time + 1;
		u.Ftime = time;
		TopologicList.add(0, u);

		return time;
	}

	/* this is where we work on the topologic list */
	public static void DynamicPath(Vector<Vertex> TopologicList) {

		/* printing the list */
		System.out.println("The topologic list is: ");
		for (int i = 0; i < TopologicList.size(); i++) {
			System.out.println(TopologicList.elementAt(i).name + " "
					+ TopologicList.elementAt(i).Ftime);
		}
		// an array that keeps max weight for each index in topologic list.
		int[] MaxPath = new int[TopologicList.size()];

		// an array that keeps max path for each index in topologic list.
		String[] Path = new String[TopologicList.size()];

		/*
		 * initialization of both arrays. max path is the current vertex, max
		 * weight is 0.
		 */
		for (int i = 0; i < MaxPath.length; i++) {
			MaxPath[i] = 0;
			String str2 = String.valueOf(TopologicList.elementAt(i).name);
			Path[i] = str2;
		}
		// initializing max.
		int max = MaxPath[0];

		// initializing MyPath
		String MyPath = "";

		// first for runs on all Vertexes in TopologicList.
		for (int i = 0; i < TopologicList.size(); i++) {

			// second for runs on all Edges of current Vertex.
			for (int j = 0; j < TopologicList.elementAt(i).EdgeVector.size(); j++) {

				/*
				 * if the Parallel index in MaxPath array, of the End vertex of
				 * edge from current Vertex, is bigger than what the index holds
				 * now, replace it. also replace in Path array the path leading
				 * to this index.
				 */
				if (MaxPath[TopologicList
						.indexOf(TopologicList.elementAt(i).EdgeVector
								.elementAt(j).End)] < MaxPath[i]
						+ TopologicList.elementAt(i).EdgeVector.elementAt(j).Weight) {

					MaxPath[TopologicList
							.indexOf(TopologicList.elementAt(i).EdgeVector
									.elementAt(j).End)] = MaxPath[i]
							+ TopologicList.elementAt(i).EdgeVector
									.elementAt(j).Weight;

					/*
					 * updating the Parallel index in Path array, of the End
					 * vertex of edge from current Vertex, to the the maximum
					 * weight path for this Vertex. in the form of String.
					 */
					Path[TopologicList
							.indexOf(TopologicList.elementAt(i).EdgeVector
									.elementAt(j).End)] = Path[i]
							+ " "
							+ String.valueOf(TopologicList.elementAt(i).EdgeVector
									.elementAt(j).End.name);
				}

			}

			// Updating the max value, and max path.
			if (MaxPath[i] > max) {
				max = MaxPath[i];
				MyPath = Path[i];
			}
		}

		// Printing results.
		System.out.println("The Max weight and the path for each Vertex:");
		for (int i = 0; i < MaxPath.length; i++) {
			System.out.println(MaxPath[i] + "   " + Path[i]);
		}
		System.out.println("the max Weight is: " + max);
		System.out.println("the Path is: " + MyPath);
	}
}
