package graphs;

public abstract class GraphTraversal{

  abstract public void execute(Graph g, int s);

  protected void processVertexEarly(int u){}

  protected void processVertexLate(int u){}

  protected final void processEdge(int u, int v){
    processEdge(u, v, 0.0);
  }

  protected void processEdge(int u, int v, double weight){}


}
