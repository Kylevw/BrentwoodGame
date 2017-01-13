/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_HEIGHT;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_WIDTH;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_X;
import static brentwoodgame.java.main.Environment.DEFAULT_WINDOW_Y;
import brentwoodgame.java.resources.ImageProviderIntf;
import brentwoodgame.java.resources.PImageManager;
import grid.Grid;
import java.awt.Color;
import java.awt.Dimension;

/**
 *
 * @author Kyle
 */
public class Map {
    
    private final Grid environmentGrid;
    
    private TileID[][] mapData;
    
    private final ImageProviderIntf im;

    public Map(TileID[][] mapData, Grid environmentGrid, ImageProviderIntf im) {
        this.environmentGrid = environmentGrid;
        this.mapData = mapData;
        this.im = im;
    }
    
    public void drawMap(Graphics2D graphics, int xTranslation, int yTranslation) {
        for (int x = 0; x < environmentGrid.getColumns(); x++) {
            for (int y = 0; y < environmentGrid.getRows(); y++) {
                Point point = environmentGrid.getCellSystemCoordinate(x, y);
                if (point.x + environmentGrid.getCellWidth() >= -xTranslation &&
                        point.y + environmentGrid.getCellHeight() >= -yTranslation &&
                        point.x - DEFAULT_WINDOW_WIDTH <= -xTranslation &&
                        point.y - DEFAULT_WINDOW_HEIGHT <= -yTranslation)
                    {
                        BufferedImage image = null;
                        
                        if (mapData != null && mapData[x][y] != null) {
                            switch (mapData[x][y]) {
                                case AIR:
                                    break;
                                case WALL:
                                    image = im.getImage(PImageManager.WALL_TILE);
                                    break;
                            }
                        }
                        
                        if (image != null) graphics.drawImage(image,
                        point.x, point.y,
                        environmentGrid.getCellWidth(),
                        environmentGrid.getCellHeight(), null);
                        if (mapData != null && mapData[x][y] == TileID.WALL) {
                            if (x <= 0 || mapData[x - 1][y] == TileID.WALL) graphics.drawImage(im.getImage(PImageManager.WALL_TILE_CONNECTOR_LEFT), point.x, point.y + 1, null);
                            if (x >= environmentGrid.getColumns() || mapData[x + 1][y] == TileID.WALL) graphics.drawImage(im.getImage(PImageManager.WALL_TILE_CONNECTOR_RIGHT), point.x + environmentGrid.getCellWidth() - 2, point.y + 1, null);
                            if (y <= 0 || mapData[x][y - 1] == TileID.WALL) graphics.drawImage(im.getImage(PImageManager.WALL_TILE_CONNECTOR_UP), point.x + 1, point.y, null);
                            if (y >= environmentGrid.getRows() || mapData[x][y + 1] == TileID.WALL) graphics.drawImage(im.getImage(PImageManager.WALL_TILE_CONNECTOR_DOWN), point.x + 1, point.y  + environmentGrid.getCellHeight() - 2, null);
                        }
                    }
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
    
    private boolean hasCollision(TileID tileID) {
        return (tileID == TileID.WALL);
    }
    
    public boolean collision(Rectangle objectBoundary) {
        if (mapData != null) {
        int x = (objectBoundary.x + (environmentGrid.getGridSize().width / 2)) / environmentGrid.getCellWidth();
        int y = (objectBoundary.y + (environmentGrid.getGridSize().height / 2)) / environmentGrid.getCellHeight();
        int x2 = (objectBoundary.x + objectBoundary.width - 1 + (environmentGrid.getGridSize().width / 2)) / environmentGrid.getCellWidth();
        int y2 = (objectBoundary.y + objectBoundary.height - 1 + (environmentGrid.getGridSize().height / 2)) / environmentGrid.getCellHeight();
        
        if (objectBoundary.x < -(environmentGrid.getGridSize().width / 2) || x2 >= environmentGrid.getColumns() ||
                objectBoundary.y < -(environmentGrid.getGridSize().height / 2) || y2 >= environmentGrid.getRows()) return true;
        else return (hasCollision(mapData[x][y]) || hasCollision(mapData[x2][y]) ||
                hasCollision(mapData[x][y2]) || hasCollision(mapData[x2][y2]));
        } else return false;
    }
    
}
