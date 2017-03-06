package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GameActivity extends Game {

	public MyGdxGame myGdxGame;
	public MenuGameScreen menuPrincipal;
	public GameOverScreen gameOverScreen;
	private AssetManager manager;
	public Preferences pref;

	public AssetManager getManager(){
		return manager;
	}

	@Override
	public void create(){
		pref= Gdx.app.getPreferences("My Preferences");
		manager = new AssetManager();
		manager.load("music.mp3", Music.class);
		manager.load("grito.mp3", Sound.class);
		manager.load("bebeRisa.mp3", Sound.class);
		manager.load("portada.png", Texture.class);
		manager.load("gameOver.png", Texture.class);
        manager.load("image.png", Texture.class);
		manager.finishLoading();

		myGdxGame = new MyGdxGame(this);
		menuPrincipal = new MenuGameScreen(this);
		gameOverScreen = new GameOverScreen(this);
		setScreen(menuPrincipal);
	}
}
