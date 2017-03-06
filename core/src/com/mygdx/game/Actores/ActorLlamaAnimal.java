package com.mygdx.game.Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by oscar on 04/03/2017.
 */

public class ActorLlamaAnimal extends Actor {
    int x,y;


    public Texture llamaAnimal;

    public ActorLlamaAnimal(Texture llamaAnimal){
        this.llamaAnimal = llamaAnimal;
        setSize(llamaAnimal.getWidth(),llamaAnimal.getHeight());
    }


    @Override
    public void act(float delta) {
        x = x - (int) Gdx.input.getAccelerometerX();

        if(x > Gdx.graphics.getWidth() -llamaAnimal.getWidth()){
            x = (Gdx.graphics.getWidth() -llamaAnimal.getWidth());
        }
        if (x + (int)Gdx.input.getAccelerometerX() < 0){
            x = 0;
        }
        x = x - (int)Gdx.input.getAccelerometerX();
        setX(x);
        setY(y);
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(llamaAnimal,this.x,y);
    }
}
