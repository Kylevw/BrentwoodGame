/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import brentwoodgame.java.resources.ImageProviderIntf;
import java.awt.Point;

/**
 *
 * @author Kyle
 */
public class Door extends Tile {

    private final String newMap;
    private final Point newPlayerPosition;
    private final Direction newPlayerDirection;
    
    public Door(Point position, String imageList, boolean isBarrier, ImageProviderIntf ip, String newMap, Point newPlayerPosition, Direction newPlayerDirection) {
        super(position, imageList, isBarrier, ip);
        this.newMap = newMap;
        this.newPlayerPosition = newPlayerPosition;
        this.newPlayerDirection = newPlayerDirection;
        setInteract(true);
    }
    
    @Override
    public void interactEvent() {
        MapManager.promptNewMap(newMap, getImageIntf(), newPlayerPosition, newPlayerDirection);
    }
    
}
