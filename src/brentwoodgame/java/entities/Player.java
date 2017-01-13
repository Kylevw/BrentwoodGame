/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.entities;

import java.awt.Dimension;
import brentwoodgame.java.main.Direction;
import brentwoodgame.java.resources.PImageManager;
import brentwoodgame.java.resources.ImageProviderIntf;
import brentwoodgame.java.main.ScreenLimitProviderIntf;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import brentwoodgame.java.main.MapManager;
import brentwoodgame.java.resources.AudioPlayerIntf;

/**
 *
 * @author Kyle van Wiltenburg
 */
public class Player extends Entity {
    
    private static final int PLAYER_WIDTH = 16;
    private static final int PLAYER_HEIGHT = 16;
    private static final int PLAYER_HITBOX_FACTOR = 4;
    private static final int WALK_SPEED = 2;
    
    {
        facing = Direction.DOWN;
//        drawObjectBoundary(true);
    }
    
    private final ScreenLimitProviderIntf screenLimiter;
    
    private final ArrayList<Direction> directions;
    private Direction facingDebug;
    private Direction facing;
    private boolean moving;
    private boolean movingDebug;
    
    private final Point environmentPosition;
    private final Point displacementPosition;
    
    private static final int ANIMATION_SPEED = 160;
    
    
    /**
     * Constructor, returns an instance of the Player class
     *
     * @param position the current position of the entity on screen
     * @param screenLimiter inputs the minimum and maximum positions for the camera
     * @param ip the PImageManager for the entity
     * @param ap the AudioManager for the entity
     * 
     */
    
    public Player(Point position, ScreenLimitProviderIntf screenLimiter, ImageProviderIntf ip, AudioPlayerIntf ap) {

        super(position, new Dimension(PLAYER_WIDTH, PLAYER_HEIGHT), PLAYER_HITBOX_FACTOR, ip, ap, PImageManager.PLAYER_WALK_DOWN_LIST, ANIMATION_SPEED);
        this.directions = new ArrayList<>();
        this.environmentPosition = new Point(position);
        this.displacementPosition = new Point(0, 0);
        this.screenLimiter = screenLimiter;
        screenLimiter.setMaxY(screenLimiter.getMaxY());
        
    }
    
    @Override
    public void timerTaskHandler() {
        
        correctDisplacementPosition();
        
        updateVelocity();
        updateFacingDirection();

        move();
        
        if (facingDebug != facing || movingDebug != moving) {
            updateAnimator();
        }
        
        facingDebug = facing;
        movingDebug = moving;
        
        // Updates the player's position in the world
        setPosition(environmentPosition.x + displacementPosition.x, environmentPosition.y + displacementPosition.y);
        
        super.timerTaskHandler();
        
    }
    
    private void correctDisplacementPosition() {
        if (environmentPosition.x < screenLimiter.getMinX()) {
            int xDifference = environmentPosition.x - screenLimiter.getMinX();
            environmentPosition.x -= xDifference;
            displacementPosition.x += xDifference;
        }
        else if (environmentPosition.x > screenLimiter.getMaxX()) {
            int xDifference = environmentPosition.x - screenLimiter.getMaxX();
            environmentPosition.x -= xDifference;
            displacementPosition.x += xDifference;
        }
        else if (displacementPosition.x != 0 && getPosition().x >= screenLimiter.getMinY() && getPosition().x <= screenLimiter.getMaxY()) {
            environmentPosition.x += displacementPosition.x;
            displacementPosition.x = 0;
        }
        
        if (environmentPosition.y < screenLimiter.getMinX()) {
            int yDifference = environmentPosition.y - screenLimiter.getMinY();
            environmentPosition.y -= yDifference;
            displacementPosition.y += yDifference;
        }
        else if (environmentPosition.y > screenLimiter.getMaxX()) {
            int yDifference = environmentPosition.y - screenLimiter.getMaxY();
            environmentPosition.y -= yDifference;
            displacementPosition.y += yDifference;
        }
        else if (displacementPosition.y != 0 && getPosition().y >= screenLimiter.getMinY() && getPosition().y <= screenLimiter.getMaxY()) {
            environmentPosition.y += displacementPosition.y;
            displacementPosition.y = 0;
        }
    }
    
    private void updateVelocity() {
        
        // If the player's Directions list contains a certain direction, apply that direction to the velocity
        setVelocity(0, 0);
        if (directions.contains(Direction.UP)) accelerate(0, -WALK_SPEED);
        if (directions.contains(Direction.DOWN)) accelerate(0, WALK_SPEED);
        if (directions.contains(Direction.LEFT)) accelerate(-WALK_SPEED, 0);
        if (directions.contains(Direction.RIGHT)) accelerate(WALK_SPEED, 0);
        
        moving = !(getVelocity().x == 0 && getVelocity().y == 0);
    }
    
    private void updateAnimator() {
        if (!moving) {
            switch (facing) {
                case UP: 
                    setImageList(PImageManager.PLAYER_IDLE_UP_LIST);
                    break;
                case DOWN:
                    setImageList(PImageManager.PLAYER_IDLE_DOWN_LIST);
                    break;
                case LEFT:
                    setImageList(PImageManager.PLAYER_IDLE_LEFT_LIST);
                    break;
                case RIGHT:
                    setImageList(PImageManager.PLAYER_IDLE_RIGHT_LIST);
                    break;
            }
        } else {
            switch (facing) {
                case UP: 
                    if (MapManager.checkCollision(new Rectangle(getObjectBoundary().x,
            getObjectBoundary().y + getVelocity().y, getObjectBoundary().width,
            getObjectBoundary().height))) {
                        if (getVelocity().x < 0) setImageList(PImageManager.PLAYER_WALK_LEFT_LIST);
                        else if (getVelocity().x > 0) setImageList(PImageManager.PLAYER_WALK_RIGHT_LIST);
                        else setImageList(PImageManager.PLAYER_WALK_UP_LIST);
                    } else setImageList(PImageManager.PLAYER_WALK_UP_LIST);
                    break;
                case DOWN: 
                    if (MapManager.checkCollision(new Rectangle(getObjectBoundary().x,
            getObjectBoundary().y + getVelocity().y, getObjectBoundary().width,
            getObjectBoundary().height))) {
                        if (getVelocity().x < 0) setImageList(PImageManager.PLAYER_WALK_LEFT_LIST);
                        else if (getVelocity().x > 0) setImageList(PImageManager.PLAYER_WALK_RIGHT_LIST);
                        else setImageList(PImageManager.PLAYER_WALK_DOWN_LIST);
                    } else setImageList(PImageManager.PLAYER_WALK_DOWN_LIST);
                    break;
                case LEFT:  
                    if (MapManager.checkCollision(new Rectangle(getObjectBoundary().x + getVelocity().x,
            getObjectBoundary().y, getObjectBoundary().width,
            getObjectBoundary().height))) {
                        if (getVelocity().y < 0) setImageList(PImageManager.PLAYER_WALK_UP_LIST);
                        else if (getVelocity().y > 0) setImageList(PImageManager.PLAYER_WALK_DOWN_LIST);
                        else setImageList(PImageManager.PLAYER_WALK_LEFT_LIST);
                    } else setImageList(PImageManager.PLAYER_WALK_LEFT_LIST);
                    break;
                case RIGHT:   
                    if (MapManager.checkCollision(new Rectangle(getObjectBoundary().x + getVelocity().x,
            getObjectBoundary().y, getObjectBoundary().width,
            getObjectBoundary().height))) {
                        if (getVelocity().y < 0) setImageList(PImageManager.PLAYER_WALK_UP_LIST);
                        else if (getVelocity().y > 0) setImageList(PImageManager.PLAYER_WALK_DOWN_LIST);
                        else setImageList(PImageManager.PLAYER_WALK_RIGHT_LIST);
                    } else setImageList(PImageManager.PLAYER_WALK_RIGHT_LIST);
                    break;
            }
        }
    }
    
    private void updateFacingDirection() {
        if (directions != null && !directions.isEmpty()) facing = directions.get(directions.size() - 1);
        switch (facing) {
            case UP:
                if (getVelocity().y >= 0) {
                    if (getVelocity().x < 0) facing = Direction.LEFT;
                    else if (getVelocity().x > 0) facing = Direction.RIGHT;
                }
                break;
            case DOWN:
                if (getVelocity().y <= 0) {
                    if (getVelocity().x < 0) facing = Direction.LEFT;
                    else if (getVelocity().x > 0) facing = Direction.RIGHT;
                }
                break;
            case LEFT:
                if (getVelocity().x >= 0) {
                    if (getVelocity().y < 0) facing = Direction.UP;
                    else if (getVelocity().y > 0) facing = Direction.DOWN;
                }
                break;
            case RIGHT:
                if (getVelocity().x <= 0) {
                    if (getVelocity().y < 0) facing = Direction.UP;
                    else if (getVelocity().y > 0) facing = Direction.DOWN;
                }
                break;
        }
    }
    
    @Override
    public void move() {
        
        for (int x = getVelocity().x; Math.abs(x) > 0; x = (Math.abs(x) - 1) * x / Math.abs(x)) {
            if (!MapManager.checkCollision(new Rectangle(getObjectBoundary().x + x,
                getObjectBoundary().y, getObjectBoundary().width,
                getObjectBoundary().height))) {
                environmentPosition.x += x;
                break;
            }
        }
        
        for (int y = getVelocity().y; Math.abs(y) > 0; y = (Math.abs(y) - 1) * y / Math.abs(y)) {
            if (!MapManager.checkCollision(new Rectangle(getObjectBoundary().x,
                getObjectBoundary().y + y, getObjectBoundary().width,
                getObjectBoundary().height))) {
                environmentPosition.y += y;
                break;
            }
        }
        
    }
    
    public ArrayList<Direction> getDirections() {
        return directions;
    }
    
    public void addDirection(Direction direction) {
        directions.add(direction);
    }
    
    public void removeDirection(Direction direction) {
        directions.remove(direction);
    }
    
    public Point getEnvironmentPosition() {
        return environmentPosition;
    }
    
    public Point getDisplacementPosition() {
        return displacementPosition;
    }
    
    public int getScreenMinX() {
        return screenLimiter.getMinX();
    }
    
    public int getScreenMaxX() {
        return screenLimiter.getMaxX();
    }
    
    public int getScreenMinY() {
        return screenLimiter.getMinY();
    }
    
    public int getScreenMaxY() {
        return screenLimiter.getMaxY() - (getSize().height / 2);
    }
    
    public void setScreenLimiter(int screenWidth, int screenHeight) {
        
        screenLimiter.setMinX(-screenWidth / 2);
        screenLimiter.setMinY(-screenHeight / 2);
        screenLimiter.setMaxX(screenWidth / 2);
        screenLimiter.setMaxY(screenHeight / 2);
    }
    
}
