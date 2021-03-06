package info.fyodor.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by dom on 19.12.2017.
 */

public class GameStateManager {

    private Stack<States> states;

    public GameStateManager()
    {
        states=new Stack<States>();
    }

    public void push(States state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop().dispose();
    }

    public void set(States state)
    {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
}
