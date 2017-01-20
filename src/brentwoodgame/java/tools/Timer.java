/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.tools;

/**
 *
 * @author Kyle
 */
public class Timer {
    
    int duration;
    int timeElapsed = 0;
    boolean run = true;
    
    private static final int TICKS_PER_SECOND = 40;
    
    public Timer(double duration) {
        this.duration = (int) duration * TICKS_PER_SECOND;
    }
    
    public void timerTaskHandler() {
        if (run) {
            if (isComplete()) pause();
            else timeElapsed++;
        }
    }
    
    public void restart(double duration) {
        this.duration = (int) duration;
        timeElapsed = 0;
        resume();
    }
    
    public void restart() {
        timeElapsed = 0;
        resume();
    }
    
    public void resume() {
        run = true;
    }
    
    public boolean isComplete() {
        return timeElapsed >= duration;
    }
    
    public void pause() {
        run = false;
    }
    
    public double progress() {
        return timeElapsed / duration;
    }
    
}
