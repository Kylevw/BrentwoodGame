/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.entities;

import environment.Actor;
import environment.Velocity;
import images.Animator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import brentwoodgame.java.main.Map;
import brentwoodgame.java.main.MapManager;
import brentwoodgame.java.resources.AudioPlayerIntf;
import brentwoodgame.java.resources.ImageProviderIntf;
import brentwoodgame.java.resources.PImageManager;

/**
 *
 * @author Kyle
 */
public class Entity extends Actor{
    
    {
        drawBoundary = false;
    }
        
    private boolean despawn;
    
    private final Animator animator;
    
    private final Dimension size;
    private boolean drawBoundary;
    
    private Color tint;
    
    private final ImageProviderIntf ip;
    private final AudioPlayerIntf ap;
    
    private final int objectBoundaryFactor;
    
    public Entity(Point position, Dimension size, int objectBoundaryFactor, ImageProviderIntf ip, AudioPlayerIntf ap, String imageListName, int animationSpeed) {
        super(null, position, new Velocity(0, 0));
        this.ap = ap;
        this.size = size;
        this.ip = ip;
        PImageManager im = new PImageManager();
        this.animator = new Animator(im, ip.getImageList(imageListName), animationSpeed);
        this.objectBoundaryFactor = objectBoundaryFactor;
    }
    
    public Dimension getSize() {
        return size;
    }
    
    public void timerTaskHandler() {
        updateImage();
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawImage(ip.getImage(PImageManager.ENTITY_SHADOW), getObjectBoundary().x, getObjectBoundary().y + 1, getObjectBoundary().width, getObjectBoundary().height, null);
        graphics.drawImage(animator.getCurrentImage(), getPosition().x - (size.width / 2), getPosition().y - size.height, size.width, size.height, null);
        if (drawBoundary) {
            graphics.setColor(Color.RED);
            graphics.draw(getObjectBoundary());
        }
    }
    
    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(getPosition().x - (size.width / 2) + objectBoundaryFactor,
        getPosition().y - (size.height * 3 / 4) + objectBoundaryFactor,
        size.width - (objectBoundaryFactor * 2), size.width - (objectBoundaryFactor * 2));
    }
    
    public void drawObjectBoundary(boolean drawBoundary) {
        this.drawBoundary = drawBoundary;
    }
    
    @Override
    public void move() {
        int xVelocity = getVelocity().x;
        int yVelocity = getVelocity().y;
        for (int x = xVelocity; Math.abs(x) > 0; x = (Math.abs(x) - 1) * x / Math.abs(x)) {
            if (!MapManager.checkCollision(new Rectangle(getObjectBoundary().x + x,
                getObjectBoundary().y, getObjectBoundary().width,
                getObjectBoundary().height))) {
                xVelocity = x;
                break;
            }
        }
        
        for (int y = yVelocity; Math.abs(y) > 0; y = (Math.abs(y) - 1) * y / Math.abs(y)) {
            if (!MapManager.checkCollision(new Rectangle(getObjectBoundary().x,
                getObjectBoundary().y + y, getObjectBoundary().width,
                getObjectBoundary().height))) {
                yVelocity = y;
                break;
            }
        }
        
        setPosition(getPosition().x + xVelocity, getPosition().y + yVelocity);
    }
    
    public boolean drawBoundary() {
        return drawBoundary;
    }
    
    public boolean intersects(Entity entity) {
        return getObjectBoundary().intersects(entity.getObjectBoundary()) &&
        getObjectBoundary().intersects(entity.getObjectBoundary());
    }
    
    public void setImage(String image) {
        super.setImage(ip.getImage(image));
    }
    
    public Animator getAnimator() {
        return animator;
    }
    
    public ImageProviderIntf getImageProvider() {
        return ip;
    }
    
    public AudioPlayerIntf getAudioPlayer() {
        return ap;
    }
    
    public void setDespawn(boolean despawn) {
        this.despawn = despawn;
    }
    
    public void setTint(Color tint) {
        this.tint = tint;
    }
    
    public Color getTint() {
        return tint;
    }
    
    public boolean despawn() {
        return despawn;
    }
    
    private void updateImage() {
        if (animator != null) setImage(animator.getCurrentImage());
        if (tint != null) setImage(getImageProvider().getTintedImage(getImage(), tint));
    }
    
    public void setImageList(String listName) {
        animator.setImageNames(getImageProvider().getImageList(listName));
    }
    
}
