package info.fyodor.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import info.fyodor.game.FlappyDemo;

/**
 * Created by dom on 07.01.2018.
 */

public class GameOver extends States {
    private Texture background,gameOver;

    public GameOver(GameStateManager gsm) {
        super(gsm);

        camera.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        background=new Texture("bg.png");
        gameOver=new Texture("gameover.png");

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

        sb.draw(background,0,0);
        sb.draw(gameOver,camera.position.x-gameOver.getWidth()/2,camera.position.y);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();

    }
}
