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
public class Pathfinder {
    public static ArrayList<ImageView> valid = new ArrayList<ImageView>();
    public static void showValidMoves(int x, int y,Tile[][] map,int moves,ArrayList<Tile> alreadyChecked,Pane root,Unit unit){
        if(moves <= 0){
            return;
        }
       
//        for(int i = 0; i < alreadyChecked.size(); i++){
//            if(alreadyChecked.get(i) == map[x][y]){
//                return;
//            }
//        }
        
        //TODO place the thing tht shows the valid moves
        if(map[x][y].getLastMoves() < moves && ((map[x][y].isLand() && unit.isLand()) || (!map[x][y].isLand() && unit.isSea()) || (map[x][y].isMountain() && unit.isMountain()))){
            System.out.println(map[x][y].getTerrain()+" "+(map[x][y].getLastMoves() < moves && ((map[x][y].isLand() && unit.isLand()) || (!map[x][y].isLand() && unit.isSea()) || (map[x][y].isMountain() && unit.isMountain()))));
            valid.add(new ImageView ("valid.png"));
            valid.get(valid.size()-1).setTranslateX(x*32);
            valid.get(valid.size()-1).setTranslateY(y*32);
            root.getChildren().add(valid.get(valid.size()-1));

            map[x][y].setLastMoves(moves);
            if(x+1 <= 60){
                showValidMoves(x+1,y,map,moves-map[x+1][y].getCost(),alreadyChecked,root,unit);
            }
            if(y+1 <= 31)
                showValidMoves(x,y+1,map,moves-map[x][y+1].getCost(),alreadyChecked,root,unit);
            if(x-1 >= 0){
                showValidMoves(x-1,y,map,moves-map[x-1][y].getCost(),alreadyChecked,root,unit);
            }
            if(y-1 >= 0)
                showValidMoves(x,y-1,map,moves-map[x][y-1].getCost(),alreadyChecked,root,unit);
        
        }
        else{
            return;
        }
        
    }
    
    public static ArrayList<Tile> paths(int targetX,int targetY,int x, int y,Tile[][] map,Unit unit){
        int currX = x,currY = y;
        ArrayList<Tile> path = new ArrayList<Tile>();
        boolean yLock = false;
        while(currX != targetX || currY != targetY){
            
            if(currX < targetX){
               // if((map[currX+1][currY].isLand() && unit.isLand()) || (!map[currX+1][currY].isLand() && unit.isSea()) ||(map[currX][currY-1].isMountain() && unit.isMountain())){
                    
                    currX += 1;
                    path.add(map[currX][currY]);
               // }
            }
            
            if(currX > targetX){
                //if((map[currX-1][currY].isLand() && unit.isLand()) || (!map[currX-1][currY].isLand() && unit.isSea()) ||(map[currX][currY-1].isMountain() && unit.isMountain())){
                    
                    currX -= 1;
                    path.add(map[currX][currY]);
               // }
            }
            if(currY < targetY){
                //if((map[currX][currY+1].isLand() && unit.isLand()) || (!map[currX][currY+1].isLand() && unit.isSea()) ||(map[currX][currY-1].isMountain() && unit.isMountain())){
                    
                    currY += 1;
                    path.add(map[currX][currY]);
//                }
//                else{
//                    yLock = true;
//                }
                
            }
            if(currY > targetY){
//                if((map[currX][currY-1].isLand() && unit.isLand()) || (!map[currX][currY-1].isLand() && unit.isSea()) ||(map[currX][currY-1].isMountain() && unit.isMountain()) ){
                    
                    currY -= 1;
                    path.add(map[currX][currY]);
//                }
//                else{
//                    yLock = true;
//                }
            }
            
//            if(yLock){
//                
//             if((map[currX+1][currY].isLand() && unit.isLand()) || (!map[currX+1][currY].isLand() && unit.isSea()) ||(map[currX][currY-1].isMountain() && unit.isMountain())){
//                    
//                    currX += 1;
//                    path.add(map[currX][currY]);
//            }
//            
//            
//            
//                if((map[currX-1][currY].isLand() && unit.isLand()) || (!map[currX-1][currY].isLand() && unit.isSea()) ||(map[currX][currY-1].isMountain() && unit.isMountain())){
//                    
//                    currX -= 1;
//                    path.add(map[currX][currY]);
//                }
//            }
//            yLock = false;
            
        }
        
        return path;
    }
    
    public static void showValidAttacks(int x, int y,Tile[][] map,int range,ArrayList<Tile> alreadyChecked,Pane root,Unit unit){
        if(range <= 0){
            return;
        }
        
       
//        for(int i = 0; i < alreadyChecked.size(); i++){
//            if(alreadyChecked.get(i) == map[x][y]){
//                return;
//            }
//        }
        
        //TODO place the thing tht shows the valid moves
        if(map[x][y].getLastMoves() < range /*&& ((map[x][y].isLand() && unit.isLand()) || (!map[x][y].isLand() && unit.isSea()))*/){
            if(x != ((int)unit.getSprite().getTranslateX()/32) || y != ((int)unit.getSprite().getTranslateY()/32)){
                valid.add(new ImageView ("validAttack.png"));
                valid.get(valid.size()-1).setTranslateX(x*32);
                valid.get(valid.size()-1).setTranslateY(y*32);
                root.getChildren().add(valid.get(valid.size()-1));
                map[x][y].setLastMoves(range);
            }
            if(x+1 <= 60){
                showValidAttacks(x+1,y,map,range-1,alreadyChecked,root,unit);
            }
            if(y+1 <= 31)
                showValidAttacks(x,y+1,map,range-1,alreadyChecked,root,unit);
            if(x-1 >= 0){
                showValidAttacks(x-1,y,map,range-1,alreadyChecked,root,unit);
            }
            if(y-1 >= 0)
                showValidAttacks(x,y-1,map,range-1,alreadyChecked,root,unit);
            
        }
        else{
            return;
        }
        
    }
}
