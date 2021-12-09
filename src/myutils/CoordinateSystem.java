package src.myutils;

import java.awt.Font;
import java.awt.Graphics2D;


public abstract class CoordinateSystem {

  public final static int X_OFFSET = 50;
  public final static int Y_OFFSET = 50;

  public static void draw(Graphics2D g2d, TwoAxisCoordinate max, int step) {
    String s;
    
    // Remember the actual font.
    Font fo = g2d.getFont();
    
    // Use a small font.
    g2d.setFont(new Font("sansserif", Font.PLAIN, 9));
    
    // x-axis.
    g2d.drawLine(X_OFFSET, Y_OFFSET, max.x, Y_OFFSET);
    // Marks and labels for the x-axis.
    for (int i = X_OFFSET + step; i <= max.x; i = i + step) {
      g2d.drawLine(i, Y_OFFSET - 2, i, Y_OFFSET + 2);
      g2d.drawString(String.valueOf(i-step), i - 7, Y_OFFSET - 7);
    }

    // y-axis.
    g2d.drawLine(X_OFFSET, Y_OFFSET, X_OFFSET, max.y);
    // Marks and labels for the y-axis.
    s = "  "; // for indention of numbers < 100
    for (int i = Y_OFFSET + step; i <= max.y; i = i + step) {
      g2d.drawLine(X_OFFSET - 2, i, X_OFFSET + 2, i);
      if (i > 99) s = "";
      g2d.drawString(s + String.valueOf(i-step), X_OFFSET - 25, i + 5);
    }

    // Reset to the original font.
    g2d.setFont(fo);
  }

}
