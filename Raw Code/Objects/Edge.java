package Objects;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Edge extends JComponent {

  private Vertex vertex1;
  private Vertex vertex2;
  private int distance;
  private int stroke;
  private Color color;

  public Edge(Vertex vertex1, Vertex vertex2, int stroke, Color color, Dimension size) {

    this.vertex1 = vertex1;
    this.vertex2 = vertex2;

    distance = (int) (Math.sqrt(Math.pow(this.vertex2.getCenterX() - this.vertex1.getCenterX(), 2) + Math.pow(this.vertex2.getCenterY() - this.vertex1.getAlignmentY(), 2)));
    this.stroke = stroke;
    this.color = color;

    this.setSize(size);
  }

  public int getDistance() {
      return distance;
  }

  public Vertex getVertex1() {
      return vertex1;
  }

  public Vertex getVertex2() {
      return vertex2;
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setStroke(new BasicStroke(stroke));
    g2D.setColor(color);
    g2D.drawLine(vertex1.getCenterX(), vertex1.getCenterY(), vertex2.getCenterX(), vertex2.getCenterY());
    super.paintComponent(g);
  }
}
