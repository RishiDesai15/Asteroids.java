
import java.awt.Polygon;

public class Debris extends VectorSprite {
    public Debris (double x,double y){
        shape = new Polygon();
        shape.addPoint(1,1);
        shape.addPoint(1,-1);
        shape.addPoint(-1,-1);
        shape.addPoint(-1,1);
        drawShape = new Polygon();
        drawShape.addPoint(1,1);
        drawShape.addPoint(1,-1);
        drawShape.addPoint(-1,-1);
        drawShape.addPoint(-1,1);
        
        angle = Math.random() * 2 * Math.PI;
        THRUST = -10;
        xspeed = Math.cos(angle + Math.PI / 2)*THRUST;
        yspeed = Math.sin(angle + Math.PI / 2)*THRUST;
        xposition = x+xspeed;
        yposition = y+yspeed;
        
    }

}

