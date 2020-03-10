package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.geekbrains.rpg.game.logic.utils.ObjectPool;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class MonstersController extends ObjectPool<Monster> {
    private GameController gc;

    @Override
    protected Monster newObject() {
        return new Monster(gc);
    }

    public Monster getMonster(){
        return getActiveElement();
    }


    public MonstersController(GameController gc) {
        this.gc = gc;
    }

       public void render(SpriteBatch batch) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).render(batch, null);
        }
    }

    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }
}
