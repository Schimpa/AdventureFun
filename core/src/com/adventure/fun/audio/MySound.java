package com.adventure.fun.audio;

import com.badlogic.gdx.audio.Sound;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Dennis on 21.03.2016.
 */
public class MySound implements Serializable {

    private Sound sound;

    public MySound(Sound sound){
        this.sound = sound;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }


}
