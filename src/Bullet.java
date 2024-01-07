
import java.awt.Polygon;


public class Bullet extends VectorSprite {
    int ship;
    public Bullet(double x,double y,double a, int s){
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
        ship = s;
        
        angle = a;
        THRUST = -20;
        xspeed = Math.cos(a + Math.PI / 2)*THRUST;
        yspeed = Math.sin(a + Math.PI / 2)*THRUST;
        xposition = x+xspeed;
        yposition = y+yspeed;
        
    }

}
