
import java.awt.Polygon;

public class Rocks extends VectorSprite {

    int size;
    int level;

    public Rocks(int l) {
        size = 3;
        level = 1;
        initialize();
    }

    public Rocks(double x, double y, int s, int l) {
        size = s;
        initialize();
        xposition = x;
        yposition = y;
        level = l;
        
    }

    public void updatePosition() {
        angle += ROTATION;
        super.updatePosition();
    }

    public void initialize() {
        shape = new Polygon();
        shape.addPoint(10*size, 1*size);
        shape.addPoint(1*size, 7*size);
        shape.addPoint(-5*size, 2*size);
        shape.addPoint(-4*size, -3*size);
        shape.addPoint(4*size, -7*size);
        shape.addPoint(3*size, 4*size);
        drawShape = new Polygon();
        drawShape.addPoint(10*size, 1*size);
        drawShape.addPoint(1*size, 7*size);
        drawShape.addPoint(-5*size, 2*size);
        drawShape.addPoint(-4*size, -3*size);
        drawShape.addPoint(4*size, -7*size);
        shape.addPoint(3*size, 4*size);
        double h = Math.random() + 2.5 + (5 - size) + level;
        double a = Math.random() * 2 * Math.PI;
        xspeed = Math.cos(a) * h;
        yspeed = Math.sin(a) * h;
        h = Math.random() * 300 + 100;
        a = Math.random() * 2 * Math.PI;
        xposition = Math.sin(a) * h + 450;
        yposition = Math.cos(a) * h + 300;
        ROTATION = 8 - size * Math.random();
    }
}
