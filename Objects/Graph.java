package Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

  private HashMap< String, Vertex> vertices;
  private ArrayList<Edge> edges;

  public Graph(HashMap<String, Vertex> vertices, ArrayList<Edge> edges) {
    this.vertices = vertices;
    this.edges = edges;
  }

  public HashMap<String, Vertex> getVertices() {
    return vertices;
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public ArrayList<Edge> incidentEdges(PathVertex v) {
    ArrayList<Edge> output = new ArrayList<>();
    for (Edge edge : edges) 
      if (edge.getVertex1().getLabel().equals(v.getLabel())) 
        output.add(edge);
    return output;
  }
}
