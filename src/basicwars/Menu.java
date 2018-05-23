/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author joshd
 */
public class Menu {
    private ArrayList<ImageView> back = new ArrayList<ImageView>();
    private ArrayList<ImageView> options = new ArrayList<ImageView>();
    private Pane root;

    public Menu(ArrayList<ImageView> options,Pane root,int x,int y) {
        this.root = root;
        this.options = options;
        if(options.size() == 1){
            back.add(new ImageView("bguiLone.png"));
        }
        if(options.size() == 2){
            back.add(new ImageView("bguiTop.png"));
            back.add(new ImageView("bguiBot.png"));
        }
        if(options.size() == 3){
            back.add(new ImageView("bguiTop.png"));
            back.add(new ImageView("bguiMid.png"));
            back.add(new ImageView("bguiBot.png"));
        }
        
        for(int i =0; i < back.size();i++){
            root.getChildren().add(back.get(i));
            back.get(i).setTranslateX(x);
            back.get(i).setTranslateY(y+(32*i));
        }
        
        for(int i =0; i < options.size();i++){
            root.getChildren().add(options.get(i));
            options.get(i).setTranslateX(x);
            options.get(i).setTranslateY(y+(32*i));
        }
        
        
        
    }
    
    public void clear(){
        for(int i =0; i < back.size();i++){
            root.getChildren().remove(back.get(i));
        }
        
        for(int i =0; i < options.size();i++){
            root.getChildren().remove(options.get(i));
        }
        
    }
    
    
    
}
