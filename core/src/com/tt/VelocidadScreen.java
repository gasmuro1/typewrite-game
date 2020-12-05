package com.tt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.tt.actors.Nave;
import com.tt.actors.Proyectil;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

//import com.sun.xml.internal.ws.api.model.wsdl.WSDLDescriptorKind;

public class VelocidadScreen extends BaseScreen {
    private Label  lScore;
    private Label.LabelStyle label1Style;
    private Label lTexto;
    BitmapFont myFont;
    BitmapFont scoreFont;
    //private String textohacer="ASDF";
    private String textohacer="ASDFASDFASDFASDFASDFASDFAÑLKJÑLKJÑLKJÑLKJÑLKJASDFASDFÑLKJÑLKJÑLKJÑLKJÑLKJASDFÑLKJAASDKFJKALDÑALDKJFADASDKDJFASDFÑLK";
    private int progreso=0;
    private int  vidas=10;
    private float tiempo=0;
    Nave nave;
    int lineHeight=Gdx.graphics.getHeight()/20;
    private HashMap<Integer, String> textos;

    public VelocidadScreen(tt game) {
        super(game);
        textos=new HashMap<Integer,String>();
        textos.put (1,"ASDF ASDF ASDF ASDF ASDF ASDFA ÑLKJ ÑLKJ ÑLKJ ÑLKJ ÑLKJ ASDF ASDF\n" +
                      "ÑLKJ ÑLKJ ÑLKJ ÑLKJ ASDF ÑLKJA ASA FASA SASA ÑAÑA LALA KASA DAJA\n" +
                      "DSAFJKLÑJKLÑ FDSAFD SAJKLÑJKLÑAS FDSAFASAFAD AFDKFJADKFJA SKDJFF\n");
        textos.put (3,"ASDFG ASDFG ASDFG ASDFG ÑLKJH ÑLKJH ÑLKJH ÑLKJH  ASA ASA GASA\n" +
                      "SALA LASA LADA JADAS SADA KASA ÑAÑA ÑAÑA FASA SAFA FAFA GAFA\n"+
                      "SALA LASA LADA JADAS SADA KASA ÑAÑA ÑAÑA FASA SAFA FAFA GAFA");

        //  textureAtlas=new TextureAtlas("things1.txt");

    }
    private Stage stage;

    public void loadScore()
    {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        scoreFont = new BitmapFont();
        label1Style.font = scoreFont;
        label1Style.fontColor = Color.RED;

        int x=0;
        int y=1;
        lScore=new Label(String.format("Puntos : %d",game.puntuacion), label1Style);
        lScore.setSize(Gdx.graphics.getWidth()/2,lineHeight);
        lScore.setFontScale(1);
        lScore.setPosition(x,Gdx.graphics.getHeight()-lineHeight);
        stage.addActor(lScore);

    }

    @Override
    public void show() {
        stage=new Stage();
        loadScore();
        myFont = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fake.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        myFont = generator.generateFont(parameter);
        generator.dispose();
        textohacer=">"+textos.get(game.nivel)+"<";
        Label.LabelStyle label1Style = new Label.LabelStyle();
        scoreFont = new BitmapFont();
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLACK;

        lTexto=new Label(String.format(textohacer), label1Style);
        lTexto.setSize(Gdx.graphics.getWidth()-10,4*lineHeight);
        lTexto.setFontScale(1);
        lTexto.setPosition(10,Gdx.graphics.getHeight()-12*lineHeight);
        stage.addActor(lTexto);


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char character) {
                System.out.println(String.format("%s",character));
                avanzar(character);
                return super.keyTyped(character);
            }


        });


    }



    private void avanzar(char key){
        System.out.println(textohacer.toLowerCase().charAt(progreso+1));
        if (key==textohacer.toLowerCase().charAt(progreso+1))
        {

            textohacer=textohacer.replaceFirst(">","_");
            textohacer=textohacer.replaceFirst(String.format("%c",key).toUpperCase(),">");
            progreso ++;
            game.puntuacion++;
            lScore.setText (String.format("Puntos: %d",game.puntuacion));
            if (textohacer.charAt(progreso+1)=='\n')
            {
                System.out.println("hola");
                progreso ++;
            }
       /* for (String guns1:guns) {
            if (guns1.indexOf(key) != -1) ;
            {
                String aux = guns1.replace(String.format("%c", key).toUpperCase(), "|");
                aux = aux.replaceAll("[^||]", " ");
                Proyectil p = new Proyectil(aux, label1Style, lineHeight, key);
                p.setPosition(0, lineHeight * 2);
                proyectiles.add(p);
                stage.addActor(p);
            }*/
        }
    }


    @Override
    public void hide() {

       stage.dispose();
    }


    public boolean comprobarColision()
    {
        boolean ret=false;
        if (progreso+2>textohacer.length())
        {
            ret=true;
        }
        return ret;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        tiempo+=delta;
        if (tiempo>1)
        {
            tiempo=0;
            textohacer= textohacer.substring(0,textohacer.length()-2)+"<";
        }
        if (comprobarColision())
            game.setScreen(game.ns);
           lTexto.setText(textohacer);

        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        myFont.dispose();
        scoreFont.dispose();
    }
}
