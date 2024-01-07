
import java.awt.Polygon;

public class Spacecraft extends VectorSprite {
        int spawnxpos, spawnypos;
    public Spacecraft() {
        shape = new Polygon();
        shape.addPoint(0,-25);
        shape.addPoint(15, 10);
        shape.addPoint(-15, 10);
        drawShape = new Polygon();
        drawShape.addPoint(0, 25);
        drawShape.addPoint(15, 10);
        drawShape.addPoint(-15, 10);
        xposition = 450;
        yposition = 300;
        spawnxpos = 0;
        spawnypos = 0;
        
        ROTATION = (2 * Math.PI / 25);
        THRUST = -5;

    }

    public void accelerate() {
        xspeed = Math.cos(angle + Math.PI / 2) * THRUST;
        yspeed = Math.sin(angle + Math.PI / 2) * THRUST;
    }

    public void rotateleft() {
        angle -= ROTATION; 
    }

    public void rotateright() {
        angle += ROTATION;
    }
    
    public void respawnShip(){
        if (active == false && counter > 50){
            xposition = spawnxpos;
            yposition = spawnypos;
            angle = 0;
            xspeed = 0;
            yspeed = 0;
            active = true;
        }
    }
}
