
import java.awt.Graphics;
import java.awt.Polygon;

public class VectorSprite {

    double xposition, yposition, xspeed, yspeed, angle;
    Polygon shape, drawShape;
    double ROTATION, THRUST;
    boolean active = true;
    int counter;

    public VectorSprite() {

    }

    public void paint(Graphics g) {
        if (active) {
            g.drawPolygon(drawShape);
        }
    }

    public void updatePosition() {
        counter ++;
        xposition += xspeed;
        yposition += yspeed;
        wraparound();
        for (int i = 0; i < shape.npoints; i++) {
            int x = (int) Math.round(shape.xpoints[i] * Math.cos(angle) - shape.ypoints[i] * Math.sin(angle));
            int y = (int) Math.round(shape.xpoints[i] * Math.sin(angle) + shape.ypoints[i] * Math.cos(angle));
            drawShape.xpoints[i] = x;
            drawShape.ypoints[i] = y;

        }
        drawShape.invalidate();
        drawShape.translate((int) xposition, (int) yposition);

    }

    private void wraparound() {
        if (xposition < 0) {
            xposition = 1300;
        }
        if (xposition > 1300) {
            xposition = 0;
        }

        if (yposition < 0) {
            yposition = 600;
        }
        if (yposition > 600) {
            yposition = 0;
        }
    }
}
