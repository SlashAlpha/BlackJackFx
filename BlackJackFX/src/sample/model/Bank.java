package sample.model;

import java.util.ArrayList;

public class Bank {
    private double casinoGain;
    private double bets;
    private double bankLimit = 300000;
    ArrayList<Player> players = new ArrayList();


    public Bank(int casinoGain, double bets) {
        this.casinoGain = casinoGain;
        this.bets = bets;

    }
    public void bankBet(int bet){
        this.bets+=2*bet;
        this.bankLimit-=bet;
    }




    public double getBets() {
        return bets;
    }

    public void setBets(double bets) {
        this.bets = bets;

    }

    public double getBankLimit() {
        return bankLimit;
    }

    public void setBankLimit(double bankLimit) {
        this.bankLimit = bankLimit;
    }
}
