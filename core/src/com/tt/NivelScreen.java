package com.tt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class NivelScreen extends BaseScreen {
    private Stage stage;
    private Skin skin;
    private SelectBox nivelSelectBox;
    private SelectBox modoSelectBox;
    private SelectBox njugadoresBox;
    private Label estadoLabel;
    private float retryEstado=0;
    public NivelScreen(tt game) {
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
        int lineHeight=Gdx.graphics.getHeight()/10;
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        skin= new Skin(Gdx.files.internal("uiskin.json"));
        Label linea1=new Label("Bien hecho pasamos de nivel ",skin);
        linea1.setSize(Gdx.graphics.getWidth(),lineHeight);
        linea1.setFontScale(3f);
        linea1.setPosition(0,Gdx.graphics.getHeight()-lineHeight*2);
        linea1.setAlignment(Align.center);
        stage.addActor(linea1);

     /*   Label linea2 =new Label(String.format("Nivel: %d",++game.nivel),skin);
        linea2.setSize(Gdx.graphics.getWidth()/2,lineHeight);
        linea2.setFontScale(1);
        linea2.setPosition(0,Gdx.graphics.getHeight()-lineHeight*4);
        linea2.setAlignment(Align.right);
        stage.addActor(linea2);*/

        TextButton btnLogin =new TextButton(String.format("Nivel: %d",++game.nivel),skin);
        btnLogin.getLabel().setFontScale(1);
        btnLogin.setPosition(Gdx.graphics.getWidth()/2-150,Gdx.graphics.getHeight()-lineHeight*4);
        btnLogin.setSize(200,lineHeight);
        btnLogin.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                cambiarNivel();
            }
        });
        stage.addActor(btnLogin);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char character) {
                 cambiarNivel();
                 return super.keyTyped(character);
            }
        });


    }
    public void cambiarNivel(){
        if (game.nivel==10)
            game.setScreen(game.fs);
        else if (game.nivel%2==0)
            game.setScreen(game.ps);
        else
            game.setScreen(game.vs);


    }
}
