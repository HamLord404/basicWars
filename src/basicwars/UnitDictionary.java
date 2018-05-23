/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import javafx.scene.layout.Pane;

/**
 *
 * @author joshd
 */
public class UnitDictionary {
    public static Unit create(String type,Player creater,Pane root,int x,int y){
        if(type.equals("tank")){
            return new Unit(4,5,"tank",creater,true,false,false,root,x,y, 7,1);
        }
        if(type.equals("heavyTank")){
            return new Unit(5,6,"heavytank",creater,true,false,false,root,x,y, 5,1);
        }
        if(type.equals("infantry")){
            return new Unit(4,2,"infantry",creater,true,false,true,root,x,y,2,1);
        }
        
        return null;
    }
}
