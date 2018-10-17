package graphs;

import graphs.Graph.EdgeNode;
import static graphs.GraphTraversal.Progress;

public class DFS extends GraphTraversal{

  public int [] parent;
  public int [] entry;
  public int [] exit;

  int time;


  @Override
  public void init(Graph g){
    super.init(g);

    entry = new int[g.vertices];
    exit = new int[g.vertices];
    time=0;

  }

  @Override
  public void execute( int s){
    dfsVisit(s);
  }

  protected final void dfsVisit(int u){
    entry[u] = time++;
    progress[u] = Progress.DISCOVERED;

    for(EdgeNode e : g.adjList[u]){
      if(progress[e.v] == Progress.UNDISCOVERED){
        processVertexEarly(e.v);
        processEdge(u,e.v);
        dfsVisit(e.v);
        processVertexLate(e.v);
      }
    }
    exit[u] = ++time;
    progress[u] = Progress.PROCESSED;

  }
}
