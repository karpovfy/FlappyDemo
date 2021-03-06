package info.fyodor.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import info.fyodor.game.FlappyDemo;

/**
 * Created by dom on 20.12.2017.
 */

public class MenuState extends States {

    private Texture background;
    private Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false,FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        background=new Texture("bg.png");
        playBtn=new Texture("playbtn.png");


    }

    @Override
    protected void handleInput() {
     if(Gdx.input.justTouched())
     {
         gsm.set(new PlayState(gsm));
     }
    }

    @Override
    public void update(float dt) {
      handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
       // sb.draw(background,0,0, FlappyDemo.WIDTH,FlappyDemo.HEIGHT);
        sb.draw(background,0,0);
        sb.draw(playBtn,camera.position.x-playBtn.getWidth()/2,camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {

        background.dispose();
        playBtn.dispose();
        System.out.println("menu dispose");
    }
}
