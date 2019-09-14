package sample.model;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
    int number;
    ArrayList<Card> deck=new ArrayList<Card>();
    public String[] color={"Spade","Diamond","Heart","Club"};
    public Integer[] value={1,2,3,4,5,6,7,8,9,10,10,10,10};
    public String [] face ={"Ace","Jack","Queen","King",};
    public boolean [] switchers={true,false};

    public String[] files={"ace_of_spades.png","2_of_spades.png","3_of_spades.png","4_of_spades.png","5_of_spades.png","6_of_spades.png",
            "7_of_spades.png","8_of_spades.png","9_of_spades.png","10_of_spades.png","jack_of_spades.png","queen_of_spades.png","king_of_spades.png",
            "ace_of_diamonds.png","2_of_diamonds.png","3_of_diamonds.png","4_of_diamonds.png","5_of_diamonds.png","6_of_diamonds.png",
            "7_of_diamonds.png","8_of_diamonds.png","9_of_diamonds.png","10_of_diamonds.png","jack_of_diamonds.png","queen_of_diamonds.png",
            "king_of_diamonds.png","ace_of_hearts.png","2_of_hearts.png","3_of_hearts.png","4_of_hearts.png","5_of_hearts.png",
            "6_of_hearts.png","7_of_hearts.png","8_of_hearts.png","9_of_hearts.png","10_of_hearts.png","jack_of_hearts.png","queen_of_hearts.png",
            "king_of_hearts.png","ace_of_clubs.png","2_of_clubs.png","3_of_clubs.png","4_of_clubs.png","5_of_clubs.png","6_of_clubs.png",
            "7_of_clubs.png","8_of_clubs.png","9_of_clubs.png","10_of_clubs.png","jack_of_clubs.png","queen_of_clubs.png","king_of_clubs.png",};



    public void FineDeck(){
        int z=0;
        for(int i=0;i<color.length;i++){

            int count=0;
            for(int d=0;d<value.length;d++){

                if(value[d]<10 && value[d]>1){
                    Card newCard = new Card(color[i],value[d],"faceless",false,files[z]);
                    deck.add(newCard);
                    z+=1;
                }
                else if (value[d]==1){Card newCard = new Card(color[i],value[d], face[0],false,files[z]); deck.add(newCard);z+=1;}
                else if (d==9){Card newCard = new Card(color[i],value[d],"faceless",false,files[z]); deck.add(newCard);z+=1;}
                else if (d!=9 && value[d]>9){count+=1;Card newCard = new Card(color[i],value[d], face[count],false,files[z]); deck.add(newCard);z+=1;}
            }
        }
    }

    public DeckOfCards(int number) {
        this.number = number;
    }
    public void printDeck(){
        int count2=0;
        int count=0;
        for(int i =0;i<deck.size();i++){
            count+=1;
            Card card=deck.get(i);
            if(card.isSwitcher()==true){count2+=1;}
            if(card.getFig()!="faceless"){System.out.println("Card :"+card.getFig()+" of "+card.getCol());}else{
                System.out.println("Card :"+card.getVal()+" of "+card.getCol());}
        }
        System.out.println("number of Cards : "+count);
        System.out.println("switch : "+count2);
    }
    public void printDeck2(){
        for(Card c: deck){
            System.out.println(c.val+c.col+"///"+c.file);
        }
    }
    public void randomizeDeck(DeckOfCards deck){
        Collections.shuffle(deck.deck);
    }



}
