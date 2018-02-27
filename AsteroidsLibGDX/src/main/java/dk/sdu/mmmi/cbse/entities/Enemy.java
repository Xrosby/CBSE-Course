/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author MadsNorby
 */
public class Enemy extends SpaceObject {

    private boolean left;
    private boolean right;
    private boolean up;

    private final int MAXBULLETS = 4;
   
    private float timer;
    
    private ArrayList<Bullet> bullets;
 

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    public Enemy(ArrayList<Bullet> bullets) {

        this.bullets = bullets;

        x = Game.WIDTH / 10;
        y = Game.HEIGHT / 10;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapex = new float[4];
        shapey = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        timer = 0;

    }

    private void setShape() {
        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1145f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    
    
     public void shoot() {
        if(bullets.size() == MAXBULLETS) {
            return;
        } else {
            bullets.add(new Bullet(x, y, radians));
        }
    }
    
    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void update(float dt) {

      
    chooseDirection(dt);
    shoot();
    
        // turning
        if (left) {
            radians += rotationSpeed * dt;
        } else if (right) {
            radians -= rotationSpeed * dt;
        }

        // accelerating
        if (up) {
            dx += MathUtils.cos(radians) * acceleration * dt;
            dy += MathUtils.sin(radians) * acceleration * dt;
        }

        // deceleration
        float vec = (float) Math.sqrt(dx * dx + dy * dy);
        if (vec > 0) {
            dx -= (dx / vec) * deceleration * dt;
            dy -= (dy / vec) * deceleration * dt;
        }
        if (vec > maxSpeed) {
            dx = (dx / vec) * maxSpeed;
            dy = (dy / vec) * maxSpeed;
        }

        // set position
        x += dx * dt;
        y += dy * dt;

        // set shape
        setShape();

        // screen wrap
        wrap();

    }

    
    public void chooseDirection(float dt) {
          if (timer < 4) {

            if (timer >= 0 && timer < 0.2) {
                accelerate();
                

            }
            if (timer > 0.2 && timer < 2) {
                turnRight();
            }
            
            if(timer > 2 && timer < 2.2) {
                accelerate();
            
            }
            
            if (timer > 3 && timer < 4) {
                turnLeft();

            }
            
            timer +=dt;

            System.out.println(timer);
        } else {
            timer = 0;
        }
    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(74, 175, 0, 20);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
                i < shapex.length;
                j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }

    public void turnLeft() {
        up = false;
        left = true;
        right = false;
    }

    public void turnRight() {
        up = false;
        left = false;
        right = true;
    }

    public void accelerate() {
        up = true;
        left = false;
        right = false;
    }

}
