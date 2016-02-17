package com.adventure.fun.effects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dennis on 17.12.2015.
 */
public class ObjectAnimation {

    private Animation animation;
    private TextureRegion[] frames;

    private int lengthX,lengthY,startNum,endNum;
    private float animationSpeed;

    private boolean isActive;

    public void takeParameters(int lengthX,int lengthY,int startNum,int endNum,float animationSpeed){
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.startNum = startNum;
        this.endNum = endNum;
        this.animationSpeed = animationSpeed;
    }

    // TEXTURE - LÄNGE DES BILDES (AB 1) - HÖHE DES BILDES (AB 1) - STARTNUMMER ( AB 0) - ENDNUMMER (AB 0)
    public ObjectAnimation(Texture texture,int lengthX,int lengthY,int startNum,int endNum,float animationSpeed){
        takeParameters(lengthX,lengthY,startNum,endNum,animationSpeed);

        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth()/lengthX, texture.getHeight()/lengthY);

        int tempNum = 0;

        this.frames = new TextureRegion[(endNum-startNum) + 1];
        int index = 0;
        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                if (tempNum >= startNum && tempNum <= endNum){
                    this.frames[index++] = tmp[i][j];
                 }
                tempNum++;
            }
        }

        animation = new Animation(animationSpeed, frames);
        isActive = false;
    }

    public ObjectAnimation(TextureRegion region,int lengthX,int lengthY,int startNum,int endNum,float animationSpeed) {
        takeParameters(lengthX,lengthY,startNum,endNum,animationSpeed);

        TextureRegion[][] tmp = region.split(
                region.getRegionWidth() / lengthX, region.getRegionHeight() / lengthY);

        int tempNum = 0;

        this.frames = new TextureRegion[(endNum - startNum) + 1];
        int index = 0;
        for (int i = 0; i < lengthY; i++) {
            for (int j = 0; j < lengthX; j++) {
                if (tempNum >= startNum && tempNum <= endNum) {
                    this.frames[index++] = tmp[i][j];
                }
                tempNum++;
            }
        }

        animation = new Animation(animationSpeed, frames);
        isActive = false;
    }


    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public TextureRegion[] getFrames() {
        return frames;
    }

    public void setFrames(TextureRegion[] frames) {
        this.frames = frames;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getLengthX() {
        return lengthX;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public int getLengthY() {
        return lengthY;
    }

    public void setLengthY(int lengthY) {
        this.lengthY = lengthY;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public int getEndNum() {
        return endNum;
    }

    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public float getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(float animationSpeed) {
        this.animationSpeed = animationSpeed;
    }
}
