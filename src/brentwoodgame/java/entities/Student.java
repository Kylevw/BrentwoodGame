/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.entities;

import brentwoodgame.java.main.Direction;
import brentwoodgame.java.resources.AudioPlayerIntf;
import brentwoodgame.java.resources.ImageProviderIntf;
import brentwoodgame.java.resources.BrentwoodImageManager;
import java.awt.Dimension;
import java.awt.Point;
import timer.DurationTimer;

/**
 *
 * @author Kyle
 */
public class Student extends Entity {
    
    private Direction direction;

    DurationTimer timer;
    
    private final Gender gender;
    private static final int STUDENT_WIDTH = 16;
    private static final int STUDENT_HEIGHT = 16;
    private static final int STUDENT_HITBOX_FACTOR = 4;
    private static final int WALK_SPEED = 1;
    
    private static final int ANIMATION_SPEED = 160;
    
    private static final int MIN_WALK_TIME = 3000;
    private static final int MAX_WALK_TIME = 4500;
    
    public Student(Point position, ImageProviderIntf ip, AudioPlayerIntf ap, Gender gender) {
        super(position, new Dimension(STUDENT_WIDTH, STUDENT_HEIGHT), STUDENT_HITBOX_FACTOR, ip, ap, null, ANIMATION_SPEED);
        this.gender = gender;
        direction = Direction.DOWN;
        changeDirection();
        timer = new DurationTimer((int) (MIN_WALK_TIME + ((MAX_WALK_TIME - MIN_WALK_TIME) * Math.random())));
    }
    
    public Student(Point position, ImageProviderIntf ip, AudioPlayerIntf ap, Gender gender, Direction startDirection) {
        super(position, new Dimension(STUDENT_WIDTH, STUDENT_HEIGHT), STUDENT_HITBOX_FACTOR, ip, ap, null, ANIMATION_SPEED);
        this.gender = gender;
        this.direction = startDirection;
        timer = new DurationTimer((int) (MIN_WALK_TIME + ((MAX_WALK_TIME - MIN_WALK_TIME) * Math.random())));
    }
    
    @Override
    public void timerTaskHandler() {
        if (timer.isComplete()) {
            changeDirection();
            timer.start();
        }
        super.timerTaskHandler();
    }
    
    @Override
    public void collisionAI() {
        changeDirection();
        timer.start();
    }
    
    private void changeDirection() {
        if (Math.random() * 2 >= 1) turnLeft();
        else turnRight();
    }
    
    public void turnLeft() {
        if (direction != null) {
            switch (direction) {
                case UP:
                    direction = Direction.LEFT;
                    setVelocity(-WALK_SPEED, 0);
                    updateAnimator();
                    break;
                case LEFT:
                    direction = Direction.DOWN;
                    setVelocity(0, WALK_SPEED);
                    updateAnimator();
                    break;
                case DOWN:
                    direction = Direction.RIGHT;
                    setVelocity(WALK_SPEED, 0);
                    updateAnimator();
                    break;
                case RIGHT:
                    direction = Direction.UP;
                    setVelocity(0, -WALK_SPEED);
                    updateAnimator();
                    break;
            }
        }
    }
    
    public void turnRight() {
        if (direction != null) {
            switch (direction) {
                case DOWN:
                    direction = Direction.LEFT;
                    setVelocity(-WALK_SPEED, 0);
                    updateAnimator();
                    break;
                case RIGHT:
                    direction = Direction.DOWN;
                    setVelocity(0, WALK_SPEED);
                    updateAnimator();
                    break;
                case UP:
                    direction = Direction.RIGHT;
                    setVelocity(WALK_SPEED, 0);
                    updateAnimator();
                    break;
                case LEFT:
                    direction = Direction.UP;
                    setVelocity(0, -WALK_SPEED);
                    updateAnimator();
                    break;
            }
        }
    }
    
    public void setPlayerImageList(String listName) {
        if (gender == Gender.GIRL) {
            listName = "GIRL_" + listName;
        } else listName = "BOY_" + listName;
        setImageList(listName);
    }
    
    private void updateAnimator() {
        switch (direction) {
                case UP: 
                    setPlayerImageList(BrentwoodImageManager.PLAYER_WALK_UP_LIST);
                    break;
                case DOWN:
                    setPlayerImageList(BrentwoodImageManager.PLAYER_WALK_DOWN_LIST);
                    break;
                case LEFT:
                    setPlayerImageList(BrentwoodImageManager.PLAYER_WALK_LEFT_LIST);
                    break;
                case RIGHT:
                    setPlayerImageList(BrentwoodImageManager.PLAYER_WALK_RIGHT_LIST);
                    break;
        }
    }
    
}
