package com.geekbrains.rpg.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.geekbrains.rpg.game.screens.ScreenManager;

public class GeekRpgGame extends Game {
    private SpriteBatch batch;

    // Домашнее задание:
    // - Разбор кода и пишите какие вопросы возникли
    // - Добавьте опыт и уровни всем персонажам, когда персонаж наносит урон,
    // то он получает опыт, когда опыт достигает 1000, 2000, 4000 и т.д. ед. опыта,
    // уровень персонажа растет
    // - * При повышении уровня, может повышаться сила персонажа

    @Override
    public void create() {
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this, batch);
        ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.GAME);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}