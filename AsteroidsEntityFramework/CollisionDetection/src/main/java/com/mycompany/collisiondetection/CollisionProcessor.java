/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.collisiondetection;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author MadsNorby
 */
public class CollisionProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                PositionPart firstEntityPosition = entity.getPart(PositionPart.class);
                PositionPart secondEntityPosition = entity2.getPart(PositionPart.class);

                float x1 = firstEntityPosition.getX();
                float y1 = firstEntityPosition.getY();
                float radius1 = 8;
                float x2 = secondEntityPosition.getX();
                float y2 = secondEntityPosition.getY();
                float radius2 = 8;
                
                
                //For test purposes
               //float middleDisplayX = gameData.getDisplayWidth() / 2;
               //float middleDisplayY = gameData.getDisplayHeight() / 2;
                

                if (checkCollision(x1, y1, radius1, x2, y2, radius2)) {
                    System.out.println("Collision: " + entity.toString() + " : " + entity2.toString());
                 
                } else {
                    System.out.println("NOT COLLISION");
                }
            }

        }

    }

    private boolean checkCollision(float x1, float y1, float radius1, float x2, float y2, float radius2) {
        float distance = (float) Math.hypot((x1 - x2), (y1 - y2));

        if ((radius1 + radius2) > distance) {
            return true;
        }
        return false;

    }

}
