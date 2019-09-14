package sample.model;

import java.util.ArrayList;

public class Player {
    String name;
    double bankRoll;
    ArrayList<Card> playerCards=new ArrayList<Card>();
    int score;
    int bet;
    int splitScore;
    int splitBet;
    int car=10000;
    int wed=10000;
    int whoring=5000;


   private ArrayList<Card> playerSplitCard;

    public ArrayList<Card> getPlayerSplitCard() {
        return playerSplitCard;
    }

    public void setPlayerSplitCard(ArrayList<Card> playerSplitCard) {
        this.playerSplitCard = playerSplitCard;
    }

    public Player(String name, int bet, int score) {
        this.name = name;
        this.bankRoll = 50000;
        this.bet = bet;
        this.score=score;
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBankRoll() {
        return bankRoll;
    }

    public void setBankRoll(double bankRoll) {
        this.bankRoll = bankRoll;
    }
    public void addPlayerCard(Card card){
        this.playerCards.add(card);
        this.setScore(getScore()+card.getVal());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;

    }
    public void addBet(int bet){
        this.bet+=bet;
        this.bankRoll-=bet;
    }

    public int getSplitScore() {
        return splitScore;
    }

    public void setSplitScore(int splitScore) {
        this.splitScore = splitScore;
    }

    public int getSplitBet() {
        return splitBet;
    }

    public void setSplitBet(int splitBet) {
        this.splitBet = splitBet;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getWed() {
        return wed;
    }

    public void setWed(int wed) {
        this.wed = wed;
    }

    public int getWhoring() {
        return whoring;
    }

    public void setWhoring(int whoring) {
        this.whoring = whoring;
    }

    public void preparingSplit(){
        playerSplitCard =new ArrayList<>();
        splitScore=0;
        splitBet=0;
    }
    public void addSplitCard(Card card){
        playerSplitCard.add(card);
        setSplitScore(getSplitScore()+card.getVal());
    }
    public int playernext(boolean split){
        int count=0;
        if(!split){

       for(Card card:playerCards){
           count+=1;
       }

       return count;}
        if(split){

            for(Card card:playerSplitCard){
                count+=1;
            }

            return count;
        }return count;
    }

}
