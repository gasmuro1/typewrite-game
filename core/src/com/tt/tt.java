package com.tt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class tt extends Game {
	/*SpriteBatch batch;
	Map<String, Sprite> sprites;
	TextureAtlas textureAtlas;*/
	PartidaScreen ps=null;
	FinalScreen fs=null;
	NivelScreen ns=null;
	VelocidadScreen vs=null;

    int puntuacion;
    int nivel;
    public int getNivel()
	{
		return nivel;
	}
	@Override
	public void create () {
		//setScreen(new BienvenidaScreen(this));
		nivel=0;
		ps = new PartidaScreen(this);
		fs=new FinalScreen(this);
		ns = new NivelScreen(this);
		vs = new VelocidadScreen(this);

		setScreen(ps);
		//ns = new NivelScreen(this);

	}


}
