/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author joshd
 */
public class Unit {
    private int hp;
    private int atk;
    private int armour;
    private String name;
    private Player owner;
    private boolean land;
    private boolean sea;
    private boolean mountain;
    private ImageView sprite;
    private int x;
    private int y;
    private int moves;
    private int range;
    private boolean moving = false;
    private boolean exhaust = false;
    public Unit(int atk, int armour, String name, Player owner, boolean land, boolean sea,boolean mountain,Pane root,int x,int y,int moves,int range) {
        this.hp = 10;
        this.atk = atk;
        this.armour = armour;
        this.name = name;
        this.owner = owner;
        this.land = land;
        this.mountain = mountain;
        this.sea = sea;
        sprite = new ImageView(name+".gif");
        root.getChildren().add(sprite);
        this.x = x;
        this.y = y;
        this.moves = moves;
        sprite.setTranslateX(32*x);
        sprite.setTranslateY(32*y);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(owner.getHue());
        colorAdjust.setContrast(.2);
        colorAdjust.setSaturation(1);
        
       colorAdjust.setBrightness(.2);
        sprite.setEffect(colorAdjust);
        this.range = range;
    }

    public boolean isMountain() {
        return mountain;
    }

    public void setMountain(boolean mountain) {
        this.mountain = mountain;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
    
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isExhaust() {
        return exhaust;
    }

    public void setExhaust(boolean exhaust) {
        this.exhaust = exhaust;
    }
    
    

    public int getHp() {
        return hp;
    }
    
    public void place(int x, int y){
        this.x = x;
        this.y = y;
        sprite.setTranslateX(32*x);
        sprite.setTranslateY(32*y);
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isLand() {
        return land;
    }

    public void setLand(boolean land) {
        this.land = land;
    }

    public boolean isSea() {
        return sea;
    }

    public void setSea(boolean sea) {
        this.sea = sea;
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

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
    
    

    
    
    
    
}
