package graphs;

import graphs.Graph.EdgeNode;

public class DFS extends GraphTraversal{
  private static final int UNDISCOVERED = 0;
  private static final int DISCOVERED = 1;
  private static final int PROCESSED = 2;


  public int [] progress;
  public int [] parent;
  public int [] entry;
  public int [] exit;

  int time;

  @Override
  public void execute(Graph g, int s){
    progress = new int[g.vertices];
    parent = new int[g.vertices];
    entry = new int[g.vertices];
    exit = new int[g.vertices];
    time=0;

    dfsVisit(g,s);

  }

  protected final void dfsVisit(Graph g, int u){
    entry[u] = time++;
    progress[u] = DISCOVERED;

    for(EdgeNode e : g.adjList[u]){
      if(progress[e.v] == UNDISCOVERED){
        processVertexEarly(e.v);
        processEdge(u,e.v);
        dfsVisit(g,e.v);
        processVertexLate(e.v);
      }
    }
    exit[u] = ++time;
    progress[u] = PROCESSED;

  }
}
