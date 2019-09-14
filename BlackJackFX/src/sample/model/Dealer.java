package sample.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Dealer {

     DeckOfCards deck=new DeckOfCards(52);
    boolean ace;
    boolean blackJack;
    private Bank bank=new Bank(0,0);
    public int level=17;
    private int score;
    public int dealerHands=0;
    public boolean splitAble=false;
    HashMap<String,String>tell;
    ArrayList<Card>dealerCards=new ArrayList<>();
    public boolean splitOn=false;


    public ArrayList<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(ArrayList<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }
    public void addDealerCard(Card card){
        this.dealerCards.add(card);

        this.setScore(this.score+card.getVal());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void dealerSpeak(String label, String speak){
        tell.put(label,speak);
    }
    public void textDealer(){
        dealerSpeak("hi","Welcome to casino");
        dealerSpeak("name","What is your name");
        dealerSpeak("deuce","Deuce.Get You bet back.Would you like to continue?");
        dealerSpeak("blackjack","BlackJack!!Would you like to stay in the game?");
        dealerSpeak("step","What is your next step ?");
        dealerSpeak("bankrupt","BankRupt.Please start a new game");
        dealerSpeak("thanks","Thank you for playing");
        dealerSpeak("win","You win.Would you like to continue?");
        dealerSpeak("lost","You lose.Would you like to continue?");
        dealerSpeak("casino bankrupt","Casino Bankrupt, you won it all, you are a winner and our best player");
        dealerSpeak("bet","Please place a new bet to keep playing..");
        dealerSpeak("tokens","you don't have enough tokens,please enter the right amount of tokens :");


    }


    public Dealer(int score) {
        this.tell = new HashMap<>();
        textDealer();
        this.score=score;
    }
    public Card getCard(int index){
        return this.deck.deck.get(index);
    }

    public String dataTextDealer(int choice,String label, String sentence, int data1){
        if(choice==0){
        dealerSpeak(label,sentence+data1);
            return tell.get(label);}
        if (choice==1){
            return tell.get(label)+data1;
        }

        return null;
    }

    public HashMap<String, String> getTell() {
        return tell;
    }

    public void setTell(HashMap<String, String> tell) {
        this.tell = tell;
    }

    public DeckOfCards getDeck() {

        return deck;
    }
    public void randomizeDeck(){
        this.deck.randomizeDeck(this.deck);
    }
    public boolean checkAce(ArrayList<Card> cards){

        for(int i =0;i<cards.size();i++){
            if(cards.get(i).getFig().equals("Ace")){return true;}
        }return false;


    }
    public void switchAceD(){
        boolean switchA=checkAce(getDealerCards());
        if(switchA && (getScore()+10)<=21){setScore(getScore()+10);}

    }
    public boolean checkBlackJack(ArrayList<Card> cards){
        Card one=cards.get(0);
        Card two=cards.get(1);
        if(one.getVal()==1&& two.getVal()==10){

            return true;}
        else if(two.getVal()==1&&one.getVal()==10){
            return true;} else{return false;}
    }

    public void betSystem(Player player,int bet){

        player.addBet(bet);getBank().bankBet(bet);


    }

    private Bank getBank() {
        return bank;
    }

    public void finalWin(Player player){
        player.setBankRoll(player.getBankRoll()+((getBank().getBets())));
        getBank().setBets(0);
        player.setBet(0);
        player.setScore(0);
        this.setScore(0);


    }
    public void finalLose(Player player){
        getBank().setBankLimit(getBank().getBankLimit()+(getBank().getBets()));
        getBank().setBets(0);
        player.setBet(0);
        player.setScore(0);
        this.setScore(0);
    }
    public void finalDeuce(Player player){
        getBank().setBankLimit(getBank().getBankLimit()+getBank().getBets()/2);
        player.setBankRoll(player.getBankRoll()+getBank().getBets()/2);
        getBank().setBets(0);
        player.setBet(0);
        player.setScore(0);
        this.setScore(0);

    }
    public void halfdeuce(Player player){
        splitgain(player);
        player.setScore(0);
        this.setScore(0);
    }

    private void setBank(Bank bank) {
        this.bank = bank;
    }

    public void playableDeck(){
        this.deck=new DeckOfCards(52);
        getDeck().FineDeck();
        getDeck().FineDeck();
        getDeck().FineDeck();
        getDeck().FineDeck();
        randomizeDeck();
    }

    public boolean isAce() {
        return ace;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public void distribution(Player player){

        distributeOneCard(0,null,this);
        distributeOneCard(0,null,this);

       distributeOneCard(0,player,null);
       boolean split=false;
       ArrayList<Card>dummy=new ArrayList<>();
     // while (!split){
       //    if(player.getPlayerCards().get(0).getVal()!=deck.deck.get(0).getVal()){
         //   dummy.add(deck.deck.get(0));
   //            deck.deck.remove(0);
   //        }else{player.getPlayerCards().add(deck.deck.get(0));
   //        player.setScore(deck.deck.get(0).getVal()+player.getScore());
   //        deck.deck.remove(0);
   //       dummy=new ArrayList<>();
   //        split=true;
   //        }
    //  }
        distributeOneCard(0,player,null);


        switchAceD();
        identifySplit(player);
         ace= checkAce(player.getPlayerCards());
         blackJack =checkBlackJack(player.getPlayerCards());
    }
    public void distributeOneCard(int index,Player player,Dealer dealer){
        if(dealer==null){
            player.addPlayerCard(getDeck().deck.get(index));
            getDeck().deck.remove(index);
        }
        else  if (player==null){
            addDealerCard(getDeck().deck.get(index));
            getDeck().deck.remove(0);
        }
    }

    public int getLevel() {
        return level;
    }

    public int analyseResults(int scoreP){
        if(scoreP>21 && getScore()<=21){return -1;}
        else if(scoreP>21 && getScore()>21){return 0;}
        else if (scoreP<=21 && getScore()>21){return 1;}
        else if(scoreP==21 && getScore()==21){return 0;}
        else if(scoreP<getScore()){return -2;}
        else if(scoreP>getScore()){return 2;}
        else if(scoreP==getScore() && getScore()>=getLevel()  ){return 0;}
        else{return -3;}
    }

    public int analyseSplitResult(int scoreP,int scoreSP){
        if (scoreP > 21 && scoreSP > 21 && getScore() <= 21) {return -1;}//next
        else if(scoreP>21 && scoreSP>21 && getScore()>21){return 0;}//next

        else if(scoreP>21 && scoreSP<=21 && getScore()>21){return 0;}//next
        else if(scoreP>21 && scoreSP<=21 && getScore()<=21){if(scoreSP<getScore()){return -1;}
        else if(getScore()==scoreSP){return -3;} return 0;}//next
        else if(scoreP<=21 && scoreSP>21 && getScore()<=21){if (scoreP<getScore()){return -1;}
        else if(scoreP==getScore()){return -3;}
        return 0;}//next
        else if(scoreP<=21 && scoreSP>21 && getScore()>21){return 0;}//next
        else if(scoreP<=21 && scoreSP<=21 && getScore()>21){return 1;}//next & stay
        else if(scoreP>21 && scoreSP==getScore()){return -3;}//stay
        else if (scoreSP>21 && scoreP==getScore()){return -3;}
        else if(scoreP==getScore() && scoreSP>21){return 0;}//stay
        else if(scoreP==getScore() && scoreSP>getScore()){return 1;}
        else if(scoreSP==getScore() && scoreP>getScore()){return 1;}
        else if(scoreP<getScore() && scoreSP>21){return -2;}//stay
        else if(scoreSP<getScore() && scoreP>21){return -2;}//stay
        else if (scoreP<getScore() && scoreSP<getScore()){return -2;}//stay
        else if(scoreP==getScore() && scoreSP==getScore()){return 0;}//stay
        else if (scoreP>getScore() & scoreSP>getScore()){return 2;}
        else if (scoreP<getScore()&& scoreSP>getScore()){return 0;}
        else if (scoreP>getScore() && scoreSP<getScore()){return 0;}
        return -8;


    }
    public double  getBankLimit(){
       return getBank().getBankLimit();
    }
    public void splitCards(Player player){

        player.preparingSplit();
        Card card=player.getPlayerCards().get(1);
        player.setScore(player.getScore()-card.getVal());
        player.getPlayerSplitCard().add(card);
        player.setSplitScore(card.getVal());
        player.setSplitBet(player.getBet());
        player.setBankRoll(player.getBankRoll()-player.getSplitBet());
        bank.bankBet(player.getSplitBet());
        player.getPlayerCards().remove(1);



    }

    public boolean isSplitOn() {
        return splitOn;
    }

    public void setSplitOn(boolean splitOn) {
        this.splitOn = splitOn;
    }

    public boolean identifySplit(Player player){
        if( player.getPlayerCards().get(0).getVal()<10){
        if(player.getPlayerCards().get(0).getVal()==player.getPlayerCards().get(1).getVal()){
            return splitAble=true;
        }}else if(player.getPlayerCards().get(0).getVal()==10){
            if(player.getPlayerCards().get(0).getFig().equals(player.getPlayerCards().get(1).getFig()) && player.getPlayerCards().get(1).getVal()==10){
                return splitAble=true;}
        } return splitAble=false;
    }
    public Card getDeckCard(){
        return getDeck().deck.get(0);
    }
    public void removeCard(int index){
        getDeck().deck.remove(index);
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void splitgain(Player player){

        double playerGain=getBank().getBets()/4;
       double gain=getBank().getBets()-playerGain;
        player.setBankRoll(player.getBankRoll()+(playerGain));
        bank.setBankLimit(bank.getBankLimit()+(gain));
        bank.setBets(0);
        player.setBet(0);
    }
}

