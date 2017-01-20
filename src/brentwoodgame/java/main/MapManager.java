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
    
    public static final String ARTS_MAP = "arts";
    
    private static Map map;
    
    private static final String MAP_FILE_LOCATION = "src/brentwoodgame/resources/maps/";
    private static final String MAP_FILE_TYPE = ".map";
    
    private static boolean drawGrid = false;
    private static Color backgroundColor;
    
    private static String promptedMap;
    private static ImageProviderIntf promptedip;
    private static Point promptedPlayerPosition;
    private static Direction promptedPlayerDirection;
    
    private static boolean updateMap = false;
    
    public static void promptNewMap(String map, ImageProviderIntf ip, Point playerPosition, Direction playerDirection) {
        promptedMap = map;
        promptedip = ip;
        promptedPlayerPosition = playerPosition;
        promptedPlayerDirection = playerDirection;
        updateMap = true;
        PaintEnvironment.loadingNewMap = true;
    }
    
    public static void setMap(String map, ImageProviderIntf ip, Point playerPosition, Direction playerDirection) {
        if (player != null) {
            player.setPosition(playerPosition);
            player.setDirection(playerDirection);
        }
        setMap(map, ip);
    }
    
    public static void setMap(String map, ImageProviderIntf ip) {
        MapManager.map = null;
        EntityManager.students.clear();
        File mapFile = new File(MAP_FILE_LOCATION + map + MAP_FILE_TYPE);
        String tileScheme = null;
        String[] mapSize;
        String tileData = null;
        double xScreens = 1;
        double yScreens = 1;
        try {
            FileReader mapReader = new FileReader(mapFile);
            BufferedReader mapInput = new BufferedReader(mapReader);
            
            String inputData = "";
            String line;
            while ((line = mapInput.readLine()) != null) {
                inputData = inputData + line;
            }
            
            String[] dataFragments = inputData.split(";");
            
            tileScheme = dataFragments[0];
            drawGrid = tileScheme.equals(TileLibrary.DEBUG);
            backgroundColor = Color.WHITE;
            switch (tileScheme) {
                case TileLibrary.ARTS:
                    backgroundColor = Color.BLACK;
                    break;
                case TileLibrary.CAMPUS:
                    backgroundColor = Color.BLUE;
                    break;
            }
            
            
            mapSize = dataFragments[1].split(",");
            
            tileData = dataFragments[2];
            
//            System.out.println(tileScheme);
//            System.out.println(mapSize);
//            System.out.println(tileData);
            
            xScreens = Double.valueOf(mapSize[0]);
            xScreens = xScreens < 1 ? 1 : xScreens;
            yScreens = Double.valueOf(mapSize[1]);
            yScreens = yScreens < 1 ? 1 : yScreens;
            
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
        
        Tile[][] mapData;
        if (tileData != null) mapData = getTileMap(tileData, tileScheme, environmentGrid.getColumns(), environmentGrid.getRows(), ip);
        else mapData = new Tile[environmentGrid.getColumns()][environmentGrid.getRows()];
        
        MapManager.map = new Map(mapData, environmentGrid);
        
        updateMap = false;
        PaintEnvironment.loadingNewMap = false;
    }
    
    private static Tile[][] getTileMap(String tileData, String tileScheme, int columns, int rows, ImageProviderIntf ip) {
        String[] tileIDs = tileData.split(",");
        Tile[][] mapData = new Tile[columns][rows];
        for (int tile = 0; tile < columns * rows; tile++) {
            int x = tile % columns;
            int y = tile / columns;
            if (tile >= tileIDs.length) {
                for (int tileFill = tile; tileFill < columns * rows; tileFill++) {
                    mapData[x][y] = TileLibrary.getTile(TileLibrary.AIR, null, null, ip);
                }
                break;
            }
            if (tileIDs[tile].contains(":")) {
                String[] tileInfo = tileIDs[tile].split(":");
                int id = Integer.valueOf(tileInfo[0]);
                mapData[x][y] = TileLibrary.getTile(id, tileScheme, new Point(x, y), ip, tileInfo[1], new Point(Integer.valueOf(tileInfo[2]), Integer.valueOf(tileInfo[3])), Integer.valueOf(tileInfo[4]));
            }
            else {
                int id = Integer.valueOf(tileIDs[tile]);
                mapData[x][y] = TileLibrary.getTile(id, tileScheme, new Point(x, y), ip);
            }
        }
        return mapData;
    }
    
    public static void timerTaskHandler() {
        if (updateMap && !PaintEnvironment.isMapVisible()) setMap(promptedMap, promptedip, promptedPlayerPosition, promptedPlayerDirection);
    }
    
    public static void drawMap(Graphics2D graphics, int xTranslation, int yTranslation) {
        if (drawGrid) map.drawGridBase(graphics);
        map.drawMap(graphics, xTranslation, yTranslation);
    }
    
    public static Dimension getMapSize() {
        return map.getMapSize();
    }
    
    public static boolean checkCollision(Rectangle objectBoundary) {
        return map.collision(objectBoundary);
    }
    
    public static Point gridCellLocation(Point gridPoint) {
        return map.checkPointLocation(gridPoint);
    }
    
    public static String getTileScheme() {
        return map.getTileScheme();
    }
    
    public static boolean checkIsBarrier(int x, int y) {
        return map.getTileBarrier(x, y);
    }
    
    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static boolean interactableTile(Point point) {
        return map.checkInteractable(point);
    }

    public static void runTileEvent(Point point) {
        map.runTileEvent(point);
    }
    
}
