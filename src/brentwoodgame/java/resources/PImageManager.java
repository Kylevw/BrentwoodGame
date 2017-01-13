/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brentwoodgame.java.resources;

import images.ImageManager;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kyle van Wiltenburg
 */
public class PImageManager extends ImageManager implements ImageProviderIntf{
    
    public static final String MISSING_TEXTURE = "MISSING_TEXTURE";
    
    public static final String INVENTORY_SLOT = "INVENTORY_SLOT";
    public static final String ITEM_SWORD = "ITEM_SWORD";
    public static final String ITEM_BOW = "ITEM_BOW";
    
    public static final String WALL_TILE = "WALL_TILE";
    public static final String WALL_TILE_CONNECTOR_UP = "WALL_TILE_CONNECTOR_UP";
    public static final String WALL_TILE_CONNECTOR_DOWN = "WALL_TILE_CONNECTOR_DOWN";
    public static final String WALL_TILE_CONNECTOR_LEFT = "WALL_TILE_CONNECTOR_LEFT";
    public static final String WALL_TILE_CONNECTOR_RIGHT = "WALL_TILE_CONNECTOR_RIGHT";
    
    public static final String ENTITY_SHADOW = "ENTITY_SHADOW";
    
    private static final String PLAYER_IDLE_UP_00 = "PLAYER_IDLE_UP_00";
    private static final String PLAYER_IDLE_DOWN_00 = "PLAYER_IDLE_DOWN_00";
    private static final String PLAYER_IDLE_LEFT_00 = "PLAYER_IDLE_LEFT_00";
    private static final String PLAYER_IDLE_RIGHT_00 = "PLAYER_IDLE_RIGHT_00";
    
    private static final String PLAYER_WALK_DOWN_00 = "PLAYER_WALK_DOWN_00";
    private static final String PLAYER_WALK_DOWN_01 = "PLAYER_WALK_DOWN_01";
    private static final String PLAYER_WALK_DOWN_02 = "PLAYER_WALK_DOWN_02";
    
    private static final String PLAYER_WALK_UP_00 = "PLAYER_WALK_UP_00";
    private static final String PLAYER_WALK_UP_01 = "PLAYER_WALK_UP_01";
    private static final String PLAYER_WALK_UP_02 = "PLAYER_WALK_UP_02";
    
    private static final String PLAYER_WALK_LEFT_00 = "PLAYER_WALK_LEFT_00";
    private static final String PLAYER_WALK_LEFT_01 = "PLAYER_WALK_LEFT_01";
    private static final String PLAYER_WALK_LEFT_02 = "PLAYER_WALK_LEFT_02";
    
    private static final String PLAYER_WALK_RIGHT_00 = "PLAYER_WALK_RIGHT_00";
    private static final String PLAYER_WALK_RIGHT_01 = "PLAYER_WALK_RIGHT_01";
    private static final String PLAYER_WALK_RIGHT_02 = "PLAYER_WALK_RIGHT_02";
    
    public static final String PLAYER_IDLE_UP_LIST = "PLAYER_IDLE_UP_LIST";
    public static final String PLAYER_IDLE_DOWN_LIST = "PLAYER_IDLE_DOWN_LIST";
    public static final String PLAYER_IDLE_LEFT_LIST = "PLAYER_IDLE_LEFT_LIST";
    public static final String PLAYER_IDLE_RIGHT_LIST = "PLAYER_IDLE_RIGHT_LIST";
    
    public static final String PLAYER_WALK_UP_LIST = "PLAYER_WALK_UP_LIST";
    public static final String PLAYER_WALK_DOWN_LIST = "PLAYER_WALK_DOWN_LIST";
    public static final String PLAYER_WALK_LEFT_LIST = "PLAYER_WALK_LEFT_LIST";
    public static final String PLAYER_WALK_RIGHT_LIST = "PLAYER_WALK_RIGHT_LIST";
    
    private final ImageManager im;
    
    private final ArrayList<String> PLAYER_IDLE_UP;
    private final ArrayList<String> PLAYER_IDLE_DOWN;
    private final ArrayList<String> PLAYER_IDLE_LEFT;
    private final ArrayList<String> PLAYER_IDLE_RIGHT;
    
    private final ArrayList<String> PLAYER_WALK_UP;
    private final ArrayList<String> PLAYER_WALK_DOWN;
    private final ArrayList<String> PLAYER_WALK_LEFT;
    private final ArrayList<String> PLAYER_WALK_RIGHT;
    
    private final HashMap<String, Image> imageMap;
    private final HashMap<String, ArrayList> imageListMap;
    
    {
        imageListMap = new HashMap<>();
        imageMap = new HashMap<>();
        
        im = new ImageManager(imageMap);
        
        PLAYER_WALK_UP = new ArrayList<>();
        PLAYER_WALK_DOWN = new ArrayList<>();
        PLAYER_WALK_LEFT = new ArrayList<>();
        PLAYER_WALK_RIGHT = new ArrayList<>();
                
        PLAYER_IDLE_UP = new ArrayList<>();
        PLAYER_IDLE_DOWN = new ArrayList<>();
        PLAYER_IDLE_LEFT = new ArrayList<>();
        PLAYER_IDLE_RIGHT = new ArrayList<>();
        
        PLAYER_IDLE_UP.add(PLAYER_IDLE_UP_00);
        PLAYER_IDLE_DOWN.add(PLAYER_IDLE_DOWN_00);
        PLAYER_IDLE_LEFT.add(PLAYER_IDLE_LEFT_00);
        PLAYER_IDLE_RIGHT.add(PLAYER_IDLE_RIGHT_00);
        
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_00);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_01);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_02);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_01);
        
        PLAYER_WALK_UP.add(PLAYER_WALK_UP_00);
        PLAYER_WALK_UP.add(PLAYER_WALK_UP_01);
        PLAYER_WALK_UP.add(PLAYER_WALK_UP_02);
        PLAYER_WALK_UP.add(PLAYER_WALK_UP_01);
        
        PLAYER_WALK_LEFT.add(PLAYER_WALK_LEFT_00);
        PLAYER_WALK_LEFT.add(PLAYER_WALK_LEFT_01);
        PLAYER_WALK_LEFT.add(PLAYER_WALK_LEFT_02);
        PLAYER_WALK_LEFT.add(PLAYER_WALK_LEFT_01);
        
        PLAYER_WALK_RIGHT.add(PLAYER_WALK_RIGHT_00);
        PLAYER_WALK_RIGHT.add(PLAYER_WALK_RIGHT_01);
        PLAYER_WALK_RIGHT.add(PLAYER_WALK_RIGHT_02);
        PLAYER_WALK_RIGHT.add(PLAYER_WALK_RIGHT_01);
        
        imageListMap.put(PLAYER_IDLE_UP_LIST, PLAYER_IDLE_UP);
        imageListMap.put(PLAYER_IDLE_DOWN_LIST, PLAYER_IDLE_DOWN);
        imageListMap.put(PLAYER_IDLE_LEFT_LIST, PLAYER_IDLE_LEFT);
        imageListMap.put(PLAYER_IDLE_RIGHT_LIST, PLAYER_IDLE_RIGHT);
        
        imageListMap.put(PLAYER_WALK_UP_LIST, PLAYER_WALK_UP);
        imageListMap.put(PLAYER_WALK_DOWN_LIST, PLAYER_WALK_DOWN);
        imageListMap.put(PLAYER_WALK_LEFT_LIST, PLAYER_WALK_LEFT);
        imageListMap.put(PLAYER_WALK_RIGHT_LIST, PLAYER_WALK_RIGHT);
        
        imageMap.put(MISSING_TEXTURE, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/utility/missing_texture.png"));
        imageMap.put(ENTITY_SHADOW, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/utility/entity_shadow.png"));
        
        imageMap.put(INVENTORY_SLOT, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/utility/inventory_slot.png"));
        imageMap.put(ITEM_SWORD, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/item/sword.png"));
        imageMap.put(ITEM_BOW, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/item/bow.png"));
        
        imageMap.put(WALL_TILE, ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/wall.png"));
        
        BufferedImage playerSprites = (BufferedImage) ResourceTools.loadImageFromResource("brentwoodgame/resources/images/entity/player.png");
        BufferedImage wallConnector = (BufferedImage) ResourceTools.loadImageFromResource("brentwoodgame/resources/images/tiles/wall_connect.png");
        
        imageMap.put(PLAYER_IDLE_UP_00, playerSprites.getSubimage(16, 16, 16, 16));
        imageMap.put(PLAYER_IDLE_DOWN_00, playerSprites.getSubimage(0, 16, 16, 16));
        imageMap.put(PLAYER_IDLE_LEFT_00, playerSprites.getSubimage(32, 16, 16, 16));
        imageMap.put(PLAYER_IDLE_RIGHT_00, playerSprites.getSubimage(48, 16, 16, 16));
        
        imageMap.put(PLAYER_WALK_DOWN_00, playerSprites.getSubimage(0, 0, 16, 16));
        imageMap.put(PLAYER_WALK_DOWN_01, playerSprites.getSubimage(0, 17, 16, 16));
        imageMap.put(PLAYER_WALK_DOWN_02, playerSprites.getSubimage(0, 32, 16, 16));
        
        imageMap.put(PLAYER_WALK_UP_00, playerSprites.getSubimage(16, 0, 16, 16));
        imageMap.put(PLAYER_WALK_UP_01, playerSprites.getSubimage(16, 17, 16, 16));
        imageMap.put(PLAYER_WALK_UP_02, playerSprites.getSubimage(16, 32, 16, 16));
        
        imageMap.put(PLAYER_WALK_LEFT_00, playerSprites.getSubimage(32, 0, 16, 16));
        imageMap.put(PLAYER_WALK_LEFT_01, playerSprites.getSubimage(32, 17, 16, 16));
        imageMap.put(PLAYER_WALK_LEFT_02, playerSprites.getSubimage(32, 32, 16, 16));
        
        imageMap.put(PLAYER_WALK_RIGHT_00, playerSprites.getSubimage(48, 0, 16, 16));
        imageMap.put(PLAYER_WALK_RIGHT_01, playerSprites.getSubimage(48, 17, 16, 16));
        imageMap.put(PLAYER_WALK_RIGHT_02, playerSprites.getSubimage(48, 32, 16, 16));
        
        imageMap.put(WALL_TILE_CONNECTOR_UP, wallConnector.getSubimage(1, 0, 14, 2));
        imageMap.put(WALL_TILE_CONNECTOR_DOWN, wallConnector.getSubimage(1, 14, 14, 2));
        imageMap.put(WALL_TILE_CONNECTOR_LEFT, wallConnector.getSubimage(0, 1, 2, 14));
        imageMap.put(WALL_TILE_CONNECTOR_RIGHT, wallConnector.getSubimage(14, 1, 2, 14));

    }
    
    @Override
    public BufferedImage getImage(String name){
        BufferedImage image = (BufferedImage) im.getImage(name);
        if (image == null) image = (BufferedImage) im.getImage(MISSING_TEXTURE);
        return image;
    }
    
    @Override
    public BufferedImage getTintedImage(String imageName, Color tintColor) {
        return PImageManager.this.getTintedImage(getImage(imageName), tintColor);
    }
    
    @Override
    public BufferedImage getTintedImage(BufferedImage image, Color tintColor) {
        
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage tintedImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        
        for (int pixelX = 0; pixelX < image.getWidth(); pixelX++) {
            for (int pixelY = 0; pixelY < image.getHeight(); pixelY++) {
                
                int pixel = image.getRGB(pixelX, pixelY);
                if((pixel>>24) != 0x00) {        
                
                Color color = new Color(image.getRGB(pixelX, pixelY));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int redTint = tintColor.getRed();
                int greenTint = tintColor.getGreen();
                int blueTint = tintColor.getBlue();
                
                int finalRed = red + ((redTint - red) * tintColor.getAlpha() / 255);
                int finalGreen = green + ((greenTint - green) * tintColor.getAlpha() / 255);
                int finalBlue = blue + ((blueTint - blue) * tintColor.getAlpha() / 255);
                
                Color newColor = new Color(finalRed, finalGreen, finalBlue, color.getAlpha());
                
                tintedImage.setRGB(pixelX, pixelY, newColor.getRGB());
                }
            }
        }
        
        return tintedImage;
        
    }
    
    public void drawTint(Graphics2D graphics) {
        
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
}

