package com.geekbrains.rpg.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Monster {
    private GameScreen gameScreen;
    private TextureRegion texture;
    private TextureRegion textureHp;
    private Vector2 position;
    private Vector2 dst;
    private Vector2 tmp;
    private float lifetime;
    private float attackTime;
    private float speed;
    private int hp;
    private int hpMax;
    private int damage;

    public Monster(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
        this.position = new Vector2(800, 300);
        this.dst = new Vector2(position);
        this.tmp = new Vector2(0, 0);
        this.speed = 100.0f;
        this.hpMax = 30;
        this.hp = 30;
        this.damage = 1;
    }

    public Vector2 getPosition() {
        return position;
    }

    private void respawn() {
        hp = hpMax;
        position.set((float) (50 + Math.random() * 1200), (float) (50 + Math.random() * 640));
    }

    public boolean takeDamage(int amount) {
        hp -= amount;
        if (hp <= 0) {
            respawn();
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(0.5f, 0.5f, 0.5f, 0.7f);
        batch.draw(texture, position.x - 30, position.y - 30, 30, 30, 60, 60, 1, 1, 0);
        batch.setColor(1, 1, 1, 1);
        batch.draw(textureHp, position.x - 30, position.y + 30, 60 * ((float) hp / hpMax), 12);
    }

    public void update(float dt) {
        lifetime += dt;
        tmp.set(gameScreen.getHero().getPosition()).sub(position).nor().scl(speed);
        if (position.dst(gameScreen.getHero().getPosition()) < 40) {
            attackTime += dt;
        } else{
            position.mulAdd(tmp, dt);
        }
        if (attackTime > 0.5f) {
            attackTime = 0.0f;
            gameScreen.getHero().takeDamage(this.damage);
        }
    }
}
