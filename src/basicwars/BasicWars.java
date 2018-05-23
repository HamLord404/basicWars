/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;


import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author joshd
 */
public class BasicWars extends Application {
    int xMapSize = 60, yMapSize = 31;
    int xTileSize = 32, yTileSize = 32;
    Tile[][] map = new Tile[xMapSize][yMapSize];
    ArrayList<Player> players = new ArrayList<Player>();
    Pane root = new Pane();
    Pane menu = new Pane();
    Pane mapMaker = new Pane();
    Pane setup = new Pane();
    Scene scene = new Scene(menu, 2000, 1000);
    Button play = new Button("Play");
    Button exit = new Button("Exit");
    Button makeMap = new Button("make map");
    ImageView unitOption = new ImageView("unitoptions.png");
    Unit selected = null;
    ArrayList<Unit> units = new ArrayList<Unit>();
    ArrayList<Building> buildings = new ArrayList<Building>();
    ArrayList<Tile> path  = new ArrayList<Tile>();
    
    boolean showingMoves = false;
    String brush = "grass";
    boolean edit = false;
    int waypoint = 0;
    int turn = 0;
    Menu unitMenu;
    
    ImageView wait = new ImageView("army_wait.png");
    
    @Override
    public void start(Stage primaryStage) {
        createMap();
        
//        for(int i =0; i < xMapSize;i++){
//            for(int j = 0; j < yMapSize;j++){
//                map[i][j] = new Tile("grass",xTileSize*i,yTileSize*j);
//                map[i][j].setX(i);
//                map[i][j].setY(j);
//                mapMaker.getChildren().add(map[i][j].getSprite());
//                map[i][j].getSprite().setOnMouseClicked(this::paint);
//                map[i][j].getSprite().setOnMouseDragOver(this::paint);
//                
//                map[i][j].getSprite().setOnDragEntered(this::paint);
//            }
//            edit = true;
//        }

        wait.setOnMouseClicked(this::unitOption);


        
        menu.getChildren().add(play.getSprite());
        menu.getChildren().add(play.getText());
        menu.getChildren().add(exit.getSprite());
        menu.getChildren().add(exit.getText());
        menu.getChildren().add(makeMap.getSprite());
        menu.getChildren().add(makeMap.getText());
        play.getSprite().setOnMouseClicked(this::MenuButton);
        exit.getSprite().setOnMouseClicked(this::MenuButton);
        makeMap.getSprite().setOnMouseClicked(this::MenuButton);
        play.place(1000, 400);
        makeMap.place(1000, 500);
        exit.place(1000, 600);
        
        root.getChildren().add(unitOption);
        unitOption.setVisible(false);
        
        mapMaker.setOnMouseClicked(this::makeMenu);
        tick();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mapClick(MouseEvent event){
        System.out.print(((ImageView)event.getSource()).getTranslateX()/32);
    }
    
    public void MenuButton(MouseEvent event){
        if(event.getSource() == play.getSprite()){
            scene.setRoot(root);
            players.add(new Player(1));
            players.add(new Player(-.1));
            createUnit("tank",5,5,players.get(0));
            createUnit("heavyTank",10,10,players.get(1));
        }
        if(event.getSource() == makeMap.getSprite()){
            scene.setRoot(mapMaker);
            
        }
        if(event.getSource() == exit.getSprite()){
            System.exit(0);
        }
    }
    
    public void createUnit(String type,int x, int y,Player creater){
        Unit temp = UnitDictionary.create(type, creater, root, x, y);
        temp.getSprite().setOnMouseClicked(this::unitSelect);
        units.add(temp);
        creater.getArmy().add(temp);
        
    }
    
    public void createBuilding(String type, int x, int y){
        Building temp = new Building(type,x,y,root);
        temp.getSprite().setOnMouseClicked(this::selectBuilding);
        buildings.add(temp);
        map[x][y].setStructure(temp);
    } 
    
    public void createMap(){
        
            Random r = new Random();
            for(int i =0; i < xMapSize;i++){
                for(int j = 0; j < yMapSize;j++){


                    map[i][j] = new Tile("grass",xTileSize*i,yTileSize*j);
                    map[i][j].setX(i);
                    map[i][j].setY(j);
                    root.getChildren().add(map[i][j].getSprite());
                    map[i][j].getSprite().setOnMouseClicked(this::mapClick);
                }
            }
            
            for(int i =0; i < xMapSize;i++){
                for(int j = 0; j < yMapSize;j++){
                    //forest generation
                    if(r.nextInt(50) == 0){
                        map[i][j] = new Tile("forest",xTileSize*i,yTileSize*j);
                        map[i][j].setX(i);
                        map[i][j].setY(j);
                        root.getChildren().add(map[i][j].getSprite());
                        map[i][j].getSprite().setOnMouseClicked(this::mapClick);
                        int forestSize = r.nextInt(8)+1;
                        for(int k = 0; k < forestSize; k++){
                            int xoffset = r.nextInt(3)-1;
                            int yoffset = r.nextInt(3)-1;
                            if(i+xoffset < 60 && i+xoffset >= 0 && j+yoffset <= 30 && j+yoffset >= 0 ){
                                map[i+xoffset][j+yoffset].setTerrain("forest");
                                map[i+xoffset][j+yoffset].getSprite().setImage(new Image("forest"+r.nextInt(2)+".png"));
                                map[i+xoffset][j+yoffset].setCost(2);
                            }
                        }
                    }
                    
                    //lake generation
//                    if(r.nextInt(300) == 0){
//                        map[i][j] = new Tile("sea",xTileSize*i,yTileSize*j);
//                        map[i][j].setX(i);
//                        map[i][j].setY(j);
//                        root.getChildren().add(map[i][j].getSprite());
//                        map[i][j].getSprite().setOnMouseClicked(this::mapClick);
//                        int lakeSize = r.nextInt(22)+40;
//                        for(int k = 0; k < lakeSize; k++){
//                            int xoffset = r.nextInt(6)-3;
//                            int yoffset = r.nextInt(6)-3;
//                            if(i+xoffset < 60 && i+xoffset >= 0 && j+yoffset <= 30 && j+yoffset >= 0 ){
//                                map[i+xoffset][j+yoffset].setTerrain("sea");
//                                map[i+xoffset][j+yoffset].getSprite().setImage(new Image("sea.png"));
//                                map[i+xoffset][j+yoffset].setCost(1);
//                                map[i+xoffset][j+yoffset].setLand(false);
//                            }
//                        }
//                    }
                    
                    
                    
                }
            }
            for(int i =0; i < xMapSize;i++){
                for(int j = 0; j < yMapSize;j++){
            //river generation
                    if(r.nextInt(900) == 0){
                        map[i][j] = new Tile("sea",xTileSize*i,yTileSize*j);
                        map[i][j].setX(i);
                        map[i][j].setY(j);
                        root.getChildren().add(map[i][j].getSprite());
                        map[i][j].getSprite().setOnMouseClicked(this::mapClick);
                        int spawn = r.nextInt(60);
                        int xoffset = spawn;
                        int yoffset = 0;
                        while(yoffset != 60){
                            int gen = r.nextInt(10);
                            if(gen <= 6){
                                yoffset++;
                            }
                            if(gen == 7){
                                xoffset++;
                            }
                            if(gen == 8){
                                xoffset--;
                            }
                            if(xoffset < 60 && xoffset >= 0 && yoffset <= 30 && yoffset >= 0 ){
                                map[xoffset][yoffset].setTerrain("sea");
                                map[xoffset][yoffset].getSprite().setImage(new Image("sea.png"));
                                map[xoffset][yoffset].setCost(1);
                                map[xoffset][yoffset].setLand(false);
                            }
                        }
                    }
                }
            }
        
    }
    
    public void displayOptions(Unit unit,Tile loc){
        boolean inRange = false;
        for(int i = 0; i < units.size(); i++){
            if((units.get(i).getSprite().getTranslateX()/32-unit.getSprite().getTranslateX()/32 + units.get(i).getSprite().getTranslateY()/32 - unit.getSprite().getTranslateY()/32) <= unit.getRange()){
                inRange = true;
                i = units.size();
            }
        }
        //if(!inRange && loc.getStructure() == null){
            ArrayList<ImageView> temp = new ArrayList<ImageView>();
            temp.add(new ImageView("army_wait.png"));
            temp.get(0).setOnMouseClicked(this::unitOption);
            unitMenu = new Menu(temp,root,1700,200);
        //}
        
    }
    
    public void unitSelect(MouseEvent event){
        if(showingMoves){
            return;
        }
        for(int i = 0; i < units.size();i++){
            if(event.getSource() == units.get(i).getSprite()){
                selected = units.get(i);
                i = units.size();
            }
        }
        if(selected.getOwner() != players.get(turn)){
            return;
        }
        ArrayList<Tile> list = new ArrayList<Tile>();
        Pathfinder.showValidMoves(((int)((ImageView)event.getSource()).getTranslateX()/32), ((int)((ImageView)event.getSource()).getTranslateY()/32), map, 7, list, root,selected);
        for(int i = 0; i < Pathfinder.valid.size();i++){
            Pathfinder.valid.get(i).setOnMouseClicked(this::move);
        }
        for(int i =0; i < xMapSize;i++){
            for(int j = 0; j < yMapSize;j++){
                map[i][j].setLastMoves(0);
            }
        }
        showingMoves=true;
    }
    
    public void unitOption(MouseEvent event){
        //if(((ImageView)event.getSource()).getImage().toString().equals("army_wait.png")){
            selected.setExhaust(true);
            selected = null;
            unitMenu.clear();
       // }
    }
    
    public void selectBuilding(MouseEvent event){
        //TODO show build menus and stuff
        
    }
    public void move(MouseEvent event){
        for(int i = 0; i < Pathfinder.valid.size(); i++){
            root.getChildren().remove(Pathfinder.valid.get(i));
        }
        Pathfinder.valid.clear();
        path = Pathfinder.paths((int)((ImageView)event.getSource()).getTranslateX()/32, (int)((ImageView)event.getSource()).getTranslateY()/32, (int)selected.getSprite().getTranslateX()/32, (int)selected.getSprite().getTranslateY()/32, map, selected);
        selected.setMoving(true);
        showingMoves=false;
    }
    
    public void target(int x,int y){
        ArrayList<Tile> list = new ArrayList<Tile>();
        Pathfinder.showValidAttacks(x, y, map, 2, list, root,selected);
        for(int i = 0; i < Pathfinder.valid.size();i++){
            Pathfinder.valid.get(i).setOnMouseClicked(this::attack);
        }
        for(int i =0; i < xMapSize;i++){
            for(int j = 0; j < yMapSize;j++){
                map[i][j].setLastMoves(0);
            }
        }
        showingMoves=false;
    }
    
    public void tick(){
        
        
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(.01), new EventHandler<ActionEvent>() {

                        @Override
                        //this happens every tick
                        public void handle(ActionEvent event) {
                            if(selected != null){
                                if(selected.isMoving() && path.size() > 0){
                                    if(selected.getSprite().getTranslateX() < path.get(waypoint).getSprite().getTranslateX()){
                                        selected.getSprite().setTranslateX(selected.getSprite().getTranslateX()+2);
                                    }
                                    if(selected.getSprite().getTranslateX() > path.get(waypoint).getSprite().getTranslateX()){
                                        selected.getSprite().setTranslateX(selected.getSprite().getTranslateX()-2);
                                    }
                                    if(selected.getSprite().getTranslateY() < path.get(waypoint).getSprite().getTranslateY()){
                                        selected.getSprite().setTranslateY(selected.getSprite().getTranslateY()+2);
                                    }
                                    if(selected.getSprite().getTranslateY() > path.get(waypoint).getSprite().getTranslateY()){
                                        selected.getSprite().setTranslateY(selected.getSprite().getTranslateY()-2);
                                    }
                                    if(selected.getSprite().getTranslateX() == path.get(waypoint).getSprite().getTranslateX() && selected.getSprite().getTranslateY() == path.get(waypoint).getSprite().getTranslateY()){
                                        waypoint++;
                                    }
                                    if(waypoint == path.size()){
                                        selected.setMoving(false);
                                        displayOptions(selected,path.get(waypoint-1));
                                       // target((int)selected.getSprite().getTranslateX()/32,(int)selected.getSprite().getTranslateY()/32);
                                        
                                        
                                        waypoint = 0;
                                        
                                    }
                                }
                            }
                            
                            boolean temp = false;
                                for(int i = 0; i < players.get(turn).getArmy().size();i++){
                                    if(!players.get(turn).getArmy().get(i).isExhaust()){
                                        temp = true;
                                        i = players.get(turn).getArmy().size();
                                    }
                                }
                                showingMoves=false;
                            if(!temp){
                                endTurn();
                            }
                            
                        }
                    }));
                    fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
                    fiveSecondsWonder.play(); 
    }
    
    public void makeMenu(MouseEvent event){
        
    }
    
    public void attack(MouseEvent event){
        ImageView temp = null;
        for(int i =0; i < Pathfinder.valid.size(); i++){
            if(Pathfinder.valid.get(i) == event.getSource()){
                temp = Pathfinder.valid.get(i);
            }
        }
        Tile terrain = null;
        for(int i = 0; i < xMapSize;i++){
            for(int j = 0; j < yMapSize;j++){
                if(temp.getTranslateX() == map[i][j].getSprite().getTranslateX() && temp.getTranslateY() == map[i][j].getSprite().getTranslateY()){
                    terrain = map[i][j];
                }
            }
        }
        
        
        Unit target = null;
        for(int i = 0; i < units.size(); i++){
            if(units.get(i).getSprite().getTranslateX() == temp.getTranslateX() && units.get(i).getSprite().getTranslateY() == temp.getTranslateY()){
                target = units.get(i);
            }
        }
        for(int i = 0; i < Pathfinder.valid.size(); i++){
                root.getChildren().remove(Pathfinder.valid.get(i));
        }
        Pathfinder.valid.clear();
        if(target == null){
            selected.setExhaust(true);
            selected = null;
            return;
            
        }
        dmgCalc(selected,target,terrain.getDef());
    }
    
    public void dmgCalc(Unit attacker, Unit defender,int bonus){
        int dmgToDef = (attacker.getHp()*attacker.getAtk())/(defender.getArmour()+bonus);
        System.out.println(dmgToDef);
        defender.setHp(defender.getHp()-dmgToDef);
        if(defender.getHp() <= 0){
            kill(defender);
            return;
        }
        int dmgToAtk = (defender.getHp()*defender.getAtk())/((int)((attacker.getArmour()+bonus)*1.2));
        if(attacker.getHp() <= 0){
            kill(attacker);
            return;
        }
        
       
    }
    
    public void kill(Unit unit){
       root.getChildren().remove(unit.getSprite());
       units.remove(unit);
       unit.getOwner().getArmy().remove(unit);
       //TODO make the dead unit explode
    }
    
    public void paint(MouseEvent event){
        Tile temp = null;
        for(int i = 0; i < xMapSize;i++){
            for(int j = 0; j < yMapSize;j++){
                if(event.getSource() == map[i][j].getSprite()){
                    temp = map[i][j];
                }
            }
        }
        temp.paint(brush);
    }
    
    public void paint(DragEvent event){
        Tile temp = null;
        for(int i = 0; i < xMapSize;i++){
            for(int j = 0; j < yMapSize;j++){
                if(event.getSource() == map[i][j].getSprite()){
                    temp = map[i][j];
                }
            }
        }
        temp.paint(brush);
    }
    
    public void endTurn(){
        turn++;
        if(turn >= players.size()){
            turn = 0;
        }
        for(int i =0; i < units.size(); i++){
            units.get(i).setExhaust(false);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
