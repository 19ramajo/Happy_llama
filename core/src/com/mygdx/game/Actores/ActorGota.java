package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by oscar on 04/03/2017.
 */

public class ActorGota extends Actor {
    public Texture gota;
    int x;
    int y= Gdx.graphics.getHeight();


    public ActorGota(Texture gota){
        this.gota = gota;
        setSize(gota.getWidth(),gota.getHeight());
        x = MathUtils.random(1,Gdx.graphics.getWidth()-gota.getWidth());
    }

    @Override
    public void act(float delta) {
        y = y - 8;

        if(y < 0 - gota.getHeight()){
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
        batch.draw(gota,x,y);
    }
}
