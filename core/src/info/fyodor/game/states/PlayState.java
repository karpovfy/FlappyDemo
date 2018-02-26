package info.fyodor.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import info.fyodor.game.FlappyDemo;
import info.fyodor.game.sprites.Bird;
import info.fyodor.game.sprites.Tube;

/**
 * Created by dom on 03.01.2018.
 */

public class PlayState extends States {
   // private Texture bird;
    public static final int TUBE_SPACING=125;
    public static final int TUBE_COUNT=4;
    public static final int GROUND_Y_OFFSET=-30;
    private Bird bird;
    private Texture bg;
    private Texture ground;
  //  private Tube tube;
    private Vector2 groundPos1,groundPos2;
    private Array<Tube> tubes;
    int Points=0;
    BitmapFont font;
    private int points;

    public PlayState(GameStateManager gsm) {
        super(gsm);
         font = new BitmapFont();
        bird=new Bird(50,300);
       // bird=new Texture("bird.png");
        bg=new Texture("bg.png");
        ground=new Texture("ground.png");
        groundPos1=new Vector2(camera.position.x-camera.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2=new Vector2((camera.position.x-camera.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);

     //   tube=new Tube(100);
        tubes=new Array<Tube>();
        for(int i=0;i<TUBE_COUNT;i++)
        {
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));

        }
        camera.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
    bird.update(dt);
    camera.position.x=bird.getPosition().x+80;
    for(int i=0;i<tubes.size;i++)
    {
        Tube tube=tubes.get(i);
        if(camera.position.x-(camera.viewportWidth/2)>tube.getPosTopTube().x
            +tube.getTopTube().getWidth())
        {
            tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH+TUBE_SPACING)*TUBE_COUNT));
            Points++;
        }

        if(tube.collides(bird.getBounds()))
        {
            gsm.set(new GameOver(gsm));
        }

    }
    camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg,camera.position.x-(camera.viewportWidth/2),0);
       // sb.draw(bird,50,50);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        //sb.draw(tube.getTopTube(),tube.getPosBottomTube().x,tube.getPosBottomTube().y);
       // sb.draw(tube.getBottomTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);

        for(Tube tube:tubes)
        {
            sb.draw(tube.getBottomTube(),tube.getPosBottomTube().x,tube.getPosBottomTube().y);
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
        }
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);

        sb.end();


        CharSequence str = String.valueOf("Point="+Points);
        SpriteBatch spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(0.1f,0.4f,0.8f,1);
        font.getData().setScale(3f);

        spriteBatch.begin();
        font.draw(spriteBatch, str, FlappyDemo.WIDTH/3, GROUND_Y_OFFSET+100);
        spriteBatch.end();
        spriteBatch.begin();

        spriteBatch.end();


    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
       for(Tube tube:tubes)
       {
           tube.dispose();
       }
       ground.dispose();
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }


    private void updateGround()
    {
       if(camera.position.x-(camera.viewportWidth/2)>groundPos1.x+ground.getWidth())
       {
            groundPos1.add(ground.getWidth()*2,0);
       }

        if(camera.position.x-(camera.viewportWidth/2)>groundPos2.x+ground.getWidth())
        {
            groundPos2.add(ground.getWidth()*2,0);
        }
    }

}
