/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import java.util.ArrayList;
import brentwoodgame.java.entities.Entity;
import brentwoodgame.java.entities.Player;

/**
 *
 * @author Kyle
 */
public class EntityManager {
    
    public static ArrayList<Entity> entities = new ArrayList<>();
    
    public static Player player;
//    public static ArrayList<Enemy> enemies = new ArrayList<>();
    
//    public static ArrayList<Enemy> getEnemies() {
//        return enemies;
//    }
//    
    
    public static ArrayList<Entity> getEntities() {
        if (player == null) entities.remove(player);
        return entities;
    }
        
}
