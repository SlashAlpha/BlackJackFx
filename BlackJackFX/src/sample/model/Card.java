package sample.model;

import java.util.HashMap;

public class Card {
    String col;
    int val;
    String fig;
    boolean switcher;
    String file;

    public String[] color={"Spade","Diamond","Heart","Club"};
    public Integer[] value={1,2,3,4,5,6,7,8,9,10,10,10,10};
    public String []face={"Ace","Jack","Queen","King"};
    public String[] files={"ace_of_spades.png","2_of_spades.png","3_of_spades.png","4_of_spades.png","5_of_spades.png","6_of_spades.png",
            "7_of_spades.png","8_of_spades.png","9_of_spades.png","10_of_spades.png","jack_of_spades.png","queen_of_spades.png","king_of_spades.png",
            "ace_of_diamonds.png","2_of_diamonds.png","3_of_diamonds.png","4_of_diamonds.png","5_of_diamonds.png","6_of_diamonds.png",
            "7_of_diamonds.png","8_of_diamonds.png","9_of_diamonds.png","10_of_diamonds.png","jack_of_diamonds.png","queen_of_diamonds.png",
            "king_of_diamonds.png","ace_of_hearts.png","2_of_hearts.png","3_of_hearts.png","4_of_hearts.png","5_of_hearts.png",
            "6_of_hearts.png","7_of_hearts.png","8_of_hearts.png","9_of_hearts.png","10_of_hearts.png","jack_of_hearts.png","queen_of_hearts.png",
            "king_of_hearts.png","ace_of_clubs.png","2_of_clubs.png","3_of_clubs.png","4_of_clubs.png","5_of_clubs.png","6_of_clubs.png",
            "7_of_clubs.png","8_of_clubs.png","9_of_clubs.png","10_of_clubs.png","jack_of_clubs.png","queen_of_clubs.png","king_of_clubs.png",};



    public void generateRandCard(){
        int rand= (int) ((Math.random()*3)+1);
        this.col = color[(int) (Math.random()*4)];
        this.val = value[(int) (Math.random()*13)];
        if(val==1){this.fig=face[0];}
        if(val<10 &&val>1){this.fig=null;}
        if(val>9){this.fig=face[rand];}
    }

    public Card(String col, int val, String fig,boolean switcher,String file) {
        this.col = col;
        this.val = val;
        this.fig = fig;
        this.switcher = switcher;
        this.file=file;
    }

    public String getCol() {
        return col;
    }

    public int getVal() {
        return val;
    }

    public String getFig() {
        return fig;
    }

    public boolean isSwitcher() {
        return switcher;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void displayCard(Card card){
        if(card.getFig()==null){
            System.out.println(card.getVal()+" of "+card.getCol());
        }else{
            System.out.println(card.getFig()+" of "+card.getCol());
        }

    }
}
