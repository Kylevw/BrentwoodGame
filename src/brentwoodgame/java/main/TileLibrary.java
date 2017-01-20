/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import brentwoodgame.java.resources.ImageProviderIntf;
import brentwoodgame.java.resources.BrentwoodImageManager;
import java.awt.Point;

/**
 *
 * @author Kyle
 */

public class TileLibrary {
    
    public static final String DEBUG = "debug";
    public static final String ROSS = "ross";
    public static final String CROOKS = "crooks";
    public static final String ARTS = "arts";
    public static final String CAMPUS = "campus";
    public static final String MT = "mt";
    
    
    public static final int AIR = 0;
    public static final int WALL = 1;
    public static final int FLOOR = 2;
    public static final int DOOR = 3;
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    public static Tile getTile(int tileID, String tileScheme, Point gridPosition, ImageProviderIntf ip) {
        Tile tile = null;
        switch (tileID) {
            case AIR:
                tile = new Tile(gridPosition, null, false, ip);
                break;
            case WALL:
                tile = new Tile(gridPosition, getWallType(tileScheme), true, ip);
                break;
            case FLOOR:
                tile = new Tile(gridPosition, getFloorType(tileScheme), false, ip);
                break;
            case DOOR:
                tile = new Door(gridPosition, BrentwoodImageManager.DOOR_TILE_DEBUG_LIST,
                        true, ip, DEBUG, new Point(0, 0), Direction.DOWN);
                break;
        }
        if (tile == null) tile = new Tile(gridPosition, "missingno", false, ip);
        return tile;
    }
    
    public static Tile getTile(int tileID, String tileScheme, Point gridPosition, ImageProviderIntf ip, String mapName, Point playerPosition, int playerDirection) {
        Tile tile = null;
        Direction newDirection = Direction.DOWN;
        switch (playerDirection) {
            case UP:
                newDirection = Direction.UP;
                break;
            case DOWN:
                newDirection = Direction.DOWN;
                break;
            case LEFT:
                newDirection = Direction.LEFT;
                break;
            case RIGHT:
                newDirection = Direction.RIGHT;
                break;
        }
        switch (tileID) {
            case DOOR:
                tile = new Door(gridPosition, getDoorType(tileScheme),
                        true, ip, mapName, playerPosition, newDirection);
                break;
        }
        if (tile == null) tile = getTile(tileID, tileScheme, gridPosition, ip);
        return tile;
    }
    
    private static String getWallType(String tileScheme) {
        if (tileScheme != null) {
            String animationList = BrentwoodImageManager.WALL_TILE_DEBUG_LIST;
            switch (tileScheme) {
                case ARTS:
                    animationList = BrentwoodImageManager.WALL_TILE_ARTS_LIST;
                    break;
            }
            return animationList;
        } else return BrentwoodImageManager.WALL_TILE_DEBUG_LIST;
    }
    
    private static String getFloorType(String tileScheme) {
        if (tileScheme != null) {
            String animationList = BrentwoodImageManager.FLOOR_TILE_DEBUG_LIST;
            switch (tileScheme) {
                case ARTS:
                    animationList = BrentwoodImageManager.FLOOR_TILE_ARTS_LIST;
                    break;
            }
            return animationList;
        } else return BrentwoodImageManager.FLOOR_TILE_DEBUG_LIST;
    }
    
    private static String getDoorType(String tileScheme) {
        if (tileScheme != null) {
            String animationList = BrentwoodImageManager.DOOR_TILE_DEBUG_LIST;
            switch (tileScheme) {
                case ARTS:
                    animationList = BrentwoodImageManager.DOOR_TILE_ARTS_LIST;
                    break;
            }
            return animationList;
        } else return BrentwoodImageManager.DOOR_TILE_DEBUG_LIST;
    }
    
}
