package com.geekbrains.rpg.game.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geekbrains.rpg.game.logic.GameController;
import com.geekbrains.rpg.game.logic.WorldRenderer;

public class GameScreen extends AbstractScreen {
    private GameController gc;
    private WorldRenderer worldRenderer;
    private GameButton gameButton;

    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        gc = new GameController();
        worldRenderer = new WorldRenderer(gc, batch);
        gameButton = new GameButton();
    }

    @Override
    public void render(float delta) {
        if (!gameButton.isPause()) {
            gc.update(delta);
        }
        gameButton.update(delta);
        worldRenderer.render();
        gameButton.render(delta);
    }
}