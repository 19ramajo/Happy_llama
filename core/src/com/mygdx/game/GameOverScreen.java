package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by oscar on 23/02/2017.
 */

public class GameOverScreen extends PantallaPrincipal {

    private Stage stage;
    private Skin skin;
    private Image gameOver;
    private Image image;
    private TextButton reintentar;
    private TextButton volverMenu;
    private Sound risa;
    private Label puntuacion;


    public GameOverScreen(final GameActivity game) {
        super(game);
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        gameOver = new Image(game.getManager().get("gameOver.png", Texture.class));
        risa = game.getManager().get("bebeRisa.mp3");
        image = new Image(game.getManager().get("image.png", Texture.class));
        volverMenu = new TextButton("Menu", skin);
        volverMenu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.menuPrincipal);
            }
        });

        reintentar = new TextButton("Reintentar", skin);
        reintentar.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MyGdxGame(game));
            }
        });
        puntuacion = new Label("Puntuacion: " +game.pref.getInteger("Puntuacion"), skin);
        puntuacion.setColor(Color.WHITE);
        puntuacion.setFontScale(2,2);
        puntuacion.setPosition(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2);
        image.setPosition(Gdx.graphics.getWidth() / 4.2f , Gdx.graphics.getHeight() / 2.2f);
        gameOver.setPosition(Gdx.graphics.getWidth() / 4.5f - gameOver.getWidth() /4, Gdx.graphics.getHeight() / 2);
        gameOver.setSize(Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight() / 2);
        reintentar.setSize(Gdx.graphics.getWidth() / 3.5f, gameOver.getHeight() / 6);
        reintentar.setPosition(Gdx.graphics.getWidth() / 5 + reintentar.getWidth() / 2, Gdx.graphics.getHeight()  / 4);
        volverMenu.setSize(Gdx.graphics.getWidth() / 3.5f , gameOver.getHeight() / 6);
        volverMenu.setPosition(Gdx.graphics.getWidth() / 5 + volverMenu.getWidth() / 2, Gdx.graphics.getHeight() /8);
        stage.addActor(gameOver);
        stage.addActor(reintentar);
        stage.addActor(volverMenu);
        stage.addActor(image);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        risa.play();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        puntuacion = new Label("Puntuacion: " +game.pref.getInteger("Puntuacion"), skin);
        puntuacion.setColor(Color.WHITE);
        puntuacion.setFontScale(2,2);
        puntuacion.setPosition(Gdx.graphics.getWidth()/2.8f, Gdx.graphics.getHeight()-puntuacion.getHeight()*8);
        stage.addActor(puntuacion);
        stage.draw();
    }
}
