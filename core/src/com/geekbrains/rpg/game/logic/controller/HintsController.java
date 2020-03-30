package com.geekbrains.rpg.game.logic.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.geekbrains.rpg.game.logic.inventory.Hints;
import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class HintsController extends ObjectPool<Hints> {
    private StringBuilder stringBuilder;

    @Override
    protected Hints newObject() {
        return new Hints();
    }

    public HintsController() {
        this.stringBuilder = new StringBuilder();
    }

    public void setupAnyAmount(float x, float y, Color color, String prefix, int amount) {
        Hints hints = getActiveElement();
        stringBuilder.setLength(0);
        stringBuilder.append(prefix).append(amount);
        hints.setup(x + MathUtils.random(-20, 20), y+ MathUtils.random(-20, 20), stringBuilder, color);
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        for (int i = 0; i < activeList.size(); i++) {
            Hints hints = activeList.get(i);
            font.setColor(hints.getColor());
            font.draw(batch, hints.getText(), hints.getPosition().x, hints.getPosition().y);
        }
        font.setColor(Color.WHITE);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
