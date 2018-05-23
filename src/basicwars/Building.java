/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author joshd
 */
public class Building {
    private Player owner = null;
    private String type;
    private int tax;
    private int x;
    private int y;
    private int captureProg = 0;
    private ImageView sprite;

    public Building(String type,int x, int y,Pane root) {
        this.type = type;
        if(type.equals("city")){
            tax = 1000;
            sprite = new ImageView(type+".png");
        }
        this.x = x;
        this.y = y;
        root.getChildren().add(sprite);
        sprite.setTranslateX(x*32);
        sprite.setTranslateY(y*32);
    }
    
    

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
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

    public int getCaptureProg() {
        return captureProg;
    }

    public void setCaptureProg(int captureProg) {
        this.captureProg = captureProg;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }
    
    
    
}
