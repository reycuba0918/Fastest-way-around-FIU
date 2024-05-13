package Objects;
public class PathVertex extends Vertex{

  public Integer distance;
  public PathVertex parent;
  public boolean visited;

  public PathVertex(Vertex vertex) {
    super(vertex.getLabel(),vertex.getClickColor(),vertex.getUnclickColor(), vertex.getCenterX(), vertex.getCenterY());
    this.distance = Integer.MAX_VALUE;
    this.parent = null;
    this.visited = false;
  }
}
