/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import static brentwoodgame.java.main.EntityManager.player;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_HEIGHT;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_WIDTH;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_X;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_Y;
import static brentwoodgame.java.main.Environment.GRID_CELL_SIZE;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Kyle
 */
public class PaintEnvironment {
    
    public static boolean loadingNewMap = false;
    
    public static int TRANSITION_FACTOR = 30;
    
    private static int xTranslation;
    private static int yTranslation;
    
    private static Color environmentTint;
    
    private static int mapTransitionOpacity = 0;
    
    public static void setEnvironmentTint(Color color) {
        environmentTint = color;
    }
    
    public static boolean isMapVisible() {
        return mapTransitionOpacity < 255;
    }
    
    public static void drawEnvironment(Graphics2D g) {
        
        // Resizes the default window size to the current size of the JFrame
        AffineTransform atWindow;
        Graphics2D graphics = (Graphics2D) g;
        atWindow = AffineTransform.getScaleInstance((double) BrentwoodGame.getWindowSize().width / DEFAULT_WINDOW_WIDTH, (double) BrentwoodGame.getWindowSize().height / DEFAULT_WINDOW_HEIGHT);
        if (atWindow != null) graphics.setTransform(atWindow);
        
        if (player != null) {
            xTranslation = player.getPosition().x;
            yTranslation = player.getPosition().y;
            if (xTranslation < player.getScreenMinX()) xTranslation = player.getScreenMinX();
            else if (xTranslation > player.getScreenMaxX()) xTranslation = player.getScreenMaxX();
            if (yTranslation < player.getScreenMinY()) yTranslation = player.getScreenMinY();
            else if (yTranslation > player.getScreenMaxY() + 8) yTranslation = player.getScreenMaxY() + 8;
            xTranslation = DEFAULT_WINDOW_X - xTranslation;
            yTranslation = DEFAULT_WINDOW_Y - yTranslation;
            if ((MapManager.getMapSize().width / GRID_CELL_SIZE) <= DEFAULT_WINDOW_WIDTH / GRID_CELL_SIZE) xTranslation = DEFAULT_WINDOW_WIDTH / 2;
            if ((MapManager.getMapSize().height / GRID_CELL_SIZE) <= DEFAULT_WINDOW_HEIGHT / GRID_CELL_SIZE) yTranslation = DEFAULT_WINDOW_HEIGHT / 2;
        }
        
        // Translates all background images in reference to the player's current position
        graphics.translate(xTranslation, yTranslation);
            
        MapManager.drawMap(graphics, xTranslation, yTranslation);
            
        EntityManager.getEntities().stream().forEach((entity) -> {
            entity.draw(graphics);
        });
        
        graphics.translate(-xTranslation, -yTranslation);
        
        if (environmentTint != null) {
            graphics.setColor(environmentTint);
            graphics.fillRect(0, 0, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_WIDTH);
        }
        
        if (loadingNewMap) mapTransitionOpacity += TRANSITION_FACTOR;
        else mapTransitionOpacity -= TRANSITION_FACTOR;
        
        mapTransitionOpacity = Math.min(mapTransitionOpacity, 255);
        mapTransitionOpacity = Math.max(mapTransitionOpacity, 0);
        if (mapTransitionOpacity > 0) {
            graphics.setColor(new Color(0, 0, 0, mapTransitionOpacity));
            graphics.fillRect(0, 0, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_WIDTH);
        }
    }
}
