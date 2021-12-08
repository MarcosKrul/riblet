import myutils.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.geom.*;

public class Riblet extends Frame {

  private final int STEP = 50;
  private final double baseHalf;
  private final GeneralPath GENERAL_PATH;
  
  private int sampleLength;
  private double height;
  private double distanceBetween;
  private double distanceFirst;
  private TwoAxisCoordinate frameSize;
  
  public Riblet(String title, int sampleLength, double height, double angle, double distanceBetween, double distanceFirst) throws Exception {
    
    if (distanceFirst >= sampleLength) 
      throw new Exception("A distância do centro do primeiro Riblet é maior que a distância total.");
  
    if (angle >= 180)
      throw new Exception("Ângulo inválido.");

    this.GENERAL_PATH = new GeneralPath();

    this.sampleLength = sampleLength;
    this.height = height;
    this.distanceBetween = distanceBetween;
    this.distanceFirst = distanceFirst;

    this.baseHalf = (Math.tan(Math.toRadians(angle/2)) * height) / 2;
    
    this.frameSize = new TwoAxisCoordinate(this.sampleLength + 2*this.STEP, (int)height + 100);
    
    setTitle(title);
    setSize(this.frameSize.x, this.frameSize.y);
    
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {  
        dispose();
      }
    });
  }

  private Graphics2D setup(Graphics g, double y) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setStroke(new BasicStroke(2.0f));
    
    this.GENERAL_PATH.moveTo(CoordinateSystem.X_OFFSET, y);
    this.GENERAL_PATH.lineTo(this.sampleLength, y);
    this.GENERAL_PATH.moveTo(this.distanceFirst + CoordinateSystem.X_OFFSET, y);
    
    return g2d;
  }
  
  public void paint(Graphics g) {
    double xCentro;
    double yBase = this.height + CoordinateSystem.Y_OFFSET;
    
    Graphics2D g2d = this.setup(g, yBase);
    
    while(this.GENERAL_PATH.getCurrentPoint().getX() + this.baseHalf <= this.sampleLength) {
      xCentro = this.GENERAL_PATH.getCurrentPoint().getX();

      this.GENERAL_PATH.moveTo(xCentro - this.baseHalf, yBase);
      this.GENERAL_PATH.lineTo(xCentro, CoordinateSystem.Y_OFFSET);
      this.GENERAL_PATH.lineTo(xCentro + this.baseHalf, yBase);
      this.GENERAL_PATH.moveTo(xCentro + this.distanceBetween, yBase);
    }
    
    g2d.draw(this.GENERAL_PATH);
    g2d.setStroke(new BasicStroke(1.0f));
    CoordinateSystem.draw(g2d, this.frameSize, this.STEP);
  }

}
