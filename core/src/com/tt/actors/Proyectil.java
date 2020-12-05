package com.tt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class Proyectil extends  Label {
    private String cadena;
    private String letra;
    private BitmapFont myFont;
    private Label texto;
    private float level;
    private char key;
    public Proyectil(CharSequence letra, LabelStyle style, float lineHeight,char key)
    {
        super(letra,style);
        this.letra=letra.toString();
        this.key=key;
        setSize(Gdx.graphics.getWidth(),lineHeight);
        setFontScale(2);
        setAlignment(Align.left);
        this.level=level;

    }
    public char getKey()
    {
        return key;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        this.setText(letra);
        setY(getY()+500*delta);
    }




}
