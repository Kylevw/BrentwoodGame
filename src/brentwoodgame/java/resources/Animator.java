/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.resources;

import images.AnimatorEventListenerIntf;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Animator class is designed to allow the class user to provide an
 * ImageManager containing a set of images, each with an associated String
 * identifier, and then create an animation effect by cycling through a subset
 * of those images (this list is defined by the ImageNames).
 *
 * @author Kevin Lawrence
 */
public final class Animator {
    /**
     * TODO 
     *   - completely redesign this object, and the AnimationTimer:
     *     - potentially resource consuming, in that it will run forever: no cancel
     *     - should allow different animation sequences (named => sequence) that would service
     *       several clients from one object: ie would allow getCurrentImage to 
     *       be called with a the sequence name getCurrentImage(String sequence)
     *       and return the image for that sequence
     */
    

//  <editor-fold defaultstate="collapsed" desc="Constructors">
    {
        listeners = new ArrayList<>();
        animationTimer = new Timer();
    }

    /**
     *
     * @param ip
     * @param imageNames a list of string identifiers (must match the
     * identifiers for the related images managed by the imageManager) for the
     * subset of images to be animated
     * @param displayDurationMillis the display time for each of the images
     */
    public Animator(ImageProviderIntf ip, ArrayList<String> imageNames, int displayDurationMillis) {
        this.imageManager = ip;
        this.imageNames = imageNames;
        this.displayDurationMillis = displayDurationMillis;

        initializeTimer();
    }

    /**
     *
     * @param ip
     * @param imageNames
     * @param displayDurationMillis
     * @param listener the AnimatorEventListenerIntf to be added to the
     * notification list for AnimatorEvents
     */
    public Animator(ImageProviderIntf ip, ArrayList<String> imageNames, int displayDurationMillis, AnimatorEventListenerIntf listener) {
        this.imageManager = ip;
        this.imageNames = imageNames;
        this.displayDurationMillis = displayDurationMillis;

        registerAnimatorEventListener(listener);
        initializeTimer();
    }
//  </editor-fold>

//    <editor-fold defaultstate="collapsed" desc="Methods">
    public final void initializeTimer() {
        animationTimer.schedule(new AnimatorTimerTask(this), getDelayDurationMillis(), getDisplayDurationMillis());
    }

    /**
     * @param listener the AnimatorEventListenerIntf to be added to the
     * notification list for AnimatorEvents
     */
    public void registerAnimatorEventListener(AnimatorEventListenerIntf listener) {
        this.listeners.add(listener);
    }

    /**
     * @param listener the AnimatorEventListenerIntf to be removed from the
     * notification list for AnimatorEvents
     */
    public void deregisterAnimatorEventListener(AnimatorEventListenerIntf listener) {
        this.listeners.remove(listener);
    }
//    </editor-fold>

//<editor-fold defaultstate="collapsed" desc="Internal Timer Task - Event Notifier">
    private class AnimatorTimerTask extends TimerTask {

        private Animator animator;

        AnimatorTimerTask(Animator animator) {
            super();
            this.animator = animator;
        }

        @Override
        public void run() {
            incrementImageName();
            notifyListeners();
        }

        private void incrementImageName() {
            if (imageNames != null) {
                if (!imageNames.isEmpty()) {
                    currentImageNameIndex++;
                    currentImageNameIndex %= imageNames.size();
                    setCurrentImageName(imageNames.get(currentImageNameIndex));
                }
            } else {
                this.cancel();
            }
        }

        private void notifyListeners() {
//            for (AnimatorEventListenerIntf listener : listeners) {
//                listener.imageChange(animator);
//            }
        }
    }
//  </editor-fold>

//  <editor-fold defaultstate="collapsed" desc="Properties">
    private ImageProviderIntf imageManager;
    private ArrayList<String> imageNames = new ArrayList<>();
    private int currentImageNameIndex = 0;

    private final ArrayList<AnimatorEventListenerIntf> listeners;

    private final Timer animationTimer;
    private long delayDurationMillis = 0;
    private long displayDurationMillis = 100;

    /**
     * @param imageNames the imageNames to set
     */
    public void setImageNames(ArrayList<String> imageNames) {
        this.imageNames = imageNames;
        currentImageNameIndex = 0;
    }

    /**
     * @return the currentImage
     */
    public BufferedImage getCurrentImage() {
        return (BufferedImage) imageManager.getImage(getCurrentImageName());
    }

    /**
     * @return the currentImageName
     */
    public String getCurrentImageName() {
        try {
            return this.imageNames.get(currentImageNameIndex);
        } catch (IndexOutOfBoundsException e) {
            return this.imageNames.get(0);
        }
    }

    /**
     * @param currentImageName the currentImageName to set; note that this will
     * also set the current image to the first instance of the imageName, i.e.
     * the first instance of that image in the list of images.
     */
    public void initializeCurrentImageName(String currentImageName) {
        this.currentImageNameIndex = imageNames.indexOf(currentImageName);
    }

    /**
     * @param currentImageName the currentImageName to set
     */
    public void setCurrentImageName(String currentImageName) {
        // Make sure the current image index points to a entry that matches the 
        // current image name, if not, call initializeCurrentImageName to reset
        // to the first instance of that name in the list.
        if (!imageNames.get(currentImageNameIndex).equals(currentImageName)) {
            initializeCurrentImageName(currentImageName);
        }
    }

    /**
     * @return the delayDurationMillis
     */
    public long getDelayDurationMillis() {
        return delayDurationMillis;
    }

    /**
     * @param delayDurationMillis the delayDurationMillis to set
     */
    public void setDelayDurationMillis(long delayDurationMillis) {
        this.delayDurationMillis = delayDurationMillis;
    }

    /**
     * @return the displayDurationMillis
     */
    public long getDisplayDurationMillis() {
        return displayDurationMillis;
    }

    /**
     * @param displayDurationMillis the displayDurationMillis to set
     */
    public void setDisplayDurationMillis(long displayDurationMillis) {
        this.displayDurationMillis = displayDurationMillis;
    }
//  </editor-fold>  
}
