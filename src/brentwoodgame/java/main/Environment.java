/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import brentwoodgame.java.resources.GameState;
import brentwoodgame.java.resources.BrentwoodImageManager;
import brentwoodgame.java.entities.Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import brentwoodgame.java.entities.Entity;
import brentwoodgame.java.entities.Gender;
import brentwoodgame.java.entities.Student;
import static brentwoodgame.java.main.EntityManager.entities;
import static brentwoodgame.java.main.EntityManager.player;
import static brentwoodgame.java.main.EntityManager.students;
import brentwoodgame.java.resources.AudioManager;
import timer.DurationTimer;

/**
 *
 * @author Kyle van Wiltenburg
 */
class Environment extends environment.Environment {
    
    public boolean paused;
    
    public boolean drawBoundary;
    
    public GameState gameState;
    
    public Color backgroundColor;
    
    public static final int DEFAULT_WINDOW_WIDTH = 288;
    public static final int DEFAULT_WINDOW_HEIGHT = 160;
    public static final int DEFAULT_WINDOW_X = DEFAULT_WINDOW_WIDTH / 2;
    public static final int DEFAULT_WINDOW_Y = DEFAULT_WINDOW_HEIGHT / 2;
    
    public static final String ARTS_MAP = "arts";
    
    public static final int GRID_CELL_SIZE = 16;
    
    public BrentwoodImageManager im;
    public AudioManager am;
    
    public Environment() {
        
        gameState = GameState.ENVIRONMENT;
        im = new BrentwoodImageManager();
        am = new AudioManager();
        
        backgroundColor = Color.WHITE;
        
        MapManager.setMap(MapManager.ARTS_MAP, im);
        
        player = new Player(new Point(0, 0), Gender.BOY, new PlayerScreenLimitProvider(
                MapManager.getMapSize().width - DEFAULT_WINDOW_WIDTH,
                MapManager.getMapSize().height - DEFAULT_WINDOW_HEIGHT), im, am);
        
        students.add(new Student(new Point(0, 20), im, am, Gender.BOY));
        students.add(new Student(new Point(0, 33), im, am, Gender.GIRL));
        
//        PaintEnvironment.setEnvironmentTint(new Color(255, 255, 0, 100));
        
    }
        
    public Font gamefont, gamefont_7, gamefont_14, hitfont, hitfont_5;

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
        
        MapManager.timerTaskHandler();
        if (player != null && player.despawn()) player = null;
        
        ArrayList<Student> removeStudents = new ArrayList<>();
        EntityManager.getStudents().stream().forEach((student) -> {
            if (student.despawn()) removeStudents.add(student);
        });
        students.removeAll(removeStudents);
        
        entities = new ArrayList<>();
        if (player != null) entities.add(player);
        if (students != null) entities.addAll(EntityManager.getStudents());
        
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
            else if (e.getKeyCode() == KeyEvent.VK_Z) player.runInteraction();
            
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
        
        if (backgroundColor != MapManager.getBackgroundColor()) {
            backgroundColor = MapManager.getBackgroundColor();
            setBackground(backgroundColor);
        }
        
        PaintEnvironment.drawEnvironment((Graphics2D) g);
    }
}
