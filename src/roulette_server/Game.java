/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roulette_server;

import java.net.SocketException;
//import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

//import roulette.communication.PlayerProxy;
//import roulette.communication.Server;
import common.CommunicationCommands;
import roulette_server.TableWheel;
import common.Bet;

/**
 *
 * @author POOP
 */
public class Game 
{
    private double playerStartMoney = 200;
    private Hashtable<Integer, Player> players;
    private Croupier croupier;
    private TableWheel table;
    private Integer winningNumber;
    
	// Po?to je samo jedna igra predvi?ena, mogao bi da se koristi
	// uzorak Unikat (singleton). Pomo?u tog uzorka se lako mo?e
	// pro?iriti funkcionalnost da podr?i ve?i (ali kontrolisan)
	// broj igara.
    
    
    // Metoda kojom se prikljucuje nov igrac u igru
	// Metoda vra�a iznos koji je dodeljen igra�u
    public synchronized double newPlayer(PlayerProxy pp)
    {
        Player p = new Player(pp, playerStartMoney, this);
        // Dodati igra�a u kolekciju igra�a koju pamti Game
        //**Jovan
        players.put(new Integer(p.getId()), p);
        //**
        return playerStartMoney;
    }
    
    // Metoda koja sluzi za "broadcast" - slanje poruke svim igracima
    public void sendMessageToAllPlayers(String message)
    {
    	Set<Integer> keys=players.keySet();
        for(Integer k : keys)
        {
        	Player p=players.get(k);
            p.reportMessage(message);
        }
    }
    
    public synchronized void deletePlayer(int playerId)
    {
    	//Ako je pocela partija ne sme da se izbaci igrac. Dodaj to kao 
    	//uslov kada regulises croupiera , i odluci gde bi trebalo da stoji
    	//(u croupier ili u igri)
    	players.remove(playerId);
    }


    public boolean isAcceptingBets()
    {
        return croupier.isAcceptingBets();
    }

    public double getStartingMoney()
    {
        return playerStartMoney;
    }

    public void sendBetToCroupier(int playerId,Bet newBet)
    {
        croupier.addNewBet(playerId,newBet);
    }

    public synchronized void spinTable()
    {
        table.spinWheel();
    }

    public synchronized void updatePlayerMoney(int id, double money) {
        Player player = players.get(id);
        player.updateMoney(money);
        if(money > 0) player.reportMessage(CommunicationCommands.WIN + " " + money);
        else player.reportMessage(CommunicationCommands.WIN + " " + 0);
    }

    public int getWinningNumber()
    {
        return table.getWinningNumber();
    }
    public Game()
    {
    	players=new Hashtable<Integer, Player>();
    	croupier=new Croupier(this);
        table=new TableWheel();
        table.setCroupier(croupier);
    }

    public static void main(String []args) {
        Scanner in = new Scanner(System.in);
        int i = 1;
        try {
            Game g1=new Game();
            Server server = new Server(g1);
            // Napravi igru i sve �to je potrebno da bi se ona pokrenula i odvijala
        }
        catch (SocketException ex)
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    	

    	System.out.println("Nesto");
        while(i == 1){
            i = in.nextInt();
        }
        System.out.println("Igra je zavrsena");
    }
}
