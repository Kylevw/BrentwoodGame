/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import brentwoodgame.java.resources.GameState;
import brentwoodgame.java.resources.PImageManager;
import brentwoodgame.java.entities.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import brentwoodgame.java.entities.Entity;
import static brentwoodgame.java.main.EntityManager.entities;
import static brentwoodgame.java.main.EntityManager.player;
import brentwoodgame.java.resources.AudioManager;

/**
 *
 * @author Kyle van Wiltenburg
 */
class Environment extends environment.Environment {
    
    private boolean paused;
    
    private boolean drawBoundary;
    
    public GameState gameState;
    
    public static final int DEFAULT_WINDOW_WIDTH = 288;
    public static final int DEFAULT_WINDOW_HEIGHT = 160;
    public static final int DEFAULT_WINDOW_X = DEFAULT_WINDOW_WIDTH / 2;
    public static final int DEFAULT_WINDOW_Y = DEFAULT_WINDOW_HEIGHT / 2;
    
    public static final int GRID_CELL_SIZE = 16;
    
    private int xTranslation;
    private int yTranslation;
    
    PImageManager im;
    AudioManager am;

    public Environment() {
        
        gameState = GameState.ENVIRONMENT;
        im = new PImageManager();
        am = new AudioManager();
        
        setMap();
        
        
        player = new Player(new Point(0, 0), new PlayerScreenLimitProvider(
                MapManager.getMapSize().width - DEFAULT_WINDOW_WIDTH,
                MapManager.getMapSize().height - DEFAULT_WINDOW_HEIGHT), im, am);
        
    }
    
    private void setMap() {
        MapManager.setMap("test", im);
    }
    
    

    Font gamefont, gamefont_7, gamefont_14, hitfont, hitfont_5;

    @Override
    public void initializeEnvironment() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("brentwoodgame/resources/fonts/gamefont.ttf");

            gamefont = Font.createFont(Font.TRUETYPE_FONT, input);
            gamefont_7 = gamefont.deriveFont((float)7.0);
            gamefont_14 = gamefont.deriveFont((float)14.0);
            
            input = classLoader.getResourceAsStream("brentwoodgame/resources/fonts/hitfont.ttf");
            
            hitfont = Font.createFont(Font.TRUETYPE_FONT, input);
            hitfont_5 = hitfont.deriveFont((float)5.0);

        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void timerTaskHandler() {
        
        if (player != null && player.despawn()) player = null;
        
//        ArrayList<Enemy> removeEnemies = new ArrayList<>();
//        EntityManager.getEnemies().stream().forEach((enemy) -> {
//            if (enemy.despawn()) removeEnemies.add(enemy);
//        });
//        enemies.removeAll(removeEnemies);
        
        entities = new ArrayList<>();
        if (player != null) entities.add(player);
//        if (enemies != null) entities.addAll(EntityManager.getEnemies());
        
        entities.sort((Entity e1, Entity e2) -> {
            final int y1 = e1.getPosition().y;
            final int y2 = e2.getPosition().y;
            return y1 < y2 ? -1 : y1 > y2 ? 1 : 0;
        });
        
        if (!paused) {
            if (entities != null) {
                EntityManager.getEntities().stream().forEach((entity) -> {
                    if (drawBoundary && !entity.drawBoundary()) entity.drawObjectBoundary(true);
                    else if (!drawBoundary && entity.drawBoundary()) entity.drawObjectBoundary(false);
                    entity.timerTaskHandler();
                });
            }
        }
        
    }
    
    @Override
    public void keyPressedHandler(KeyEvent e) {
        // Uses the ProjectileArrow Keys to control the player
        if (player != null && !paused) {
            // Once pressing a key, it adds said key to the Directions list.
            if (e.getKeyCode() == KeyEvent.VK_UP && !player.getDirections().contains(Direction.UP)) player.addDirection(Direction.UP);
            else if (e.getKeyCode() == KeyEvent.VK_DOWN && !player.getDirections().contains(Direction.DOWN)) player.addDirection(Direction.DOWN);
            else if (e.getKeyCode() == KeyEvent.VK_LEFT && !player.getDirections().contains(Direction.LEFT)) player.addDirection(Direction.LEFT);
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT && !player.getDirections().contains(Direction.RIGHT)) player.addDirection(Direction.RIGHT);
                        
//            else if (e.getKeyCode() == KeyEvent.VK_1) MapManager.updateGrid(1, 1);
//            else if (e.getKeyCode() == KeyEvent.VK_2) MapManager.updateGrid(2, 2);
//            else if (e.getKeyCode() == KeyEvent.VK_3) MapManager.updateGrid(1, 2);
//            else if (e.getKeyCode() == KeyEvent.VK_3) MapManager.updateGrid(2, 1);
            
            else if (e.getKeyCode() == KeyEvent.VK_P && player != null) {
                System.out.println(player.getPosition());
            }
            
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) paused = !paused;
        else if (e.getKeyCode() == KeyEvent.VK_Q) drawBoundary = !drawBoundary;
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (player != null) {
            // Once letting go of a key, it removes said key from the Directions list.
            if (e.getKeyCode() == KeyEvent.VK_UP && player.getDirections().contains(Direction.UP)) player.removeDirection(Direction.UP);
            else if (e.getKeyCode() == KeyEvent.VK_DOWN && player.getDirections().contains(Direction.DOWN)) player.removeDirection(Direction.DOWN);
            else if (e.getKeyCode() == KeyEvent.VK_LEFT && player.getDirections().contains(Direction.LEFT)) player.removeDirection(Direction.LEFT);
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT && player.getDirections().contains(Direction.RIGHT)) player.removeDirection(Direction.RIGHT);
        }
    }
    
    @Override
    public void environmentMouseClicked(MouseEvent e) {
        Point ePoint = e.getPoint();
        ePoint.setLocation(
                ePoint.x * DEFAULT_WINDOW_WIDTH / BrentwoodGame.getWindowSize().width,
                ePoint.y * DEFAULT_WINDOW_HEIGHT / BrentwoodGame.getWindowSize().height
        );
    }
    
    @Override
    public void paintEnvironment(Graphics g) {
        
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
        
        // Draws rectangles for debugging
//            drawGridBase(graphics);
            
//            buildWall(graphics, im.getImage(PImageManager.BRICK_TILE), -4, -2, 4, 2);
            
            MapManager.drawMap(graphics, xTranslation, yTranslation);
//            environmentGrid.paintComponent(graphics);
            
        EntityManager.getEntities().stream().forEach((entity) -> {
            entity.draw(graphics);
        });
        
        graphics.translate(-xTranslation, -yTranslation);
        
        graphics.setFont(gamefont_7);
        
        if (!paused) {
            graphics.setColor(new Color(0, 0, 0, 80));
            graphics.drawString("Press ESCAPE for control menu", 3, 191);
            graphics.setColor(Color.WHITE);
            graphics.drawString("Press ESCAPE for control menu", 2, 190);
        }
            
        for (int i = 0; i < 7; i++) {
            graphics.drawImage(im.getImage(PImageManager.INVENTORY_SLOT), 222 + (i * 16), 2, 16, 16, null);
        }
        graphics.drawImage(im.getImage(PImageManager.ITEM_SWORD), 225, 5, 10, 10, null);
        graphics.drawImage(im.getImage(PImageManager.ITEM_BOW), 241, 5, 10, 10, null);
        
        if (paused) {
            graphics.setColor(new Color(0, 0, 0, 100));
            graphics.fillRect(0, 0, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
            
            graphics.setColor(new Color(0, 0, 0, 80));
            graphics.drawString("Press Q to show hitboxes", 3, 13);
            
            graphics.drawString("Use WASD to move", 3, 37);
            graphics.drawString("Press SPACE to jump", 3, 49);
            graphics.drawString("Use the ARROW KEYS to draw bow", 3, 61);
            graphics.drawString("Release the ARROW KEYS to fire bow", 3, 73);
            graphics.drawString("Press PERIOD to swing sword", 3, 85);
            graphics.drawString("Press SLASH to throw bomb", 3, 97);
            
            graphics.drawString("Press F to spawn heart pickup", 3, 121);
            graphics.drawString("Press G to spawn bomb pickup", 3, 133);
            graphics.drawString("Press H to spawn arrow pickup", 3, 145);
            graphics.drawString("Press Z to spawn Bat", 3, 157);
            graphics.drawString("Press X to spawn Samsara Eye", 3, 169);
            
            graphics.drawString("Press ESCAPE to close menu", 3, 191);
            
            
            graphics.setColor(Color.WHITE);
            graphics.drawString("Press Q to show hitboxes", 2, 12);
            
            graphics.drawString("Use WASD to move", 2, 36);
            graphics.drawString("Press SPACE to jump", 2, 48);
            graphics.drawString("Use the ARROW KEYS to draw bow", 2, 60);
            graphics.drawString("Release the ARROW KEYS to fire bow", 2, 72);
            graphics.drawString("Press PERIOD to swing sword", 2, 84);
            graphics.drawString("Press SLASH to throw bomb", 2, 96);
            
            graphics.drawString("Press F to spawn heart pickup", 2, 120);
            graphics.drawString("Press G to spawn bomb pickup", 2, 132);
            graphics.drawString("Press H to spawn arrow pickup", 2, 144);
            graphics.drawString("Press Z to spawn Bat", 2, 156);
            graphics.drawString("Press X to spawn Samsara Eye", 2, 168);
            
            graphics.drawString("Press ESCAPE to close menu", 2, 190);
        }
        
    }
    
}
