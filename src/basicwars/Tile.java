/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author joshd
 */
public class Tile {
    private ImageView sprite;
    private int x;
    private int y;
    private String terrain;
    private boolean land;
    private boolean mountain;
    private int lastMoves = 0;
    private int def = 0;
    private int cost;
    
    private Building structure = null;
    
    public Tile(String terrain,int x, int y){
        this.terrain = terrain;
        this.x = x;
        this.y = y;
        
        
        if(terrain.equals("grass")){
            def = 0;
            Random r = new Random();
            sprite = new ImageView(terrain+r.nextInt(3)+".png");
            cost =1;
            land = true;
            mountain = false;
        }
        if(terrain.equals("forest")){
            def = 1;
            Random r = new Random();
            sprite = new ImageView(terrain+r.nextInt(2)+".png");
            cost = 2;
            land = true;
            mountain = false;
        }
        if(terrain.equals("mountain")){
            def = 3;
            
            sprite = new ImageView(terrain+".png");
            cost = 10;
            land = false;
            mountain = true;
        }
        if(terrain.equals("sea")){
            def = 0;
            
            sprite = new ImageView(terrain+".png");
            cost = 1;
            land = false;
            mountain = false;
        }
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
    }

    public boolean isMountain() {
        return mountain;
    }

    public void setMountain(boolean mountain) {
        this.mountain = mountain;
    }
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Building getStructure() {
        return structure;
    }

    public void setStructure(Building structure) {
        this.structure = structure;
    }
    
    
    
    public void paint(String type){
        terrain = type;
        sprite.setImage(new Image(type+".png"));
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getLastMoves() {
        return lastMoves;
    }

    public void setLastMoves(int lastMoves) {
        this.lastMoves = lastMoves;
    }
    
    

    public boolean isLand() {
        return land;
    }

    public void setLand(boolean land) {
        this.land = land;
    }
    
    

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }
    
    
    
    
}
