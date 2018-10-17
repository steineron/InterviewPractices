package graphs;

public abstract class GraphTraversal{

protected Graph g;

  public enum Progress{
   UNDISCOVERED,
   DISCOVERED,
   PROCESSED
  }

  public Progress [] progress;
  public int [] parent;

  public void init(Graph g){
    this.g=g;

    progress = new Progress[g.vertices];
    for(int v=0; v<g.vertices; v++){
      progress[v]=Progress.UNDISCOVERED;
    }

    parent = new int[g.vertices];
  }

  abstract public void execute(int s);

  protected void processVertexEarly(int u){}

  protected void processVertexLate(int u){}

  protected final void processEdge(int u, int v){
    processEdge(u, v, 0.0);
  }

  protected void processEdge(int u, int v, double weight){}


  public final boolean visited(int u){
    return u >= 0 && u < g.vertices && progress != null && progress[u] != Progress.UNDISCOVERED;
  }
}
