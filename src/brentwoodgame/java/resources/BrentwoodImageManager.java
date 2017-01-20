/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.resources;

import images.ImageManager;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kyle van Wiltenburg
 */
public class BrentwoodImageManager extends ImageManager implements ImageProviderIntf{
    
    public static final int UP_CONNECT = 1;
    public static final int DOWN_CONNECT = UP_CONNECT * 2;
    public static final int LEFT_CONNECT = DOWN_CONNECT * 2;
    public static final int RIGHT_CONNECT = LEFT_CONNECT * 2;
    
    public static final String MISSING_TEXTURE = "MISSING_TEXTURE";
        
    public static final String WALL_TILE_DEBUG_00 = "WALL_TILE_DEBUG_00";
    
    public static final String WALL_TILE_ARTS_00 = "WALL_TILE_ARTS_00";
    
    public static final String FLOOR_TILE_DEBUG_00 = "FLOOR_TILE_DEBUG_00";
    
    public static final String FLOOR_TILE_ARTS_00 = "FLOOR_TILE_ARTS_00";
    
    public static final String DOOR_TILE_DEBUG_00 = "DOOR_TILE_DEBUG_00";
    
    public static final String DOOR_TILE_ARTS_00 = "DOOR_TILE_ARTS_00";
    
    public static final String INTERACT_SIGN = "INTERACT_SIGN";
    
    public static final String ENTITY_SHADOW = "ENTITY_SHADOW";
    
    private static final String BOY_PLAYER_IDLE_UP_00 = "BOY_PLAYER_IDLE_UP_00";
    private static final String BOY_PLAYER_IDLE_DOWN_00 = "BOY_PLAYER_IDLE_DOWN_00";
    private static final String BOY_PLAYER_IDLE_LEFT_00 = "BOY_PLAYER_IDLE_LEFT_00";
    private static final String BOY_PLAYER_IDLE_RIGHT_00 = "BOY_PLAYER_IDLE_RIGHT_00";
    
    private static final String BOY_PLAYER_WALK_DOWN_00 = "BOY_PLAYER_WALK_DOWN_00";
    private static final String BOY_PLAYER_WALK_DOWN_01 = "BOY_PLAYER_WALK_DOWN_01";
    private static final String BOY_PLAYER_WALK_DOWN_02 = "BOY_PLAYER_WALK_DOWN_02";
    
    private static final String BOY_PLAYER_WALK_UP_00 = "BOY_PLAYER_WALK_UP_00";
    private static final String BOY_PLAYER_WALK_UP_01 = "BOY_PLAYER_WALK_UP_01";
    private static final String BOY_PLAYER_WALK_UP_02 = "BOY_PLAYER_WALK_UP_02";
    
    private static final String BOY_PLAYER_WALK_LEFT_00 = "BOY_PLAYER_WALK_LEFT_00";
    private static final String BOY_PLAYER_WALK_LEFT_01 = "BOY_PLAYER_WALK_LEFT_01";
    private static final String BOY_PLAYER_WALK_LEFT_02 = "BOY_PLAYER_WALK_LEFT_02";
    
    private static final String BOY_PLAYER_WALK_RIGHT_00 = "BOY_PLAYER_WALK_RIGHT_00";
    private static final String BOY_PLAYER_WALK_RIGHT_01 = "BOY_PLAYER_WALK_RIGHT_01";
    private static final String BOY_PLAYER_WALK_RIGHT_02 = "BOY_PLAYER_WALK_RIGHT_02";
    
    private static final String GIRL_PLAYER_IDLE_UP_00 = "GIRL_PLAYER_IDLE_UP_00";
    private static final String GIRL_PLAYER_IDLE_DOWN_00 = "GIRL_PLAYER_IDLE_DOWN_00";
    private static final String GIRL_PLAYER_IDLE_LEFT_00 = "GIRL_PLAYER_IDLE_LEFT_00";
    private static final String GIRL_PLAYER_IDLE_RIGHT_00 = "GIRL_PLAYER_IDLE_RIGHT_00";
    
    private static final String GIRL_PLAYER_WALK_DOWN_00 = "GIRL_PLAYER_WALK_DOWN_00";
    private static final String GIRL_PLAYER_WALK_DOWN_01 = "GIRL_PLAYER_WALK_DOWN_01";
    private static final String GIRL_PLAYER_WALK_DOWN_02 = "GIRL_PLAYER_WALK_DOWN_02";
    
    private static final String GIRL_PLAYER_WALK_UP_00 = "GIRL_PLAYER_WALK_UP_00";
    private static final String GIRL_PLAYER_WALK_UP_01 = "GIRL_PLAYER_WALK_UP_01";
    private static final String GIRL_PLAYER_WALK_UP_02 = "GIRL_PLAYER_WALK_UP_02";
    
    private static final String GIRL_PLAYER_WALK_LEFT_00 = "GIRL_PLAYER_WALK_LEFT_00";
    private static final String GIRL_PLAYER_WALK_LEFT_01 = "GIRL_PLAYER_WALK_LEFT_01";
    private static final String GIRL_PLAYER_WALK_LEFT_02 = "GIRL_PLAYER_WALK_LEFT_02";
    
    private static final String GIRL_PLAYER_WALK_RIGHT_00 = "GIRL_PLAYER_WALK_RIGHT_00";
    private static final String GIRL_PLAYER_WALK_RIGHT_01 = "GIRL_PLAYER_WALK_RIGHT_01";
    private static final String GIRL_PLAYER_WALK_RIGHT_02 = "GIRL_PLAYER_WALK_RIGHT_02";
    
    public static final String PLAYER_IDLE_UP_LIST = "PLAYER_IDLE_UP_LIST";
    public static final String PLAYER_IDLE_DOWN_LIST = "PLAYER_IDLE_DOWN_LIST";
    public static final String PLAYER_IDLE_LEFT_LIST = "PLAYER_IDLE_LEFT_LIST";
    public static final String PLAYER_IDLE_RIGHT_LIST = "PLAYER_IDLE_RIGHT_LIST";
    
    public static final String PLAYER_WALK_UP_LIST = "PLAYER_WALK_UP_LIST";
    public static final String PLAYER_WALK_DOWN_LIST = "PLAYER_WALK_DOWN_LIST";
    public static final String PLAYER_WALK_LEFT_LIST = "PLAYER_WALK_LEFT_LIST";
    public static final String PLAYER_WALK_RIGHT_LIST = "PLAYER_WALK_RIGHT_LIST";
    
    public static final String BOY_PLAYER_IDLE_UP_LIST = "BOY_PLAYER_IDLE_UP_LIST";
    public static final String BOY_PLAYER_IDLE_DOWN_LIST = "BOY_PLAYER_IDLE_DOWN_LIST";
    public static final String BOY_PLAYER_IDLE_LEFT_LIST = "BOY_PLAYER_IDLE_LEFT_LIST";
    public static final String BOY_PLAYER_IDLE_RIGHT_LIST = "BOY_PLAYER_IDLE_RIGHT_LIST";
    
    public static final String BOY_PLAYER_WALK_UP_LIST = "BOY_PLAYER_WALK_UP_LIST";
    public static final String BOY_PLAYER_WALK_DOWN_LIST = "BOY_PLAYER_WALK_DOWN_LIST";
    public static final String BOY_PLAYER_WALK_LEFT_LIST = "BOY_PLAYER_WALK_LEFT_LIST";
    public static final String BOY_PLAYER_WALK_RIGHT_LIST = "BOY_PLAYER_WALK_RIGHT_LIST";
    
    public static final String GIRL_PLAYER_IDLE_UP_LIST = "GIRL_PLAYER_IDLE_UP_LIST";
    public static final String GIRL_PLAYER_IDLE_DOWN_LIST = "GIRL_PLAYER_IDLE_DOWN_LIST";
    public static final String GIRL_PLAYER_IDLE_LEFT_LIST = "GIRL_PLAYER_IDLE_LEFT_LIST";
    public static final String GIRL_PLAYER_IDLE_RIGHT_LIST = "GIRL_PLAYER_IDLE_RIGHT_LIST";
    
    public static final String GIRL_PLAYER_WALK_UP_LIST = "GIRL_PLAYER_WALK_UP_LIST";
    public static final String GIRL_PLAYER_WALK_DOWN_LIST = "GIRL_PLAYER_WALK_DOWN_LIST";
    public static final String GIRL_PLAYER_WALK_LEFT_LIST = "GIRL_PLAYER_WALK_LEFT_LIST";
    public static final String GIRL_PLAYER_WALK_RIGHT_LIST = "GIRL_PLAYER_WALK_RIGHT_LIST";
    
    public static final String WALL_TILE_DEBUG_LIST = "WALL_TILE_DEBUG_LIST";
    
    public static final String WALL_TILE_ARTS_LIST = "WALL_TILE_ARTS_LIST";
    
    public static final String FLOOR_TILE_DEBUG_LIST = "FLOOR_TILE_DEBUG_LIST";
    
    public static final String FLOOR_TILE_ARTS_LIST = "FLOOR_TILE_ARTS_LIST";
    
    public static final String DOOR_TILE_DEBUG_LIST = "DOOR_TILE_DEBUG_LIST";
    
    public static final String DOOR_TILE_ARTS_LIST = "DOOR_TILE_ARTS_LIST";
    
    private final ImageManager im;
    
    private final ArrayList<String> BOY_PLAYER_IDLE_UP;
    private final ArrayList<String> BOY_PLAYER_IDLE_DOWN;
    private final ArrayList<String> BOY_PLAYER_IDLE_LEFT;
    private final ArrayList<String> BOY_PLAYER_IDLE_RIGHT;
    
    private final ArrayList<String> BOY_PLAYER_WALK_UP;
    private final ArrayList<String> BOY_PLAYER_WALK_DOWN;
    private final ArrayList<String> BOY_PLAYER_WALK_LEFT;
    private final ArrayList<String> BOY_PLAYER_WALK_RIGHT;
    
    private final ArrayList<String> GIRL_PLAYER_IDLE_UP;
    private final ArrayList<String> GIRL_PLAYER_IDLE_DOWN;
    private final ArrayList<String> GIRL_PLAYER_IDLE_LEFT;
    private final ArrayList<String> GIRL_PLAYER_IDLE_RIGHT;
    
    private final ArrayList<String> GIRL_PLAYER_WALK_UP;
    private final ArrayList<String> GIRL_PLAYER_WALK_DOWN;
    private final ArrayList<String> GIRL_PLAYER_WALK_LEFT;
    private final ArrayList<String> GIRL_PLAYER_WALK_RIGHT;
    
    private final ArrayList<String> WALL_TILE_DEBUG;
    
    private final ArrayList<String> WALL_TILE_ARTS;
    
    private final ArrayList<String> FLOOR_TILE_DEBUG;
    
    private final ArrayList<String> FLOOR_TILE_ARTS;
    
    private final ArrayList<String> DOOR_TILE_DEBUG;
    
    private final ArrayList<String> DOOR_TILE_ARTS;
    
    private final HashMap<String, Image> imageMap;
    private final HashMap<String, ArrayList> imageListMap;
    
    {
        imageListMap = new HashMap<>();
        imageMap = new HashMap<>();
        
        im = new ImageManager(imageMap);
        
        BOY_PLAYER_WALK_UP = new ArrayList<>();
        BOY_PLAYER_WALK_DOWN = new ArrayList<>();
        BOY_PLAYER_WALK_LEFT = new ArrayList<>();
        BOY_PLAYER_WALK_RIGHT = new ArrayList<>();
                
        BOY_PLAYER_IDLE_UP = new ArrayList<>();
        BOY_PLAYER_IDLE_DOWN = new ArrayList<>();
        BOY_PLAYER_IDLE_LEFT = new ArrayList<>();
        BOY_PLAYER_IDLE_RIGHT = new ArrayList<>();
        
        GIRL_PLAYER_WALK_UP = new ArrayList<>();
        GIRL_PLAYER_WALK_DOWN = new ArrayList<>();
        GIRL_PLAYER_WALK_LEFT = new ArrayList<>();
        GIRL_PLAYER_WALK_RIGHT = new ArrayList<>();
                
        GIRL_PLAYER_IDLE_UP = new ArrayList<>();
        GIRL_PLAYER_IDLE_DOWN = new ArrayList<>();
        GIRL_PLAYER_IDLE_LEFT = new ArrayList<>();
        GIRL_PLAYER_IDLE_RIGHT = new ArrayList<>();
        
        WALL_TILE_DEBUG = new ArrayList<>();
        
        WALL_TILE_ARTS = new ArrayList<>();
        
        FLOOR_TILE_DEBUG = new ArrayList<>();
        
        FLOOR_TILE_ARTS = new ArrayList<>();
        
        DOOR_TILE_DEBUG = new ArrayList<>();
        
        DOOR_TILE_ARTS = new ArrayList<>();
        
        BOY_PLAYER_IDLE_UP.add(BOY_PLAYER_IDLE_UP_00);
        BOY_PLAYER_IDLE_DOWN.add(BOY_PLAYER_IDLE_DOWN_00);
        BOY_PLAYER_IDLE_LEFT.add(BOY_PLAYER_IDLE_LEFT_00);
        BOY_PLAYER_IDLE_RIGHT.add(BOY_PLAYER_IDLE_RIGHT_00);
        
        BOY_PLAYER_WALK_UP.add(BOY_PLAYER_WALK_UP_00);
        BOY_PLAYER_WALK_UP.add(BOY_PLAYER_WALK_UP_01);
        BOY_PLAYER_WALK_UP.add(BOY_PLAYER_WALK_UP_02);
        BOY_PLAYER_WALK_UP.add(BOY_PLAYER_WALK_UP_01);
        
        BOY_PLAYER_WALK_DOWN.add(BOY_PLAYER_WALK_DOWN_00);
        BOY_PLAYER_WALK_DOWN.add(BOY_PLAYER_WALK_DOWN_01);
        BOY_PLAYER_WALK_DOWN.add(BOY_PLAYER_WALK_DOWN_02);
        BOY_PLAYER_WALK_DOWN.add(BOY_PLAYER_WALK_DOWN_01);
        
        BOY_PLAYER_WALK_LEFT.add(BOY_PLAYER_WALK_LEFT_00);
        BOY_PLAYER_WALK_LEFT.add(BOY_PLAYER_WALK_LEFT_01);
        BOY_PLAYER_WALK_LEFT.add(BOY_PLAYER_WALK_LEFT_02);
        BOY_PLAYER_WALK_LEFT.add(BOY_PLAYER_WALK_LEFT_01);
        
        BOY_PLAYER_WALK_RIGHT.add(BOY_PLAYER_WALK_RIGHT_00);
        BOY_PLAYER_WALK_RIGHT.add(BOY_PLAYER_WALK_RIGHT_01);
        BOY_PLAYER_WALK_RIGHT.add(BOY_PLAYER_WALK_RIGHT_02);
        BOY_PLAYER_WALK_RIGHT.add(BOY_PLAYER_WALK_RIGHT_01);
        
        GIRL_PLAYER_IDLE_UP.add(GIRL_PLAYER_IDLE_UP_00);
        GIRL_PLAYER_IDLE_DOWN.add(GIRL_PLAYER_IDLE_DOWN_00);
        GIRL_PLAYER_IDLE_LEFT.add(GIRL_PLAYER_IDLE_LEFT_00);
        GIRL_PLAYER_IDLE_RIGHT.add(GIRL_PLAYER_IDLE_RIGHT_00);
        
        GIRL_PLAYER_WALK_DOWN.add(GIRL_PLAYER_WALK_DOWN_00);
        GIRL_PLAYER_WALK_DOWN.add(GIRL_PLAYER_WALK_DOWN_01);
        GIRL_PLAYER_WALK_DOWN.add(GIRL_PLAYER_WALK_DOWN_02);
        GIRL_PLAYER_WALK_DOWN.add(GIRL_PLAYER_WALK_DOWN_01);
        
        GIRL_PLAYER_WALK_UP.add(GIRL_PLAYER_WALK_UP_00);
        GIRL_PLAYER_WALK_UP.add(GIRL_PLAYER_WALK_UP_01);
        GIRL_PLAYER_WALK_UP.add(GIRL_PLAYER_WALK_UP_02);
        GIRL_PLAYER_WALK_UP.add(GIRL_PLAYER_WALK_UP_01);
        
        GIRL_PLAYER_WALK_LEFT.add(GIRL_PLAYER_WALK_LEFT_00);
        GIRL_PLAYER_WALK_LEFT.add(GIRL_PLAYER_WALK_LEFT_01);
        GIRL_PLAYER_WALK_LEFT.add(GIRL_PLAYER_WALK_LEFT_02);
        GIRL_PLAYER_WALK_LEFT.add(GIRL_PLAYER_WALK_LEFT_01);
        
        GIRL_PLAYER_WALK_RIGHT.add(GIRL_PLAYER_WALK_RIGHT_00);
        GIRL_PLAYER_WALK_RIGHT.add(GIRL_PLAYER_WALK_RIGHT_01);
        GIRL_PLAYER_WALK_RIGHT.add(GIRL_PLAYER_WALK_RIGHT_02);
        GIRL_PLAYER_WALK_RIGHT.add(GIRL_PLAYER_WALK_RIGHT_01);
        
        WALL_TILE_DEBUG.add(WALL_TILE_DEBUG_00);
        
        WALL_TILE_ARTS.add(WALL_TILE_ARTS_00);
        
        FLOOR_TILE_DEBUG.add(FLOOR_TILE_DEBUG_00);
        
        FLOOR_TILE_ARTS.add(FLOOR_TILE_ARTS_00);
        
        DOOR_TILE_DEBUG.add(DOOR_TILE_DEBUG_00);
        
        DOOR_TILE_ARTS.add(DOOR_TILE_ARTS_00);
        
        imageListMap.put(BOY_PLAYER_IDLE_UP_LIST, BOY_PLAYER_IDLE_UP);
        imageListMap.put(BOY_PLAYER_IDLE_DOWN_LIST, BOY_PLAYER_IDLE_DOWN);
        imageListMap.put(BOY_PLAYER_IDLE_LEFT_LIST, BOY_PLAYER_IDLE_LEFT);
        imageListMap.put(BOY_PLAYER_IDLE_RIGHT_LIST, BOY_PLAYER_IDLE_RIGHT);
        
        imageListMap.put(BOY_PLAYER_WALK_UP_LIST, BOY_PLAYER_WALK_UP);
        imageListMap.put(BOY_PLAYER_WALK_DOWN_LIST, BOY_PLAYER_WALK_DOWN);
        imageListMap.put(BOY_PLAYER_WALK_LEFT_LIST, BOY_PLAYER_WALK_LEFT);
        imageListMap.put(BOY_PLAYER_WALK_RIGHT_LIST, BOY_PLAYER_WALK_RIGHT);
        
        imageListMap.put(GIRL_PLAYER_IDLE_UP_LIST, GIRL_PLAYER_IDLE_UP);
        imageListMap.put(GIRL_PLAYER_IDLE_DOWN_LIST, GIRL_PLAYER_IDLE_DOWN);
        imageListMap.put(GIRL_PLAYER_IDLE_LEFT_LIST, GIRL_PLAYER_IDLE_LEFT);
        imageListMap.put(GIRL_PLAYER_IDLE_RIGHT_LIST, GIRL_PLAYER_IDLE_RIGHT);
        
        imageListMap.put(GIRL_PLAYER_WALK_UP_LIST, GIRL_PLAYER_WALK_UP);
        imageListMap.put(GIRL_PLAYER_WALK_DOWN_LIST, GIRL_PLAYER_WALK_DOWN);
        imageListMap.put(GIRL_PLAYER_WALK_LEFT_LIST, GIRL_PLAYER_WALK_LEFT);
        imageListMap.put(GIRL_PLAYER_WALK_RIGHT_LIST, GIRL_PLAYER_WALK_RIGHT);
        
        imageListMap.put(WALL_TILE_DEBUG_LIST, WALL_TILE_DEBUG);
        
        imageListMap.put(WALL_TILE_ARTS_LIST, WALL_TILE_ARTS);
        
        imageListMap.put(FLOOR_TILE_DEBUG_LIST, FLOOR_TILE_DEBUG);
        
        imageListMap.put(FLOOR_TILE_ARTS_LIST, FLOOR_TILE_ARTS);
        
        imageListMap.put(DOOR_TILE_DEBUG_LIST, DOOR_TILE_DEBUG);
        
        imageListMap.put(DOOR_TILE_ARTS_LIST, DOOR_TILE_ARTS);
        
        imageMap.put(MISSING_TEXTURE, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/utility/missing_texture.png"));
        imageMap.put(ENTITY_SHADOW, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/utility/entity_shadow.png"));
        
        imageMap.put(WALL_TILE_DEBUG_00, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/wall_debug.png"));
        
        imageMap.put(WALL_TILE_ARTS_00, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/wall_arts.png"));
        
        imageMap.put(FLOOR_TILE_DEBUG_00, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/floor_debug.png"));
        
        imageMap.put(FLOOR_TILE_ARTS_00, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/floor_arts.png"));
        
        imageMap.put(DOOR_TILE_DEBUG_00, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/door_debug.png"));
        
        imageMap.put(DOOR_TILE_ARTS_00, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/door_arts.png"));
        
        imageMap.put(INTERACT_SIGN, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/utility/interact_sign.png"));
        
        BufferedImage playerSprites = (BufferedImage) ResourceTools.loadImageFromResource("brentwoodgame/resources/images/entity/player.png");
        
        imageMap.put(BOY_PLAYER_IDLE_UP_00, playerSprites.getSubimage(16, 16, 16, 16));
        imageMap.put(BOY_PLAYER_IDLE_DOWN_00, playerSprites.getSubimage(0, 16, 16, 16));
        imageMap.put(BOY_PLAYER_IDLE_LEFT_00, playerSprites.getSubimage(32, 16, 16, 16));
        imageMap.put(BOY_PLAYER_IDLE_RIGHT_00, playerSprites.getSubimage(48, 16, 16, 16));
        
        imageMap.put(BOY_PLAYER_WALK_DOWN_00, playerSprites.getSubimage(0, 0, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_DOWN_01, playerSprites.getSubimage(0, 17, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_DOWN_02, playerSprites.getSubimage(0, 32, 16, 16));
        
        imageMap.put(BOY_PLAYER_WALK_UP_00, playerSprites.getSubimage(16, 0, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_UP_01, playerSprites.getSubimage(16, 17, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_UP_02, playerSprites.getSubimage(16, 32, 16, 16));
        
        imageMap.put(BOY_PLAYER_WALK_LEFT_00, playerSprites.getSubimage(32, 0, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_LEFT_01, playerSprites.getSubimage(32, 17, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_LEFT_02, playerSprites.getSubimage(32, 32, 16, 16));
        
        imageMap.put(BOY_PLAYER_WALK_RIGHT_00, playerSprites.getSubimage(48, 0, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_RIGHT_01, playerSprites.getSubimage(48, 17, 16, 16));
        imageMap.put(BOY_PLAYER_WALK_RIGHT_02, playerSprites.getSubimage(48, 32, 16, 16));
        
        imageMap.put(GIRL_PLAYER_IDLE_UP_00, playerSprites.getSubimage(80, 16, 16, 16));
        imageMap.put(GIRL_PLAYER_IDLE_DOWN_00, playerSprites.getSubimage(64, 16, 16, 16));
        imageMap.put(GIRL_PLAYER_IDLE_LEFT_00, playerSprites.getSubimage(96, 16, 16, 16));
        imageMap.put(GIRL_PLAYER_IDLE_RIGHT_00, playerSprites.getSubimage(112, 16, 16, 16));
        
        imageMap.put(GIRL_PLAYER_WALK_DOWN_00, playerSprites.getSubimage(64, 0, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_DOWN_01, playerSprites.getSubimage(64, 17, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_DOWN_02, playerSprites.getSubimage(64, 32, 16, 16));
        
        imageMap.put(GIRL_PLAYER_WALK_UP_00, playerSprites.getSubimage(80, 0, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_UP_01, playerSprites.getSubimage(80, 17, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_UP_02, playerSprites.getSubimage(80, 32, 16, 16));
        
        imageMap.put(GIRL_PLAYER_WALK_LEFT_00, playerSprites.getSubimage(96, 0, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_LEFT_01, playerSprites.getSubimage(96, 17, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_LEFT_02, playerSprites.getSubimage(96, 32, 16, 16));
        
        imageMap.put(GIRL_PLAYER_WALK_RIGHT_00, playerSprites.getSubimage(112, 0, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_RIGHT_01, playerSprites.getSubimage(112, 17, 16, 16));
        imageMap.put(GIRL_PLAYER_WALK_RIGHT_02, playerSprites.getSubimage(112, 32, 16, 16));

    }
    
    @Override
    public BufferedImage getImage(String name){
        BufferedImage image = (BufferedImage) im.getImage(name);
        if (image == null) image = (BufferedImage) im.getImage(MISSING_TEXTURE);
        return image;
    }
    
    @Override
    public BufferedImage getWallImage(String name, boolean[] connectData){
        BufferedImage image = getImage(name);
        if (image == null) return (BufferedImage) im.getImage(MISSING_TEXTURE);
        else {
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage wallImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
            
            for (int x = 0; x < wallImage.getWidth(); x++) {
                for (int y = 0; y < wallImage.getHeight(); y++) {
                    wallImage.setRGB(x, y, image.getRGB(x, y));
                }
            }
            
            Color tintColor = new Color(0, 0, 0, 85);
            
            Boolean connectUp = connectData[0];
            Boolean connectDown = connectData[1];
            Boolean connectLeft = connectData[2];
            Boolean connectRight = connectData[3];
            
            int maxX = wallImage.getWidth() - 1;
            int maxY = wallImage.getHeight() - 1;
            
            wallImage.setRGB(0, 0, tintColor(new Color(image.getRGB(0, 0)), tintColor).getRGB());
            wallImage.setRGB(maxX, 0, tintColor(new Color(image.getRGB(0, 0)), tintColor).getRGB());
            wallImage.setRGB(0, maxY, tintColor(new Color(image.getRGB(0, 0)), tintColor).getRGB());
            wallImage.setRGB(maxX, maxY, tintColor(new Color(image.getRGB(0, 0)), tintColor).getRGB());
            
            if (!connectUp) {
                for (int x = 1; x < wallImage.getWidth() - 1; x++) {
                    wallImage.setRGB(x, 0, tintColor(new Color(image.getRGB(x, 0)), tintColor).getRGB());
                }
            }
            if (!connectDown) {
                for (int x = 1; x < wallImage.getWidth() - 1; x++) {
                    int y = wallImage.getHeight() - 1;
                    wallImage.setRGB(x, y, tintColor(new Color(image.getRGB(x, y)), tintColor).getRGB());
                }
            }
            if (!connectLeft) {
                for (int y = 1; y < wallImage.getHeight() - 1; y++) {
                    wallImage.setRGB(0, y, tintColor(new Color(image.getRGB(0, y)), tintColor).getRGB());
                }
            }
            if (!connectRight) {
                for (int y = 1; y < wallImage.getHeight() - 1; y++) {
                    int x = wallImage.getWidth() - 1;
                    wallImage.setRGB(x, y, tintColor(new Color(image.getRGB(x, y)), tintColor).getRGB());
                }
            }
            return wallImage;
        }
    }
    
    @Override
    public BufferedImage getTintedImage(String imageName, Color tintColor) {
        return BrentwoodImageManager.this.getTintedImage(getImage(imageName), tintColor);
    }
    
    @Override
    public BufferedImage getTintedImage(BufferedImage image, Color tintColor) {
        
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        
        for (int pixelX = 0; pixelX < image.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < image.getHeight(); pixelY++) {
                int pixel = image.getRGB(pixelX, pixelY);
                if((pixel>>24) != 0x00) tintedImage.setRGB(pixelX, pixelY, tintColor(new Color(pixel), tintColor).getRGB());
            }
        }
        
        return tintedImage;
        
    }
    
    @Override
    public ArrayList<String> getImageList(String listName){
        ArrayList<String> arrayList = imageListMap.get(listName);
        if (arrayList == null || arrayList.isEmpty()) {
            arrayList = new ArrayList<>();
            arrayList.add(MISSING_TEXTURE);
        }
        return arrayList;
    }
    
    private Color tintColor(Color color, Color tintColor) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int redTint = tintColor.getRed();
        int greenTint = tintColor.getGreen();
        int blueTint = tintColor.getBlue();
        int tintFactor = tintColor.getAlpha();
        
        int finalRed = red + ((redTint - red) * tintFactor / 255);
        int finalGreen = green + ((greenTint - green) * tintFactor/ 255);
        int finalBlue = blue + ((blueTint - blue) * tintFactor / 255);
        
        return new Color(finalRed, finalGreen, finalBlue, color.getAlpha());
    }
}

