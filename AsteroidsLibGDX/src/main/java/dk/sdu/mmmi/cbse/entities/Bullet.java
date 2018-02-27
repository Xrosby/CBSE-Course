/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 *
 * @author MadsNorby
 */
public class Bullet extends SpaceObject {

    private float lifeTime;
    private float lifeTimer;
    
    private boolean remove;
    
    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
        
        float speed = 350;
        
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        
        width = height = 2;
        
        lifeTimer = 0;
        lifeTime = 1;
        
    }
    
    public boolean shouldRemove() {
        return remove;
    }
    
    public void update(float dt) {
        
        x += dx * dt;
        y += dy * dt;
        
        wrap();
        
        lifeTimer += dt;
        if(lifeTime < lifeTimer) {
            remove = true;
        }
        
    }
    
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
   
        sr.circle(x - width/2, y-height/2, 3);
       
        sr.end();
    }
}
