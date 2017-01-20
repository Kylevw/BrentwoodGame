/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.main;

import static brentwoodgame.java.main.Environment.GRID_CELL_SIZE;
import brentwoodgame.java.resources.Animator;
import brentwoodgame.java.resources.ImageProviderIntf;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Kyle
 */
public class Tile {
    
    private static final int ANIMATION_SPEED = 160;
    
    private boolean canInteract = false;
    
    private final Point position;
    private final ImageProviderIntf ip;
    private final boolean isBarrier;
    private final Animator animator;
    private final boolean drawTile;
    public Tile(Point position, String imageList, boolean isBarrier, ImageProviderIntf ip) {
        this.position = position;
        this.ip = ip;
        this.isBarrier = isBarrier;
        this.animator = new Animator(ip, ip.getImageList(imageList), ANIMATION_SPEED);
        drawTile = imageList != null;
        canInteract = false;
    }
    
    public Rectangle getObjectBoundary() {
        return new Rectangle((position.x * GRID_CELL_SIZE) -
                (MapManager.getMapSize().width / 2), (position.y * GRID_CELL_SIZE) -
                (MapManager.getMapSize().height / 2), GRID_CELL_SIZE, GRID_CELL_SIZE);
    }
    
    public void draw(Graphics2D graphics) {
        if (drawTile) {
            if (isBarrier) {
                boolean[] connectData = new boolean[4];
                connectData[0] = MapManager.checkIsBarrier(position.x, position.y - 1);
                connectData[1] = MapManager.checkIsBarrier(position.x, position.y + 1);
                connectData[2] = MapManager.checkIsBarrier(position.x - 1, position.y);
                connectData[3] = MapManager.checkIsBarrier(position.x + 1, position.y);
                graphics.drawImage(ip.getWallImage(animator.getCurrentImageName(), connectData), (position.x * GRID_CELL_SIZE) -
                (MapManager.getMapSize().width / 2), (position.y * GRID_CELL_SIZE) -
                (MapManager.getMapSize().height / 2), GRID_CELL_SIZE, GRID_CELL_SIZE, null);
            } else {
                graphics.drawImage(animator.getCurrentImage(), (position.x * GRID_CELL_SIZE) -
                (MapManager.getMapSize().width / 2), (position.y * GRID_CELL_SIZE) -
                (MapManager.getMapSize().height / 2), GRID_CELL_SIZE, GRID_CELL_SIZE, null);
            }
        }
    }
    
    public ImageProviderIntf getImageIntf() {
        return ip;
    }
    
    public void interactEvent() {
        
    }
    
    public boolean canInteract() {
        return canInteract;
    }
    
    public void setInteract(boolean canInteract) {
        this.canInteract = canInteract;
    }
    
    public boolean isBarrier() {
        return isBarrier;
    }
}
