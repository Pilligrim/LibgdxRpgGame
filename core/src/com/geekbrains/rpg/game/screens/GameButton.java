package com.geekbrains.rpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class GameButton {
    private Stage stage;

    public boolean isPause() {
        return pause;
    }

    private boolean pause;

    public GameButton() {
        pause = false;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        BitmapFont font14 = Assets.getInstance().getAssetManager().get("fonts/font24.ttf");
        TextButton.TextButtonStyle menuBtnStyle = new TextButton.TextButtonStyle(
                skin.getDrawable("smButton"), null, null, font14);

        TextButton btnPause = new TextButton("pause", menuBtnStyle);
        btnPause.setPosition(1040, 690);

        TextButton btnMenu = new TextButton("menu", menuBtnStyle);
        btnMenu.setPosition(1160, 690);

        btnPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pause = !pause;
                if (pause) {
                    stage.getActors().get(0).setColor(Color.RED);
                } else {
                    stage.getActors().get(0).setColor(Color.WHITE);
                }
            }
        });

        btnMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
            }
        });

        stage.addActor(btnPause);
        stage.addActor(btnMenu);
        skin.dispose();
    }

    public void update(float dt) {
        stage.act(dt);
    }

    public void render(float delta) {
        update(delta);
        stage.draw();
    }
}
