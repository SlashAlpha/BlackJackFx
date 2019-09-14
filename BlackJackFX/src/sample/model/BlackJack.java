package sample.model;

import javafx.collections.ObservableList;

public class BlackJack {

    Dealer dealer=new Dealer(0);
    Player player=new Player("",0,0);
    ObservableList<Dealer> deals;
    ObservableList<Player> plays;
    boolean ace;
    boolean blacKjack;



    public Dealer getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }


    public void start(){
        dealer.playableDeck();
        dealer.distribution(player);


    }

    public ObservableList<Dealer> getDeals() {
        return deals;
    }

    public void setDeals(ObservableList<Dealer> deals) {
        this.deals = deals;
    }

    public ObservableList<Player> getPlays() {
        return plays;
    }

    public void setPlays(ObservableList<Player> plays) {
        this.plays = plays;
    }

    public void won(){
        dealer.finalWin(player);
        dealer.getDealerCards().clear();
        player.getPlayerCards().clear();

    }
    public void lost(){
        dealer.finalLose(player);
        dealer.getDealerCards().clear();
        player.getPlayerCards().clear();
    }
    public void Deuce(){
        dealer.finalDeuce(player);
        dealer.getDealerCards().clear();
        player.getPlayerCards().clear();
    }
    public void halfdeuce(){
        dealer.halfdeuce(player);
        dealer.getDealerCards().clear();
        player.getPlayerCards().clear();
    }
    public void nextTurn(){

        dealer.distributeOneCard(0,player,null);
        if(dealer.getScore()<=dealer.getLevel()){

        dealer.distributeOneCard(0,null,dealer);
        }
    }

    public int turnResults(){
        int results=dealer.analyseResults(player.getScore());
        System.out.println(results);
        return results;
    }
    public int turnSplitResult(){
        int results=dealer.analyseSplitResult(player.getScore(),player.getSplitScore());
        System.out.println(results);
        return results;
    }
    public void newPlayer(){
            player=null;
         player=new Player("",0,0);
    }
    public int stay(){
        int count=0;
        while(dealer.getScore()<=dealer.getLevel()){
        count+=1;

            dealer.distributeOneCard(0,null,dealer);
        }return count;
    }
    public void splitCards(){
       dealer.splitCards(player);


    }
    public void nextSplitCard(){
        player.addSplitCard(dealer.getDeckCard());
        dealer.removeCard(0);
  //      if(dealer.getScore()<=dealer.getLevel()){
//
    //        dealer.distributeOneCard(0,null,dealer);
   //     }
    }



}
