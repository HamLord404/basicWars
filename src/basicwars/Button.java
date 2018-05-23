/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author joshd
 */
public class Button {
    private ImageView sprite;
    private Label text = new Label();

    public Button(String text) {
        this.text.setText(text);
        sprite = new ImageView("Button.png");
        sprite.setOnMousePressed(this::pressed);
        sprite.setOnMouseReleased(this::released);
    }
    
    public void place(int x, int y){
        sprite.setTranslateX(x);
        sprite.setTranslateY(y);
        
        text.setTranslateX(x);
        text.setTranslateY(y);
    }    
    public void pressed(MouseEvent event){
        sprite.setImage(new Image("ButtonDown.png"));
    }
    public void released(MouseEvent event){
        sprite.setImage(new Image("Button.png"));
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public Label getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }
    
    
    
    
}
