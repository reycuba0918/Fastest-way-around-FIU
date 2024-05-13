package Objects;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Vertex extends JComponent {

  private final int diameter = 16;
  private String label;
  private Color color;
  private Color clickColor;
  private Color unclickColor;
  private int centerX;
  private int centerY;

  public Vertex(String label, Color clickColor, Color unclickColor, int centerX, int centerY) {
    this.label = label;
    this.centerX = centerX;
    this.centerY = centerY;
    this.color = unclickColor;
    this.clickColor = clickColor;
    this.unclickColor = unclickColor;
    this.setBounds(centerX - (diameter / 2), centerY - (diameter / 2), diameter, diameter);
  }

  public String getLabel() {
    return label;
  }

  public int getCenterX() {
    return centerX;
  }

  public int getCenterY() {
      return centerY;
  }

  public Color getColor() {
    return color;
  }

  public Color getClickColor() {
    return clickColor;
  }

  public Color getUnclickColor() {
    return unclickColor;
  }

  public void click() {
    color = clickColor;
    this.repaint();
  }

  public void unclick() {
    color = unclickColor;
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    // g.drawString(label, x, y);
    g.setColor(color);
    g.fillOval(0, 0, diameter, diameter);
    super.paintComponent(g);
  }
}
