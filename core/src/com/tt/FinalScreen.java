package com.tt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class FinalScreen extends BaseScreen {

    private Stage stage;
    private Skin skin;
    public FinalScreen(tt  game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void dispose() {
        skin.dispose();
    }

    @Override
    public void show() {
        System.out.println("PANTALLA FIN");
        int lineHeight=Gdx.graphics.getHeight()/10;
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        skin= new Skin(Gdx.files.internal("uiskin.json"));
        Label linea1=new Label("FIN DE PARTIDA",skin);
        linea1.setSize(Gdx.graphics.getWidth(),lineHeight);
        linea1.setFontScale(2f);
        linea1.setPosition(0,Gdx.graphics.getHeight()-lineHeight*2);
        linea1.setAlignment(Align.center);
        stage.addActor(linea1);

        Label linea2 =new Label(String.format("PUNTUACION:%s",game.puntuacion),skin);
        linea2.setSize(Gdx.graphics.getWidth()/2,lineHeight);
        linea2.setFontScale(1f);
        linea2.setPosition(0,Gdx.graphics.getHeight()-lineHeight*4);
        linea2.setAlignment(Align.right);
        stage.addActor(linea2);
        TextButton btnContinuar =new TextButton("Continuar",skin);
        btnContinuar.setPosition(Gdx.graphics.getWidth()/2-300,Gdx.graphics.getHeight()-lineHeight*6);
        btnContinuar.setSize(300,lineHeight);
        btnContinuar.getLabel().setFontScale(3);
        btnContinuar.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                   game.puntuacion=0;
                   game.nivel=-1;
                   game.setScreen(game.ns);
            }
        });
        stage.addActor(btnContinuar);

        TextButton btnLogin =new TextButton("Salir",skin);
        btnLogin.setPosition(Gdx.graphics.getWidth()/2+10,Gdx.graphics.getHeight()-lineHeight*6);
        btnLogin.setSize(300,lineHeight);
        btnLogin.getLabel().setFontScale(3);
        btnLogin.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });
        stage.addActor(btnLogin);
       // game.borrarJugador();
    }



}
