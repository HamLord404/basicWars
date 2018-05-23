/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicwars;

import java.util.ArrayList;

/**
 *
 * @author joshd
 */
public class Player {
    private String name;
    private int playerID;
    private double hue;
    private ArrayList<Unit> army = new ArrayList<Unit>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    public Player(double hue) {
        this.hue = hue;
    }

    public ArrayList<Unit> getArmy() {
        return army;
    }

    public void setArmy(ArrayList<Unit> army) {
        this.army = army;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public double getHue() {
        return hue;
    }

    public void setHue(double hue) {
        this.hue = hue;
    }
    
    
}
