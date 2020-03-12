package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameController {
    private ProjectilesController projectilesController;
    private Map map;
    private Hero hero;
    private MonstersController monstersController;
    private Vector2 tmp, tmp2;
    private float respawnNewMonsterTime;

    public GameController() {
        this.projectilesController = new ProjectilesController();
        this.monstersController = new MonstersController(this);
        for (int i = 0 ; i< 4; i++) {
            this.monstersController.getMonster();
        }
        this.hero = new Hero(this);
        this.map = new Map();
        this.tmp = new Vector2(0, 0);
        this.tmp2 = new Vector2(0, 0);
        this.respawnNewMonsterTime = 0f;
    }

    public Hero getHero() {
        return hero;
    }

    public MonstersController getMonstersController() {
        return this.monstersController;
    }

    public Map getMap() {
        return map;
    }

    public ProjectilesController getProjectilesController() {
        return projectilesController;
    }

    public void update(float dt) {
        respawnNewMonsterTime += dt;
        if (respawnNewMonsterTime > 30f) {
            respawnNewMonsterTime = 0.0f;
            monstersController.getMonster().respawn();
        }
        hero.update(dt);
        monstersController.update(dt);


        checkCollisions();
        for (int i = 0; i < monstersController.getActiveList().size(); i++) {
            Monster monster = monstersController.getActiveList().get(i);
            collideUnits(hero, monster);
        }

        projectilesController.update(dt);
    }

    public void collideUnits(GameCharacter u1, GameCharacter u2) {
        if (u1.getArea().overlaps(u2.getArea())) {
            tmp.set(u1.getArea().x, u1.getArea().y);
            tmp.sub(u2.getArea().x, u2.getArea().y);
            float halfInterLen = ((u1.getArea().radius + u2.getArea().radius) - tmp.len()) / 2.0f;
            tmp.nor();

            tmp2.set(u1.getPosition()).mulAdd(tmp, halfInterLen);
            if (map.isGroundPassable(tmp2)) {
                u1.changePosition(tmp2);
            }

            tmp2.set(u2.getPosition()).mulAdd(tmp, -halfInterLen);
            if (map.isGroundPassable(tmp2)) {
                u2.changePosition(tmp2);
            }
        }
    }

    public void checkCollisions() {
        for (int i = 0; i < projectilesController.getActiveList().size(); i++) {
            Projectile p = projectilesController.getActiveList().get(i);
            if (!map.isAirPassable(p.getCellX(), p.getCellY())) {
                p.deactivate();
                continue;
            }
            for (int j = 0; j < monstersController.getActiveList().size(); j++) {
                Monster monster = monstersController.getActiveList().get(j);
                if (p.getPosition().dst(monster.getPosition()) < 24) {
                    p.deactivate();
                    if (monster.takeDamage(1)) {
                        hero.addCoins(MathUtils.random(1, 10));
                    }
                }
            }
        }
    }
}
