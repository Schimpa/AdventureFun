package com.adventure.fun.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Dennis on 31.10.2015.
 */
public abstract class AudioController {

    public static Sound sound_shoot = Gdx.audio.newSound(Gdx.files.internal("android/assets/audio/shoot.ogg"));

    public static Sound sound_jump = Gdx.audio.newSound(Gdx.files.internal("android/assets/audio/jump.ogg"));
}
