package com.barrostsb.prime_scrum.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean(name = "ringBean")
@ViewScoped
public class RingBean implements Serializable {  
  
    private List<Player> players;  
  
    private Player selectedPlayer;  

    public RingBean() {  
        players = new ArrayList<Player>();  
  
        players.add(new Player("Messi", 10,  "CF"));  
        players.add(new Player("Bojan", 9, "CF"));  
        players.add(new Player("Iniesta", 8,  "CM"));  
        players.add(new Player("Villa", 7,  "CF"));  
        players.add(new Player("Xavi", 6,  "CM"));  
        players.add(new Player("Puyol", 5,  "CB"));  
    }  
  
    public List<Player> getPlayers() {  
        return players;  
    }  
  
    public Player getSelectedPlayer() {  
        return selectedPlayer;  
    }  
  
    public void setSelectedPlayer(Player selectedPlayer) {  
        this.selectedPlayer = selectedPlayer;  
    }  
}  
