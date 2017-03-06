package com.mygdx.game;

import com.badlogic.gdx.Screen;

/**
 * Created by oscar on 21/02/2017.
 */

public abstract class PantallaPrincipal implements Screen {

    protected GameActivity game;

    public PantallaPrincipal(GameActivity game){
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
