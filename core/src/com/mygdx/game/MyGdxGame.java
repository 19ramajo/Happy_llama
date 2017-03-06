package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Actores.ActorGota;
import com.mygdx.game.Actores.ActorLlama;
import com.mygdx.game.Actores.ActorLlamaAnimal;

public class MyGdxGame extends PantallaPrincipal {

	private Stage stage;
	private Music musica;
	private Sound grito;
	private ActorLlamaAnimal llamaAnimal;
	private ActorGota gota;
	private ActorLlama llama;
	private Texture textureLlamaAnimal;
	private Texture textureGota;
	private Texture textureLlama;
	private Label lPuntos;
	private int p, Puntuacion;
	private Array<ActorLlama> listLlamas = new Array<ActorLlama>();
	private Array<ActorGota> listGotas = new Array<ActorGota>();
	private Skin skin;


	public MyGdxGame(GameActivity game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		Puntuacion=0;
		textureLlamaAnimal = new Texture("llama.png");
		textureGota = new Texture("gota.png");
		textureLlama = new Texture("fuego.png");
		musica = game.getManager().get("music.mp3");
		grito = game.getManager().get("grito.mp3");
		lPuntos = new Label("Puntos : "+Puntuacion, skin);
		lPuntos.setColor(Color.BLUE);
		lPuntos.setFontScale(2,2);
		lPuntos.setPosition(Gdx.graphics.getWidth()/2.5f,Gdx.graphics.getHeight()-lPuntos.getHeight());
	}



	@Override
	public void show() {
		Gdx.gl.glClearColor(0.5f,0,0.5f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage = new Stage();
		llamaAnimal = new ActorLlamaAnimal(textureLlamaAnimal);
		stage.addActor(llamaAnimal);
		gota = new ActorGota(textureGota);
		listGotas.add(gota);
		stage.addActor(gota);
		//llama = new ActorLlama(textureLlama);
		//listLlamas.add(llama);
		//stage.addActor(llama);
		stage.addActor(lPuntos);
		musica.setVolume(0.50f);
		musica.play();
	}

	@Override
	public void hide() {
		stage.clear();
		stage.dispose();
	}


	@Override
	public void render(float delta) {
		p = MathUtils.random(1, 150);
		stage.act();
		comprobarColision();
		comprobarColisionGota();
		probabilidadLlamas();
		stage.draw();
	}

	public void comprobarColision() {
		Rectangle llamaAnimalColision = new Rectangle();
		Rectangle llamaColision = new Rectangle();

		llamaAnimalColision.set(llamaAnimal.getX(), llamaAnimal.getY(), llamaAnimal.getWidth(), llamaAnimal.getHeight());
		for (ActorLlama llama : listLlamas) {
			llamaColision.set(llama.getX(), llama.getY(), llama.getWidth(), llama.getHeight());
			if (llamaAnimalColision.overlaps(llamaColision)) {
				lPuntos.remove();
				textureLlama.dispose();
				textureGota.dispose();
				textureLlamaAnimal.dispose();
				llama.remove();
				llamaAnimal.remove();
				gota.remove();
				textureLlama.dispose();
				Gdx.gl.glClearColor(0,0,0,0);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				musica.stop();
				stage.addAction(
						Actions.sequence(
								Actions.delay(2.0f),
								Actions.run(new Runnable() {
									@Override
									public void run() {
										game.setScreen(new GameOverScreen(game));
										game.pref.putInteger("Puntuacion" ,Puntuacion);
										game.pref.flush();
									}
								})
						)
				);
			}
			if (llamaColision.getY() == 0) {
				llama.remove();
				listLlamas.removeValue(llama,true);
				llama = new ActorLlama(textureLlama);
				listLlamas.add(llama);
				stage.addActor(llama);
			}
		}
	}

	public void comprobarColisionGota() {
		Rectangle llamaAnimalColision = new Rectangle();
		Rectangle gotaColision = new Rectangle();

		llamaAnimalColision.set(llamaAnimal.getX(), llamaAnimal.getY(), llamaAnimal.getWidth(), llamaAnimal.getHeight());
		for (ActorGota gota : listGotas) {
			gotaColision.set(gota.getX(), gota.getY(), gota.getWidth(), gota.getHeight());
			if (llamaAnimalColision.overlaps(gotaColision)) {
				Puntuacion += 2;
				lPuntos.setText("Puntos : "+ Puntuacion);
				grito.play();
				gota.remove();
				listGotas.removeValue(gota,true);
				gota = new ActorGota(textureGota);
				listGotas.add(gota);
				stage.addActor(gota);
			}
		}
	}

	private void probabilidadLlamas() {
				if(p == 60){
					llama = new ActorLlama(textureLlama);
					listLlamas.add(llama);
					stage.addActor(llama);
				}
	}

	@Override
	public void dispose() {
		stage.dispose();
		stage.clear();
		grito.dispose();
	}
}
