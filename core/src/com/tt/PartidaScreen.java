package com.tt;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;
import com.tt.actors.Nave;
import com.tt.actors.Proyectil;

import java.util.*;

//import com.sun.xml.internal.ws.api.model.wsdl.WSDLDescriptorKind;

public class PartidaScreen extends BaseScreen {
    private Label  lScore;
    private ArrayList <Label> lGuns ;
    private Label.LabelStyle label1Style;
    private Label lVidas;
    BitmapFont myFont;
    BitmapFont scoreFont;
    //private String textohacer="ASDF";
    private HashMap<Integer,String> textos;
    private String textohacer="";
    private int progreso=0;
    private int  vidas=10;
    private List <Proyectil> proyectiles;
    private ArrayList <String> guns=null;
    Nave nave;
    int lineHeight=Gdx.graphics.getHeight()/20;

    private String[]  aGuns= {
        " Q    W   E   R   T   Y   U   I   O   P",
        "   A   S   D   F   G   H   J   K   L   Ñ",
        "     Z  X   C   V    B    N  M   ,   .   -"};
    public PartidaScreen(tt game) {
        super(game);
        textos=new HashMap<Integer,String>();
        textos.put (0,"ASDFASDFASDFASDFASDFASDFAÑLKJÑLKJÑLKJÑLKJÑLKJASDFASDFÑLKJÑLKJÑLKJÑLKJÑLKJASDFÑLKJAASDKFJKALDÑALDKJFADASDKDJFASDFÑLK");
        textos.put (2,"ASDFGASDFGASDFGASDFGÑLKJHÑLKJHÑLKJHÑLKJHGHGHGFDSAGFDSAHJKLÑHJKLÑGFDSALSKDJFHGJGHFHGJGHGHFHGASDFGHJKLÑLLKJHAHSHDKALA");
        textos.put (4,"QWERQWERQWERPOIUPOIUPOIUASDFÑLKJASDFQWERÑLKJPOSOLOSALISALISOLUISDEHESASARAQUESOESOHIJOHILOPULLPISOGUISOFRISOFRISOGUI");
        textos.put (6,"ZXDVB-.,MNÑLKJASDFQWERTPOIUYPOIUYPOIUY-.,MNÑLKJHASDFGQWERTPOIUYZXCVB-.,MNÑLKJHGASDFQWERTUYAKDJFCMUAÑSOIEURTYAHAÑSOFIANAOENAOWEHCMNAFZXCVA-.,DJQPEORUA");
        textos.put (8,"MARCOSDIEGOGASPARCRISTINAROSARIOANTONIOMANOLIANDRESESMERALDAOLMOJOSEGEMADANIELLUCIAZAPATOXILOFONVACABACAQUITAMOSALGODONWISKI");

        //  textureAtlas=new TextureAtlas("things1.txt");

    }
    private Stage stage;
    /*private void reloadTarjetas() {
        int i=0;
        for (Objeto objeto : game.getJugador().getTarjeta().getObjetos()) {
            tarjetaJugador.get(i).setName (objeto.getId()); textureAtlas.createSprite(objeto.getAsset());
            tarjetaJugador.get(i).setObjetoSprite(textureAtlas.createSprite(objeto.getAsset()));
            i++;
        }
        i=0;
        for (Objeto objeto : game.getJugador().getTarjetaEnJuego().getObjetos()) {
            System.out.println("tarjeta en juego");
            tarjetaBaraja.get(i).setName( objeto.getId());
            tarjetaBaraja.get(i).setObjetoSprite(textureAtlas.createSprite(objeto.getAsset()));
            i++;
        }
    }

    private void loadObjetos(){
        tarjetaJugador=new ArrayList<ActorObjeto>();
        for (Objeto objeto:game.getJugador().getTarjeta().getObjetos())
        {
            tarjetaJugador.add(new ActorObjeto(game,objeto.getId(), textureAtlas.createSprite(objeto.getAsset())));
        }
        tarjetaBaraja=new ArrayList<ActorObjeto>();
        for (Objeto objeto:game.getJugador().getTarjetaEnJuego().getObjetos())
        {   System.out.println("tarjeta en juego");
            tarjetaBaraja.add(new ActorObjeto(game,objeto.getId(), textureAtlas.createSprite(objeto.getAsset())));
        }
        textureCarta=new Texture(Gdx.files.internal("carta.png"));
        cartas=new Carta[2];
        cartas[0]=new Carta(textureCarta);
        cartas[1]=new Carta(textureCarta);


    }
*/
    public void loadScore()
    {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        scoreFont = new BitmapFont();
        label1Style.font = scoreFont;
        label1Style.fontColor = Color.RED;

        int x=0;
        int y=1;
        lVidas=new Label(String.format("Vidas:%d",vidas),label1Style);
        lVidas.setSize(Gdx.graphics.getWidth()/2,lineHeight);
        lVidas.setFontScale(1);
        lVidas.setPosition(x+10,Gdx.graphics.getHeight()-lineHeight);
        x=Gdx.graphics.getWidth()/2;
        lVidas.setAlignment(Align.left);
        stage.addActor(lVidas);
        lScore=new Label(String.format("Puntos :%d    Quedan :%d ",game.puntuacion, textohacer.length()-progreso), label1Style);
        lScore.setSize(Gdx.graphics.getWidth()/2,lineHeight);
        lScore.setFontScale(1);
        lScore.setPosition(x,Gdx.graphics.getHeight()-lineHeight);
        stage.addActor(lScore);

    }
    public void loadGuns()
    {
        if (guns==null) {
            guns = new ArrayList<String>();
            lGuns = new ArrayList<Label>();
            label1Style = new Label.LabelStyle();
            label1Style.font = myFont;
            label1Style.fontColor = Color.BLACK;
            for (int i=0;i<3;i++) {
                Label texto = new Label(aGuns[i], label1Style);
                texto.setSize(Gdx.graphics.getWidth(), lineHeight);
                texto.setFontScale(2);
                texto.setPosition(0, lineHeight * (3 - i));
                texto.setAlignment(Align.left);
                lGuns.add(i, texto);
            }

        }
        else
            guns.clear();
        if (game.nivel<4)
        {
            guns.add(aGuns[1]);
            stage.addActor(lGuns.get(1));
        }
        else if (game.nivel==4) {
            guns.add(aGuns[0]);
            stage.addActor(lGuns.get(0));
            guns.add(aGuns[1]);
            stage.addActor(lGuns.get(1));

        }
        else
        {
            guns.add(aGuns[0]);
            stage.addActor(lGuns.get(0));
            guns.add(aGuns[1]);
            stage.addActor(lGuns.get(1));
            guns.add(aGuns[2]);
            stage.addActor(lGuns.get(2));

        }

    }

    @Override
    public void show() {
         progreso=0;
         vidas=10;
        stage=new Stage();
        textohacer=textos.get((Integer) game.getNivel());
        System.out.println("hola "+textohacer);
        System.out.println("nivel"+game.getNivel());
        loadScore();
        proyectiles=new LinkedList<Proyectil>() ;
        myFont = new BitmapFont();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fake.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        myFont = generator.generateFont(parameter);
        generator.dispose();
        loadGuns();

        initNave();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char character) {
                System.out.println(String.format("%s",character));
                if (character!=' ')
                  disparar(character);
                return super.keyTyped(character);
            }


        });


    }


    private void initNave(){
        if (progreso>textohacer.length()-1) {
            game.setScreen(game.ns);
            return;
        }
        char letra=textohacer.charAt(progreso++);
        for (String guns1:guns) {
            if (guns1.indexOf(letra) != -1) {
                String letraNave = guns1.replaceAll(String.format("[^%s ]", letra), " ");
                System.out.println(guns1);
                System.out.println(letraNave);
                if (nave == null) {
                    nave = new Nave(letraNave, label1Style, lineHeight, 100);

                } else
                    nave.setLetra(letraNave);
                nave.setX(0);
                nave.setY(Gdx.graphics.getHeight() - lineHeight * 2);
                stage.addActor(nave);
                break;
            }
        }
    }

    private void disparar(char key){
        for (String guns1:guns) {
            if (guns1.indexOf(key) != -1) ;
            {
                String aux = guns1.replace(String.format("%c", key).toUpperCase(), "|");
                aux = aux.replaceAll("[^||]", " ");
                Proyectil p = new Proyectil(aux, label1Style, lineHeight, key);
                p.setPosition(0, lineHeight * 2);
                proyectiles.add(p);
                stage.addActor(p);
            }
        }
    }


    @Override
    public void hide() {

       stage.dispose();
    }

    public boolean comprobarSuelo()
    {
        return lineHeight*2>nave.getY();
    }

    public boolean comprobarColision()
    {
        float yNave=nave.getY();
        boolean ret=false;
        Iterator<Proyectil> iter = proyectiles.iterator();
        while(iter.hasNext()){
            Proyectil p=iter.next();
            if (p.getY()>yNave && p.getY()<yNave+lineHeight && nave.getLetra().indexOf(String.format("%c",p.getKey()).toUpperCase())!=-1)
            {
                ret=true;
                p.remove();
                iter.remove();
            }
            if (p.getY()>Gdx.graphics.getHeight())
            {
                game.puntuacion--;
                p.remove();
                iter.remove();

            }

        }
        return ret;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        if(comprobarColision())
        {
            game.puntuacion++;
            lScore.setText(String.format("Puntos :%d    Quedan :%d ",game.puntuacion, textohacer.length()-progreso));
            initNave();
        }
        else if (comprobarSuelo())
        {
            vidas--;
            if (vidas==0)
                game.setScreen(game.fs);
            lVidas.setText(String.format("Vidas:%d",vidas));
            initNave();
            System.out.println(vidas);
        }

        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        myFont.dispose();
        scoreFont.dispose();
    }
}
