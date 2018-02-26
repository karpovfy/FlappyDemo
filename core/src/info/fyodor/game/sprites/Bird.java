package info.fyodor.game.sprites;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
/**
 * Created by dom on 03.01.2018.
 */

public class Bird {
    public static final int Gravity=-15;

    public static final int MOVEMENT=100;
    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private Animation birdAnimation;
    private Rectangle bounds;

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    private  Vector3 position;
    private Vector3 velosity;
    //private Texture bird;
    private Sound fly;
    private Texture texture;
    public Bird(int x,int y) {
        position=new Vector3(x,y,0);
        velosity=new Vector3(0,0,0);
        //bird=new Texture("bird.png");
      texture=new Texture("birdanimation.png");
        birdAnimation=new Animation(new TextureRegion(texture),3,0.5f);
        bounds=new Rectangle(x,y,texture.getWidth()/3,texture.getHeight());
        fly= Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }

    /*public void setBird(TextureRegion bird) {
        this.texture = texture;
    }*/

    public void update(float dt)
    {
        birdAnimation.update(dt);
        if(position.y>0)
        velosity.add(0,Gravity,0);
        velosity.scl(dt);
        position.add(MOVEMENT*dt,velosity.y,0);

        if(position.y<0)
        {
            position.y=0;
           // System.exit(0);
        }

        velosity.scl(1/dt);
        bounds.setPosition(position.x,position.y);

    }

    public  void jump()
    {
        velosity.y=160;
        fly.play();
    }

    public void dispose() {
        texture.dispose();
        fly.dispose();
       // System.out.println("bird dispose");
    }
}
