package com.geekbrains.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class Apple {
    private TextureRegion textureRegion;
    private Vector2 position;
    private boolean isAlive;


    public Apple(TextureAtlas atlas) {
        this.textureRegion = atlas.findRegion("apple");
        this.isAlive = false;
    }

    public void render(SpriteBatch batch) {
        if (isAlive) {
            batch.draw(textureRegion, position.x - 32, position.y - 32);
        }
    }
    private void  changePosition() {
        int x = 32 + (int) (Math.random() * 1240);
        int y = 32 + (int) (Math.random() * 680);
        this.position = new Vector2(x, y);
        this.isAlive = true;
    }

    public void update(float dt) {
        if (!isAlive) {
           changePosition();
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void crash() {
        this.isAlive = false;
    }
}
