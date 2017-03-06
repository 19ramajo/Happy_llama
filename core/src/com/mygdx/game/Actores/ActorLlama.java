package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by oscar on 04/03/2017.
 */

public class ActorLlama extends Actor {
    public Texture llama;
    int x;
    int y= Gdx.graphics.getHeight();



    public ActorLlama(Texture llama){
        this.llama = llama;
        setSize(llama.getWidth(),llama.getHeight());
        x = MathUtils.random(1,Gdx.graphics.getWidth()-llama.getWidth());
    }


    @Override
    public void act(float delta) {
        y = y - 8;
        if(y < 0 - llama.getHeight()){
            y = Gdx.graphics.getHeight()+200;
        }
        setX(x);
        setY(y);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(llama,x,y);
    }
}
