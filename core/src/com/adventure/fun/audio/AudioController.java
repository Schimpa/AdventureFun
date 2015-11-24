package com.adventure.fun.audio;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Sound;

/**
 * Created by Dennis on 31.10.2015.
 */
public class AudioController {

    public static Sound sound_shoot_01 = Gdx.audio.newSound(Gdx.files.internal("audio/shoot_01.ogg"));
    public static Sound sound_shoot_02 = Gdx.audio.newSound(Gdx.files.internal("audio/shoot_02.ogg"));

    public static Sound sound_step_01 = Gdx.audio.newSound(Gdx.files.internal("audio/step01.ogg"));
    public static Sound sound_step_02 = Gdx.audio.newSound(Gdx.files.internal("audio/step02.ogg"));


    public static Sound sound_jump = Gdx.audio.newSound(Gdx.files.internal("audio/jump.ogg"));

    public static Sound sound_die = Gdx.audio.newSound(Gdx.files.internal("audio/papierstau.ogg"));
}
