package graphs;

import graphs.Graph.EdgeNode;
import java.util.LinkedList;

public class BFS extends GraphTraversal{


  private static final int UNDISCOVERED = 0;
  private static final int DISCOVERED = 1;
  private static final int PROCESSED = 2;


  public int [] progress;
  public int [] parent;


  @Override
  final public void execute(Graph g, int s){

    progress = new int[g.vertices];
    LinkedList<Integer> q = new LinkedList<>();

    q.add(s);
    progress[s] = DISCOVERED;

    while(!q.isEmpty()){
        int u = q.remove(0);

        for(EdgeNode e: g.adjList[u]){
          int v = e.v;

          processVertexEarly(v);

          if(progress[v] != PROCESSED || g.isDirected){
            processEdge(u,v);
          }

          if(progress[v]==UNDISCOVERED){
            progress[v] = DISCOVERED;
            q.add(v);
            parent[v]=u;
            processVertexLate(v);
          }

        }
        progress[u] = PROCESSED;


    }


  }

}
