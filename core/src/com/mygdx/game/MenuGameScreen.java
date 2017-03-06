package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

public class MenuGameScreen extends PantallaPrincipal {

    private Stage stage;
    private Skin skin;
    private Label portada;
    private Image image;
    private TextButton iniciar;
    private TextButton salir;



    public MenuGameScreen(final GameActivity game) {
        super(game);
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        image = new Image(game.getManager().get("portada.png", Texture.class));
        portada = new Label("Llama happy", skin);
        iniciar = new TextButton("Jugar", skin);
        iniciar.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MyGdxGame(game));
            }
        });

        salir = new TextButton("Salir", skin);
        salir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.exit(0);
            }
        });

        image.setPosition(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight() - image.getHeight()*1.2f);
        portada.setPosition(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight() - portada.getHeight()*5);
        portada.setFontScale(3.5f,3.5f);
        portada.setColor(Color.YELLOW);
        iniciar.setSize(Gdx.graphics.getWidth() / 3, 100);
        iniciar.setPosition(Gdx.graphics.getWidth() / 4 - iniciar.getWidth() / 2, Gdx.graphics.getHeight()  / 13);
        salir.setSize(Gdx.graphics.getWidth() / 3, 100);
        salir.setPosition(Gdx.graphics.getWidth() /4 + salir.getWidth(), Gdx.graphics.getHeight()  / 13);
        stage.addActor(portada);
        stage.addActor(iniciar);
        stage.addActor(salir);
        stage.addActor(image);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}

