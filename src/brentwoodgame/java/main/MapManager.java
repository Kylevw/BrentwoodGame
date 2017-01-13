/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import grid.Grid;
import java.awt.Graphics2D;
import java.awt.Point;
import static brentwoodgame.java.main.EntityManager.player;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_HEIGHT;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_WIDTH;
import static brentwoodgame.java.main.Environment.GRID_CELL_SIZE;
import brentwoodgame.java.resources.ImageProviderIntf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Kyle
 */
public class MapManager {
    
    public static final String TEST_MAP = "TEST_MAP";
    
    private static Map map;
    
    public static void setMap(String map, ImageProviderIntf im) {
        File mapFile = new File(map + ".map");
        String tileScheme;
        String mapSize;
        String tileData;
        double xScreens = 1;
        double yScreens = 1;
        try {
            FileReader mapReader = new FileReader(mapFile);
            BufferedReader mapInput = new BufferedReader(mapReader);
            tileScheme = mapInput.readLine();
            mapSize = mapInput.readLine();
            tileData = mapInput.readLine();
            System.out.println(tileScheme);
            System.out.println(mapSize);
            System.out.println(tileData);
            xScreens = 1;
            yScreens = 2;
        } catch (IOException ex) {
            System.out.printf("Error: %s\n", ex);
            
        }
        
        int x = (int) (xScreens * DEFAULT_WINDOW_WIDTH);
        int y = (int) (yScreens * DEFAULT_WINDOW_HEIGHT);
        Grid environmentGrid = new Grid(x / GRID_CELL_SIZE,
                y / GRID_CELL_SIZE, GRID_CELL_SIZE, GRID_CELL_SIZE,
                new Point(-(x / 2), -(y / 2)), Color.BLACK);
        
        if (player != null) player.setScreenLimiter(
                environmentGrid.getGridSize().width - DEFAULT_WINDOW_WIDTH,
                environmentGrid.getGridSize().height - DEFAULT_WINDOW_HEIGHT);
        
        TileID[][] mapData = new TileID[environmentGrid.getColumns()][environmentGrid.getRows()];
        
        mapData[1][1] = TileID.WALL;
        
        MapManager.map = new Map(mapData, environmentGrid, im);
    }
    
    public void timerTaskHandler() {
        
    }
    
    public static void drawMap(Graphics2D graphics, int xTranslation, int yTranslation) {
        map.drawGridBase(graphics);
        map.drawMap(graphics, xTranslation, yTranslation);
    }
    
    public static Dimension getMapSize() {
        return map.getMapSize();
    }
    
    public static boolean checkCollision(Rectangle objectBoundary) {
        return map.collision(objectBoundary);
    }
    
}
