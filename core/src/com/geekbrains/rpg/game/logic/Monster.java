package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.Poolable;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class Monster extends GameCharacter implements Poolable {
    private float attackTime;
    private Vector2 tmp3;

    public Monster(GameController gc) {
        super(gc, 20, 100.0f);
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.changePosition(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.tmp3 = new Vector2(0.0f, 0.0f);
    }

    @Override
    public void onDeath() {
      this.hp = 0;
    }

    public void respawn() {
        this.position.set(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        this.hp = this.hpMax;
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        batch.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void update(float dt) {
        super.update(dt);
        if (this.position.dst(gc.getHero().getPosition()) <= 300) {
            hunt(dt);
        } else {
            walking(dt);
        }
    }

    public void walking(float dt) {
        dst.set(tmp3);
        if (this.position.dst(tmp3) < 30) {
            this.tmp3.set(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        }
    }

    public void hunt(float dt) {
        dst.set(gc.getHero().getPosition());
        if (this.position.dst(gc.getHero().getPosition()) < 40) {
            attackTime += dt;
            if (attackTime > 0.3f) {
                attackTime = 0.0f;
                gc.getHero().takeDamage(1);
            }
        }
    }

    @Override
    public boolean isActive() {
        return hp > 0;
    }
}
