/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_HEIGHT;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_WIDTH;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_X;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_Y;
import grid.Grid;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author Kyle
 */
public class Map {
    
    private final Grid environmentGrid;
    
    private final Tile[][] mapData;
    
    public Map(Tile[][] mapData, Grid environmentGrid) {
        this.environmentGrid = environmentGrid;
        this.mapData = mapData;
    }
    
    public void drawMap(Graphics2D graphics, int xTranslation, int yTranslation) {
        for (int x = 0; x < environmentGrid.getColumns(); x++) {
            for (int y = 0; y < environmentGrid.getRows(); y++) {
                Point point = environmentGrid.getCellSystemCoordinate(x, y);
                if (mapData[x][y] != null && point.x + environmentGrid.getCellWidth() >=
                        -xTranslation && point.y + environmentGrid.getCellHeight() >=
                        -yTranslation && point.x - DEFAULT_WINDOW_WIDTH <= -xTranslation &&
                        point.y - DEFAULT_WINDOW_HEIGHT <= -yTranslation)
                    mapData[x][y].draw(graphics);
            }
        }
    }
    
        public void drawGridBase(Graphics2D graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(environmentGrid.getPosition().x + DEFAULT_WINDOW_X,
        environmentGrid.getPosition().y + DEFAULT_WINDOW_Y,
        environmentGrid.getGridSize().width - DEFAULT_WINDOW_WIDTH,
        environmentGrid.getGridSize().height - DEFAULT_WINDOW_HEIGHT);
        
        graphics.setColor(Color.GRAY);
        for (int i = 0; i < environmentGrid.getColumns(); i++) {
            int x = (int) environmentGrid.getCellSystemCoordinate(i, 0).x;
            graphics.fillRect(x, environmentGrid.getPosition().y, environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
            graphics.fillRect(x, environmentGrid.getPosition().y + environmentGrid.getGridSize().height - environmentGrid.getCellHeight(), environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
        }
        
        for (int i = 0; i < environmentGrid.getRows(); i++) {
            int y = (int) environmentGrid.getCellSystemCoordinate(0, i).y;
            graphics.fillRect(environmentGrid.getPosition().x, y, environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
            graphics.fillRect(environmentGrid.getPosition().x + environmentGrid.getGridSize().width - environmentGrid.getCellWidth(), y, environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
        }
        
        environmentGrid.paintComponent(graphics);
    }
    
    public Dimension getMapSize() {
        return environmentGrid.getGridSize();
    }
    
    public boolean collision(Rectangle objectBoundary) {
        
        if (objectBoundary.x < -(environmentGrid.getGridSize().width / 2) ||
                objectBoundary.x + objectBoundary.width >= environmentGrid.getGridSize().width / 2 ||
                objectBoundary.y < -(environmentGrid.getGridSize().height / 2) ||
                objectBoundary.y + objectBoundary.height >= environmentGrid.getGridSize().height / 2)
            return true;
        
        if (mapData != null) {
        int x = (objectBoundary.x + (environmentGrid.getGridSize().width / 2)) / environmentGrid.getCellWidth();
        int y = (objectBoundary.y + (environmentGrid.getGridSize().height / 2)) / environmentGrid.getCellHeight();
        int x2 = (objectBoundary.x + objectBoundary.width - 1 + (environmentGrid.getGridSize().width / 2)) / environmentGrid.getCellWidth();
        int y2 = (objectBoundary.y + objectBoundary.height - 1 + (environmentGrid.getGridSize().height / 2)) / environmentGrid.getCellHeight();
        
        return ((mapData[x][y].isBarrier() &&
                mapData[x][y].getObjectBoundary().intersects(objectBoundary)) ||
                (mapData[x2][y].isBarrier() &&
                mapData[x2][y].getObjectBoundary().intersects(objectBoundary)) ||
                (mapData[x][y2].isBarrier() &&
                mapData[x][y2].getObjectBoundary().intersects(objectBoundary)) ||
                (mapData[x2][y2].isBarrier() &&
                mapData[x2][y2].getObjectBoundary().intersects(objectBoundary)));
        } else return false;
    }
    
    
    
    public boolean checkInteractable(Point point) {
        try {
            return mapData[point.x][point.y].canInteract();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    
    public Point checkPointLocation(Point point) {
        return environmentGrid.getCellLocationFromSystemCoordinate(point);
    }

    public String getTileScheme() {
        return null;
    }

    boolean getTileBarrier(int x, int y) {
        try {
            return mapData[x][y].isBarrier();
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    void runTileEvent(Point point) {
        mapData[point.x][point.y].interactEvent();
    }
    
}
