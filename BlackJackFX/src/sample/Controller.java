package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.BlackJack;
import sample.model.Card;
import sample.model.Dealer;
import sample.model.Player;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Controller {
    private static final String CARDURL="src/sample/cards/";

    ListView<Dealer> listDView;
    ListView<Player> listPView;
    @FXML
    SplitPane mainSplitPane;
    @FXML
    ImageView cardstack;
    @FXML
    ImageView cardDealer1;
    @FXML
    ImageView cardDealer2;
    @FXML
    ImageView cardDealer3;
    @FXML
    ImageView cardDealer4;
    @FXML
    ImageView cardDealer5;
    @FXML
    ImageView cardPlayer1;
    @FXML
    ImageView cardPlayer2;
    @FXML
    ImageView cardPlayer3;
    @FXML
    ImageView cardPlayer4;
    @FXML
    ImageView cardPlayer5;
    @FXML
    private Label bankRoll;
    @FXML
    TextField betAmount;
    @FXML
    private Label playerScore;
    @FXML
    private Label playerName;
    @FXML
    private Label dealerScore;
    @FXML
    private Label dealerSpeak;
    @FXML
    private Button bet;
    @FXML
    private Button next;
    @FXML
    private Button stay;
    @FXML
    private MenuItem newGame;
    @FXML
    private Label betReg;
    @FXML
    private Label betAm;
    @FXML
    private Label pScore;
    @FXML
    private Label dScore;
    @FXML
    private Button sWitch;
    @FXML
    private Button doubleBet;
    @FXML
    private Button oK;
    @FXML
    private Button nO;
    @FXML
    private Label casinoBank;
    @FXML
    private Button split;
    @FXML
    private ImageView cardSplit1;
    @FXML
    private ImageView cardSplit2;
    @FXML
    private ImageView cardSplit3;
    @FXML
    private ImageView cardSplit4;
    @FXML
    private ImageView cardSplit5;
    @FXML
    private Button splitNext;

    @FXML
    private Label splitScore;

    @FXML
    private Label sScore;
    @FXML
    private Label splitBet;
    @FXML
    private Label sBet;

    int start=0;
    int playerCount=0;
    int splitCount=0;
    boolean bankrupt=false;
    int dealerCount=0;
    BlackJack blackJack=new BlackJack();
    boolean splited=false;
    @FXML
    public void initialize(){


    }
        //Set up a new game, registering the name and calling for a bet
    public void setNewGame(){

        if(bankrupt){blackJack.newPlayer();}

        if(start<1){

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainSplitPane.getScene().getWindow());
        dialog.setTitle(" Player name");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("playerNameDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog window");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PlayerNameController controller=fxmlLoader.getController();

            blackJack.getPlayer().setName(controller.getplayerName());

            bankRoll.setText(Double.toString(blackJack.getPlayer().getBankRoll()));
            playerName.setText(blackJack.getPlayer().getName());

            dealerSpeak.setText(blackJack.getDealer().getTell().get("hi"));

            dealerSpeak.setText(blackJack.getDealer().getTell().get("bet"));
            if(bankrupt){actualise();}
            bankrupt=false;
            bet.setDisable(false);
        }}else{dealerSpeak.setText("Please finish your game first and leave the table");

        }


    }
    // place the bet clicking bet, unveil buttons
    //make the dealer speak when bankrupt
    public void placeBet(){
        start=1;

        if(start<2 ){if(start>0){

            start=2;
            int betR=Integer.parseInt(betAmount.getText());
            if(betR<=blackJack.getPlayer().getBankRoll() && betR>0 && bankrupt==false){
                blackJack.start();
                control(false);
            blackJack.getDealer().betSystem(blackJack.getPlayer(),betR);
            bankRoll.setText(Double.toString(blackJack.getPlayer().getBankRoll()));
            betAmount.clear();
            betReg.visibleProperty().setValue(true);
            betAm.visibleProperty().setValue(true);
            betAm.setText(Double.toString(blackJack.getPlayer().getBet()));
            dealerSpeak.setText("Now Here is the game");
            gameDistribution();
            bet.setDisable(true);}
            else if(bankrupt){start=0;clearTable();}
            else{dealerSpeak.setText("Bad Amount.enter the right amount");}
        }else{dealerSpeak.setText("Please create a new game first"); betAmount.clear();}}
        else{dealerSpeak.setText("Bet has already been placed"); betAmount.clear();}
    }
        //set up the game and load the fx element for the party to begin
    //launched right after the bet is placed.
    public void gameDistribution(){
        casinoBank.setText(Double.toString(blackJack.getDealer().getBankLimit()));
        setImages(cardDealer1,blackJack.getDealer().getDealerCards(),0);
        setImages(cardDealer2,blackJack.getDealer().getDealerCards(),1);
        setImages(cardPlayer1,blackJack.getPlayer().getPlayerCards(),0);
        setImages(cardPlayer2,blackJack.getPlayer().getPlayerCards(),1);
        dScore.setVisible(true);
        splitNext.setVisible(false);
        dealerScore.setText(Integer.toString(blackJack.getDealer().getScore()));
        pScore.setVisible(true);
        playerScore.setText(Integer.toString(blackJack.getPlayer().getScore()));
        next.setDisable(false);
        stay.setDisable(false);
        start=3;
        playerCount=blackJack.getPlayer().playernext(false);
        doubleBet.setDisable(false);
        sWitch.setDisable(!blackJack.getDealer().checkAce(blackJack.getPlayer().getPlayerCards()));
        split.setDisable(!blackJack.getDealer().splitAble);
        dealerSpeak.setText(blackJack.getDealer().getTell().get("step"));

        if(blackJack.getDealer().isAce()){sWitch.setDisable(!blackJack.getDealer().isAce());}
        if(blackJack.getDealer().isBlackJack()){resultProcess(getSpeak("blackjack"),1);
                blackJack.getPlayer().setScore(21);
        }

    }
    //give the right sentences for the dealer
    public String getSpeak(String key){
       return blackJack.getDealer().getTell().get(key);
    }

    public void stayGame(){
        control(true);
        actualise();
        clearTable();
        if(!bankrupt){
       dealerSpeak.setText(getSpeak("bet"));
        bet.setDisable(false);}
        double s=16+(Math.random()*4);
        int level=(int)s;
        blackJack.getDealer().setLevel(level);
        playerName.setText(Integer.toString(level));

    }
    public void leaveGame(){
        actualise();
        start=0;
        blackJack.newPlayer();
        clearTable();
        actualise();
        setNewGame();

    }
    //clear the playing elements on the table for next parties
    public void clearTable(){
        splited=false;
        oK.setVisible(false);
        nO.setVisible(false);
        cardDealer1.setImage(null);
        cardDealer2.setImage(null);
        cardDealer3.setImage(null);
        cardDealer4.setImage(null);
        cardDealer5.setImage(null);
        cardPlayer1.setImage(null);
        cardPlayer2.setImage(null);
        cardPlayer3.setImage(null);
        cardPlayer4.setImage(null);
        cardPlayer5.setImage(null);
        cardSplit1.setImage(null);
        cardSplit2.setImage(null);
        cardSplit3.setImage(null);
        cardSplit4.setImage(null);
        cardSplit5.setImage(null);

        splitNext.setVisible(false);
        split.setVisible(true);
        split.setDisable(true);
        splitScore.setVisible(false);
        sScore.setVisible(false);
        splitBet.setVisible(false);
        sBet.setVisible(false);
            sWitch.setDisable(true);
        blackJack.getDealer().splitAble=false;
        if(blackJack.getPlayer().getBankRoll()<1){
            start=0;

        dealerSpeak.setText(getSpeak("bankrupt"));
            bankrupt=true;
        }
    }


    //give the right file name for cards and create the image for image view
    public Image cardImage(Card card){
        File file=new File(CARDURL+card.getFile());
        Image image=new Image(file.toURI().toString());
        return image;
    }

    public void control(boolean tell){
        next.setDisable(tell);
        stay.setDisable(tell);
        doubleBet.setDisable(tell);
        split.setDisable(tell);
        splitNext.setVisible(!tell);
    }

    public void pause(int time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }
    //dealing with the next button, add a card as required, no result
    //unless you go over the 21.It follows steps as arraylist of
    //cards and Imageview need to be synchronized.
    public void nextCard(){
        playerCount=blackJack.getPlayer().playernext(false);
        if(!splited){
        if (playerCount==2){

             next(cardPlayer3,cardDealer3,2,2);
        }
       else if(playerCount==3){

            next(cardPlayer4,cardDealer4,3,3);

        }
        else if(playerCount==4){

            next(cardPlayer5,cardDealer5,4,4);
        }}

        else if(splited){
            if(playerCount==1){

            next(cardPlayer2,cardDealer3,1,2);
        }
        else if(playerCount==2){

            next(cardPlayer3,cardDealer4,2,3);
        }
        else if(playerCount==3){

            next(cardPlayer4,cardDealer5,3,4);
        }else{
            blackJack.nextTurn();
            cardPlayer5.setImage(cardImage(blackJack.getPlayer().getPlayerCards().get(4)));
        actualise();
        getResults();
        }
        }
    }
    //deal with the button stay, once the player took the decision to stop and stay
    //the dealer goes on a contained(dealer level 17) automatic loop for his next card
    //look at the stay method in blackjack
    public void stay(){
        int dealPlay=blackJack.stay();
        if(!splited){playerCount=blackJack.getPlayer().playernext(false);}
        else{playerCount=blackJack.getPlayer().playernext(true);}
        System.out.println(dealPlay);
        System.out.println("player count :"+playerCount);
        if(dealPlay==1) {
            if (playerCount == 2) {
                cardDealer3.setImage(cardImage(blackJack.getDealer().getDealerCards().get(2)));
                actualise();
                resultsStay();
            } else if (playerCount == 3) {
                cardDealer4.setImage(cardImage(blackJack.getDealer().getDealerCards().get(3)));
                actualise();
                resultsStay();
            } else if (playerCount == 4) {
                cardDealer5.setImage(cardImage(blackJack.getDealer().getDealerCards().get(4)));
                actualise();
                resultsStay();
            }


        }else if(dealPlay==0){actualise();resultsStay();}
        else if(dealPlay==2){
            if (playerCount==2){cardDealer3.setImage(cardImage(blackJack.getDealer().getDealerCards().get(2)));
                cardDealer4.setImage(cardImage(blackJack.getDealer().getDealerCards().get(3)));
                actualise();
                resultsStay();}
            else if(playerCount==3){cardDealer4.setImage(cardImage(blackJack.getDealer().getDealerCards().get(3)));
                cardDealer5.setImage(cardImage(blackJack.getDealer().getDealerCards().get(4)));
                actualise();
                resultsStay();}
        }else if (dealPlay==3){
            cardDealer3.setImage(cardImage(blackJack.getDealer().getDealerCards().get(2)));
            cardDealer4.setImage(cardImage(blackJack.getDealer().getDealerCards().get(3)));
            cardDealer5.setImage(cardImage(blackJack.getDealer().getDealerCards().get(4)));
            actualise();
            resultsStay();
        }


    }
    //transform the one to eleven in player/dealer(automatic) score
    public void switchAce(){
       blackJack.getPlayer().setScore(blackJack.getPlayer().getScore()+10);
       actualise();
       sWitch.setDisable(true);
    }
        //set the double bet
    public void setDoubleBet(){
        if(playerCount<4){
        blackJack.getDealer().betSystem(blackJack.getPlayer(),blackJack.getPlayer().getBet());
        actualise();}

    }
        //actualise the data on the table
    public void actualise(){
        bankRoll.setText(Double.toString(blackJack.getPlayer().getBankRoll()));
        betAm.setText(Integer.toString(blackJack.getPlayer().getBet()));
        playerScore.setText(Integer.toString(blackJack.getPlayer().getScore()));
        dealerScore.setText(Integer.toString(blackJack.getDealer().getScore()));
        casinoBank.setText(Double.toString(blackJack.getDealer().getBankLimit()));
        sBet.setText(Integer.toString(blackJack.getPlayer().getSplitBet()));
        sScore.setText(Integer.toString(blackJack.getPlayer().getSplitScore()));

    }
    //Analyse the right results when pushing the next button( match only 3 choices from
    //the method analyse results(Dealer), coping with the expected behavior
    //so you can keep playing if you don't match.
    public void getResults(){
        int results=3;
       if(!splited){results= blackJack.turnResults();}
       else {results=blackJack.turnSplitResult();}

       if(results==-1){
           resultProcess(getSpeak("lost"),2);}
        else if(results==1){
           resultProcess(getSpeak("win"),1);}
        else if(results==0 && blackJack.getPlayer().getScore()>=21){
           resultProcess(getSpeak("deuce"),3);
        }
    }
// Analyse the result after pushing stay , "stay"  matches 4 behaviours
 //   from the method analyseResult (Dealer) , will always take a decision
 //after pushing stay ,as the dealer is on a loop for his next cards
// and considering you stop to play after pushing stay
        public void resultsStay(){
            int results=-5;
            if(!splited){results=blackJack.turnResults();}
            else if(splited){results=blackJack.turnSplitResult();}
                if(results==1){
                    resultProcess(getSpeak("win"),1);}
                else if(results==0){
                    resultProcess(getSpeak("deuce"),3);}
               else if(results==-2){
                    resultProcess(getSpeak("lost"),2);}
                else if (results==2){
                    resultProcess(getSpeak("win"),1);}
                else if(results==-1){resultProcess(getSpeak("lost"),2);}
                else if(results==-3){resultProcess("Half Deuce .Get some of your gain,Continue?",4);}

    }




            public void resultProcess(String dealerS, int ending) {
            if(ending==1){dealerSpeak.setText(dealerS);
                blackJack.won();
                control(true);
                oK.setVisible(true);
                nO.setVisible(true);
                start=1;}
            else if(ending==2){ dealerSpeak.setText(dealerS);
                blackJack.lost();
                control(true);
                oK.setVisible(true);
                nO.setVisible(true);
                start=1;}
            else if(ending==3){
                dealerSpeak.setText(dealerS);
                blackJack.Deuce();
                control(true);
                oK.setVisible(true);
                nO.setVisible(true);
                start=1;}
            else if(ending==4){
                dealerSpeak.setText(dealerS);
                blackJack.halfdeuce();
                control(true);
                oK.setVisible(true);
                nO.setVisible(true);
                start=1;
            }
            }

        //update the game in split mode
            public void launchSplit(){
        actualise();
        blackJack.splitCards();

        cardPlayer2.setImage(null);
        cardSplit1.setImage(cardImage(blackJack.getPlayer().getPlayerSplitCard().get(0)));
        split.setVisible(false);
        split.setDisable(true);
        splitNext.setVisible(true);
        splited=true;
        splitBet.setVisible(true);
        sBet.setVisible(true);
        sBet.setText(Integer.toString(blackJack.getPlayer().getSplitBet()));
        splitScore.setVisible(true);
        sScore.setVisible(true);
        sScore.setText(Integer.toString(blackJack.getPlayer().getSplitScore()));
        actualise();
        splitCount=blackJack.getPlayer().playernext(true);
        blackJack.getDealer().setSplitOn(true);
    }
    //add a split card to the split section dealing with split next button
    public void nextSplitCard(){
        if(blackJack.getPlayer().getSplitScore()<=21){
            if(splitCount==1){

                nextSplit(cardSplit2,cardDealer3,1,2);

            }
            else if(splitCount==2){

                nextSplit(cardSplit3,cardDealer4,2,3);

            }
            else if(splitCount==3){

                nextSplit(cardSplit4,cardDealer5,3,4);

            }
            else if(splitCount==4){
                nextSplit(cardSplit5,cardDealer5,4,5);



            }

        }else{dealerSpeak.setText("You lost the split");}}


        //prepare data for the nextCard method :image score split or not
    public void next(ImageView image1,ImageView image2,int step1,int step2){

        int score=blackJack.getDealer().getScore();
        blackJack.nextTurn();
        setImages(image1,blackJack.getPlayer().getPlayerCards(),step1);
        if(score<=blackJack.getDealer().getLevel()){
            setImages(image2,blackJack.getDealer().getDealerCards(),step2);
        }
        actualise();
        playerCount=blackJack.getPlayer().playernext(false);
        getResults();}

        public void nextSplit(ImageView image1,ImageView image2,int step1,int step2){
            int score=blackJack.getDealer().getScore();
            blackJack.nextSplitCard();
            setImages(image1,blackJack.getPlayer().getPlayerSplitCard(),step1);
          //  if(score<=blackJack.getDealer().getLevel()){
           //     setImages(image2,blackJack.getDealer().getDealerCards(),step2);
         //   }
            actualise();
            splitCount=blackJack.getPlayer().playernext(true);
        }


    //set the image for the imageview according to its array apartenance
    public void setImages(ImageView image, ArrayList<Card> cards, int index){
            image.setImage(cardImage(cards.get(index)));
    }
















}
