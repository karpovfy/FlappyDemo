package info.fyodor.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by dom on 03.01.2018.
 */

public class Tube {

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    private Texture topTube,bottomTube;
    public static final int FLUCTUACTION=130;
    public static final int TUBE_GAP=100;
    public static final int TUBE_WIDTH=52;
    public static final int LOWEST_OPENING=120;
    private Vector2 posTopTube,posBottomTube;
    private Random rand;

    private Rectangle boundsTop,boundsBottom;


    public Tube(float x) {
        topTube=new Texture("toptube.png");
        bottomTube=new Texture("bottomtube.png");
    rand=new Random();

    posTopTube=new Vector2(x,rand.nextInt(FLUCTUACTION)+TUBE_GAP+LOWEST_OPENING);
    posBottomTube=new Vector2(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());

    boundsTop=new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
    boundsBottom=new Rectangle(posBottomTube.x,posBottomTube.y,bottomTube.getWidth(),bottomTube.getHeight());





    }

    public void  reposition(float x)
    {
        posTopTube.set(x,rand.nextInt(FLUCTUACTION)+TUBE_GAP+LOWEST_OPENING);
        posBottomTube.set(x,posTopTube.y-TUBE_GAP-bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x,posBottomTube.y);
    }

    public boolean collides(Rectangle player)
    {
      return player.overlaps(boundsTop)||(player.overlaps(boundsBottom));
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
