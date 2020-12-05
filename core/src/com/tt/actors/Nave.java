package com.tt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class Nave extends  com.badlogic.gdx.scenes.scene2d.ui.Label {
    private String cadena;
    private String letra;
    private BitmapFont myFont;
    private Label texto;
    private float level;
    public Nave(CharSequence letra, LabelStyle style,float lineHeight,float level)
    {
        super(letra,style);
        this.letra=letra.toString();
        setSize(Gdx.graphics.getWidth(),lineHeight);
        setFontScale(2);
        setAlignment(Align.left);
        this.level=level;

    }
    public String getLetra(){
        return letra;
    }
    public void setLetra(String l){
        letra=l;

    }
    @Override
    public void act(float delta) {
        super.act(delta);
        this.setText(letra);
        setY(getY()-level*delta);
    }




}
